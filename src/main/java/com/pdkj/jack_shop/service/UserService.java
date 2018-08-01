package com.pdkj.jack_shop.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.pdkj.jack_shop.configurer.AliYunSMS;
import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.FlowMoney;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.util.Tools;
import com.pdkj.jack_shop.util.sql.Pager;
import org.hibernate.annotations.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class UserService extends BaseService<User> {

    /*@Cacheable(value = "token", key = "#p0")*/
    public User getUserByToken(String token) {
        User user = userDao.getUserByToken(token);
        return user;
    }


    public String getVerCode(String phone, String sms) throws CustomException, ClientException {
        String verCodeNum = getVerCodeNum(4);
        SendSmsResponse sendSmsResponse = AliYunSMS.sendSms(phone, verCodeNum, sms);
        if (!sendSmsResponse.getCode().equalsIgnoreCase("ok")) {
            if (sendSmsResponse.getMessage().contains("天")) {
                throw new CustomException("太快了，一天之后再试");
            } else if (sendSmsResponse.getMessage().contains("小时")) {
                throw new CustomException("太快了，一小时之后再试");
            } else if (sendSmsResponse.getMessage().contains("分钟")) {
                throw new CustomException("太快了，一分钟之后再试");
            }
        }
        setCache("verCode" + phone, verCodeNum, 300);
        return sendSmsResponse.getMessage();
    }

    public User register(User user, String verCode) throws Exception {
        String oldCode = (String) getCache("verCode" + user.getPhone());
        if (oldCode.equals(verCode)) {
            User oldUser = userDao.getUserByPhone(user.getPhone());
            if (oldUser == null) {
                user.setToken(Tools.uuid());
                user.setQr_code(qrCodeDao.addQRCode(user.getPhone()));
                userWalletDao.save(userDao.save(user));
                user.setPassword(null);
                return user;
            } else {
                oldUser.setToken(Tools.uuid());
                userDao.updateToken(oldUser.getId(), oldUser.getToken());
                oldUser.setPassword(null);
                return oldUser;
            }
        } else {
            throw new CustomException("验证码有误");
        }
    }

    private String getVerCodeNum(int varCodelength) {
        int verCode = 0;
        if (varCodelength == 6) {
            verCode = 1000000 - new Random().nextInt(899999);
        } else {
            verCode = 10000 - new Random().nextInt(8999);
        }
        return String.valueOf(verCode);
    }

    public Map<String, Object> getUser(Long id) {
        return userDao.getUser(id);
    }


    public void delImg(String img_url) {
        userDao.delImg(img_url);
    }

    public String updateUserInfo(User user, Long id) {
        user.setId(id);
        return userDao.update(user) > 0 ? "修改成功" : "修改失败";
    }


    public Map<String, Object> getQRCode(Long id) {
        return qrCodeDao.getQRCode(id);
    }

    public List<Map<String, Object>> getRole() {
        return userDao.getRole();
    }

    public Map<String, Object> verifyOrderDetails(Long user_id, String user_order_id, Integer count) {
        //获得可用卷的数量
        Integer counts = userOrderDao.verifyOrderDetails(user_order_id);
        //获得订单信息
        Map<String, Object> orderInfo = userOrderDao.getOrder(user_order_id);
        //获得订单详情
        Map<String, Object> orderDetails = userOrderDao.getDetails(user_order_id);
        Integer type_of_id = Integer.valueOf(orderDetails.get("type_of_id").toString());
        Long item_id = Long.parseLong(orderDetails.get("item_id").toString());
        //消费的商铺
        Long shop_id = Long.parseLong(orderInfo.get("shop_id").toString());
        Map<String,Object> ret =  null;
        if (userDao.verifyUser(user_id, shop_id) > 0) {
            if (counts >= count) {
                if (type_of_id == 1) {
                    ret = couponDao.verifyCoupon(item_id);
                    ret.put("user_order_id",user_order_id);
                    ret.put("count",count);
                    return ret;
                } else if (type_of_id == 2) {
                    ret.put("user_order_id",user_order_id);
                    ret.put("count",count);
                    ret = groupBuyDao.verifyGroupBuy(item_id);
                    return ret;
                } else {
                    throw new CustomException("类型不对哟");
                }
            } else {
                throw new CustomException("数量不足哟");
            }
        } else {
            throw new CustomException("您没有审核资格哟");
        }
    }
    /*public Map<String, Object> verifyOrderDetails(Long user_id, Long user_order_details_id) {
        //获得订单详情
        Map<String, Object> orderDetails = userOrderDao.getUserOrderDetails(user_order_details_id);
        Integer type_of_id = Integer.valueOf(orderDetails.get("type_of_id").toString());
        Long item_id = Long.parseLong(orderDetails.get("item_id").toString());
        //消费的商铺
        Long shop_id = Long.parseLong(orderInfo.get("shop_id").toString());
        Map<String,Object> ret =  null;
        if (userDao.verifyUser(user_id, shop_id) > 0) {
                if (type_of_id == 1) {
                    ret = couponDao.verifyCoupon(item_id);
                    return ret;
                } else if (type_of_id == 2) {
                    ret = groupBuyDao.verifyGroupBuy(item_id);
                    return ret;
                } else {
                    throw new CustomException("类型不对哟");
                }
        } else {
            throw new CustomException("您没有审核资格哟");
        }
    }*/
    @Transactional
    public void getConfirm(Long user_order_details) {
        //获得本次消费单价
        Map<String, Object> map = userOrderDao.getUserOrderDetails(user_order_details);
        Double price = Double.parseDouble(map.get("price").toString());
        Long user_order_id = Long.parseLong(map.get("user_order_id").toString());
        //获得这个商铺的所有者id
        Map<String, Object> orderMap = userOrderDao.getShopIdByOrderId(user_order_id);
        //消费者
        Long user_id = Long.parseLong(orderMap.get("user_id").toString());
        //消费的商铺
        Long shop_id = Long.parseLong(orderMap.get("shop_id").toString());
        Long shop_user = Long.parseLong(shopDao.getUserIdByShopId(shop_id).get("user_id").toString());
        //红利
        Double dividends = price / 100 > 0.01 ? (price / 100) : 0.01;
        //修改消费者的卷状态
        userOrderDao.updateOrderRefund(user_order_details.toString(), 3);
        //修改商铺拥有者的钱包余额
        userWalletDao.updateMoney(price - dividends, shop_user, 0);
        //添加流水记录
        FlowMoney flowMoney = new FlowMoney();
        flowMoney.setId(Tools.generatorId());
        flowMoney.setUser_id(shop_user);
        flowMoney.setValue(price - dividends);
        flowMoney.setItem_id(user_order_details);
        flowMoney.setItem_id_type(2);
        flowMoney.setFlow_state_id(7);
        flowMoneyDao.addFlowMoney(flowMoney);
        //添加红利
        shareOrginDao.updateDividends(dividends, user_id);
        //获得被返利用户的id
        String level3 = shareOrginDao.getMyLevel3(user_id).get("Level3").toString();
        //判断用户是否是钻石会员
        if (userDao.getUserRole(level3, 3) > 0) {
            //修改用户钱包余额 0 收入 1 支出
            userWalletDao.updateMoney(dividends, level3, 0);
            //添加流水记录
            FlowMoney flowMoney1 = new FlowMoney();
            flowMoney.setId(Tools.generatorId());
            flowMoney.setUser_id(Long.parseLong(level3));
            flowMoney.setValue(dividends);
            flowMoney.setItem_id(user_order_details);
            flowMoney.setItem_id_type(2);
            flowMoney1.setFlow_state_id(4);
            flowMoneyDao.addFlowMoney(flowMoney1);
        }
    }

}
