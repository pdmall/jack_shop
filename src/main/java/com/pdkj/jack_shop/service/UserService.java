package com.pdkj.jack_shop.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.pdkj.jack_shop.configurer.AliYunSMS;
import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.ParameterException;
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
        /*if (oldCode.equals(verCode)) {*/
        if (true) {
            User oldUser = userDao.getUserByPhone(user.getPhone());
            if (oldUser == null) {
                user.setToken(Tools.uuid());
                user.setQr_code(qrCodeDao.addQRCode(user.getPhone()));
                userWalletDao.save(userDao.save(user));
                if(user.getPay_password()==null && user.getPay_password()==""){
                    user.setPassword("0");
                }else{
                    user.setPassword("1");
                }
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

    public Map<String, Object> verifyOrderDetails(Long user_id, String user_order_id, Integer count, String user_order_details_id) {
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
        Map<String, Object> ret = null;
        if (userDao.verifyUser(user_id, shop_id) > 0) {
            if (counts >= count) {
                if (type_of_id == 1) {
                    ret = couponDao.verifyCoupon(item_id);
                    ret.put("count", count);
                    ret.put("sum", count * (Double.valueOf(ret.get("buy_price").toString())));
                    ret.put("user_order_details_id", user_order_details_id);
                    return ret;
                } else if (type_of_id == 2) {
                    ret = groupBuyDao.verifyGroupBuy(item_id);
                    ret.put("count", count);
                    ret.put("sum", count * (Double.valueOf(ret.get("buy_price").toString())));
                    ret.put("user_order_details_id", user_order_details_id);
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

    /*public Map<String, Object> verifyOrderDetails(Long user_id, Long user_order_details_id,String user_order_id) {
        //获得订单详情
        Map<String, Object> orderDetails = userOrderDao.verifyOrder(user_order_details_id);
        Integer type_of_id = Integer.valueOf(orderDetails.get("type_of_id").toString());
        Long item_id = Long.parseLong(orderDetails.get("item_id").toString());
        //消费的商铺
        //Long shop_id = Long.parseLong(orderInfo.get("shop_id").toString());
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
    public void getConfirm(Long user_order_id, Integer count, String user_order_details_id, Double sum) {
        Map<String, Object> map = userOrderDao.getShopIdByOrderId(user_order_id);
        //消费者
        Long user_id = Long.parseLong(map.get("user_id").toString());
        //商铺id
        Long shop_id = Long.parseLong(map.get("shop_id").toString());
        //店铺所有者
        Long shop_user = Long.parseLong(shopDao.getUserIdByShopId(shop_id).get("user_id").toString());
        //红利
        Double dividends = sum / 100 > 0.01 ? (sum / 100) : 0.01;
        //修改消费者的卷状态
        List<Map<String, Object>> list = userOrderDao.getDetailsId(user_order_id, count);
        if (user_order_details_id == null) {
            if (list.size() >= count) {
                for (Map<String, Object> map1 : list) {
                    userOrderDao.updateOrderRefund(map1.get("id").toString(), 3);
                }
                if(true){

                }
            } else {
                throw new CustomException("数量不足");
            }
        } else {
            //修改消费者的卷状态
            if (userOrderDao.updateDetails(3, user_order_details_id) == 0) {
                throw new CustomException("该卷失效");
            }else{

            }
        }
        //修改商铺拥有者的钱包余额
        userWalletDao.updateMoney(sum - dividends, shop_user, 0);
        //添加流水记录
        FlowMoney flowMoney = new FlowMoney();
        flowMoney.setId(Tools.generatorId());
        flowMoney.setUser_id(shop_user);
        flowMoney.setValue(sum - dividends);
        flowMoney.setItem_id(user_order_id);
        flowMoney.setFlow_state_id(7);
        flowMoneyDao.addFlowMoney(flowMoney);
        //添加红利
        shareOrginDao.updateDividends(dividends, user_id);
        //获得被返利用户的id
        Long level3 = shareOrginDao.getMyLevel3Id(user_id);
        if (level3 != null) {
            //判断用户是否是钻石会员
            if (userDao.getUserRole(level3, 3) > 0) {
                //修改用户钱包余额 0 收入 1 支出
                userWalletDao.updateMoney(dividends, level3, 0);
                //添加流水记录
                FlowMoney flowMoney1 = new FlowMoney();
                flowMoney1.setId(Tools.generatorId());
                flowMoney1.setUser_id(level3);
                flowMoney1.setValue(dividends);
                flowMoney1.setItem_id(user_order_id);
                flowMoney1.setFlow_state_id(4);
                flowMoneyDao.addFlowMoney(flowMoney1);
            }
        }
    }

    public String updatePayPassword(Long user_id, String payPassword) {
        if(userDao.updatePayPassword(user_id,payPassword)>0){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }


    public List<Map<String,Object>> payType() {
        return userDao.payType();
    }

    public String verifVerCode(String phone, String verCode) {
        String oldCode = (String) getCache("verCode" + phone);
        if (oldCode.equals(verCode))
            return "成功";
        throw new CustomException("验证不正确");
    }
}
        /*    //修改商铺拥有者的钱包余额
            userWalletDao.updateMoney(sum - dividends, shop_user, 0);
            //添加流水记录
            FlowMoney flowMoney = new FlowMoney();
            flowMoney.setId(Tools.generatorId());
            flowMoney.setUser_id(shop_user);
            flowMoney.setValue(sum - dividends);
            flowMoney.setItem_id(user_order_id);
            flowMoney.setItem_id_type(2);
            flowMoney.setFlow_state_id(7);
            flowMoneyDao.addFlowMoney(flowMoney);
            //添加红利
            shareOrginDao.updateDividends(dividends, user_id);
            //获得被返利用户的id
            List<Map<String, Object>> level3s = shareOrginDao.getMyLevel3(user_id);
            if (level3s.size() > 0) {
                String level3 = level3s.get(0).get("Level3").toString();
                //判断用户是否是钻石会员
                if (userDao.getUserRole(level3, 3) > 0) {
                    //修改用户钱包余额 0 收入 1 支出
                    userWalletDao.updateMoney(dividends, level3, 0);
                    //添加流水记录
                    FlowMoney flowMoney1 = new FlowMoney();
                    flowMoney1.setId(Tools.generatorId());
                    flowMoney1.setUser_id(Long.parseLong(level3));
                    flowMoney1.setValue(dividends);
                    flowMoney1.setItem_id(user_order_id);
                    flowMoney1.setItem_id_type(2);
                    flowMoney1.setFlow_state_id(4);
                    flowMoneyDao.addFlowMoney(flowMoney1);
                }
            }*/