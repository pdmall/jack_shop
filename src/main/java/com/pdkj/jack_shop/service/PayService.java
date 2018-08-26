package com.pdkj.jack_shop.service;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.service
 * @author lvchong
 * @date 2018/7/24 14:26
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.PwdErrorException;
import com.pdkj.jack_shop.core.app_constant;
import com.pdkj.jack_shop.model.FlowMoney;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.util.NetUtils;
import com.pdkj.jack_shop.util.PayUtil;
import com.pdkj.jack_shop.util.Tools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName PayService
 * @Description 类描述
 * @date 2018/7/24
 */
@Service
public class PayService extends BaseService {
    //支付成功后反馈
    public void notifyInfo(HttpServletRequest request, HttpServletResponse response) {
        //获取付款类型
        try {
            String reqParams = NetUtils.getStringFromInputStream(request.getInputStream());
            Map<String, String> mapData = PayUtil.xmlToMap(reqParams);
            if ("SUCCESS".equals(mapData.get("return_code"))) {
                this.sendWeChatOk(response, "SUCCESS", "");
                Integer trade_type = 1;  // 交易类型
                String time_end = mapData.get("time_end");//支付完成时间
                String pay_on = mapData.get("out_trade_no");
                String order_id = mapData.get("attach");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                userOrderDao.paySuccess(order_id, sdf.parse(time_end), trade_type, pay_on);
                Map<String, Object> map = userOrderDao.getOrder(order_id);
                Map<String, Object> list = userOrderDao.getDetails(order_id);
                //修改会员状态
                if (4 == Integer.valueOf(list.get("type_of_id").toString())) {
                    updateRole(map.get("user_id"), list.get("item_id"), order_id);
                }
                //流水记录 用户
                FlowMoney flowMoney = new FlowMoney();
                flowMoney.setId(Tools.generatorId());
                flowMoney.setUser_id(Long.parseLong(map.get("user_id").toString()));
                flowMoney.setValue(Double.parseDouble(map.get("final_price").toString()));
                flowMoney.setItem_id(Long.parseLong(order_id));
                flowMoney.setFlow_state_id(1);
                flowMoneyDao.addFlowMoney(flowMoney);
            }
        } catch (Exception e) {
            throw new PwdErrorException(e.getMessage());
        }
    }

    //退款后反馈
    public void refundInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            String reqParams = NetUtils.getStringFromInputStream(request.getInputStream());
            Map<String, String> mapData = PayUtil.xmlToMap(reqParams);
            if ("SUCCESS".equals(mapData.get("return_code"))) {
                this.sendWeChatOk(response, "SUCCESS", "");
                //退款订单号
                String out_trade_no = mapData.get("out_trade_no");
                //修改订单状态
                userOrderDao.updateOrderRefund(out_trade_no, 4);
                //获得订单信息
                Map<String,Object> orderInfo = userOrderDao.getOrderByPayOn(out_trade_no);
                //流水记录 用户
                FlowMoney flowMoney = new FlowMoney();
                flowMoney.setId(Tools.generatorId());
                flowMoney.setUser_id(Long.parseLong(orderInfo.get("user_id").toString()));
                flowMoney.setValue(Double.parseDouble(mapData.get("refund_fee")));
                flowMoney.setItem_id(Long.parseLong(orderInfo.get("id").toString()));
                flowMoney.setFlow_state_id(4);
                flowMoneyDao.addFlowMoney(flowMoney);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new PwdErrorException(e.getMessage());
        }
    }

    /**
     * 发送请求响应微信支付平台
     *
     * @param response      响应对象
     * @param returnCode    返回状态码
     * @param returnMessage 返回信息
     */
    private void sendWeChatOk(HttpServletResponse response, String returnCode, String returnMessage) {
        try {
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            response.setLocale(Locale.SIMPLIFIED_CHINESE);
            PrintWriter printWriter = response.getWriter();
            printWriter.append("<xml>\n");
            printWriter.append("<return_code><![CDATA[");
            printWriter.append(returnCode);
            printWriter.append("]]></return_code>\n");
            printWriter.append("<return_msg><![CDATA[");
            printWriter.append(returnMessage);
            printWriter.append("]]></return_msg>\n");
            printWriter.append("</xml>");
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new PwdErrorException(e.getMessage());
        }
    }

    //钱包支付
    @Transactional
    public String walletPayment(Long orderId, String payPwd, Integer trade_type, User user) {
        if (userOrderDao.verifyNoPayOrder(orderId) == 0)
            throw new CustomException("订单支付已完成");
        if (payPwd.equals(user.getPay_password())) {
            Map<String, Object> map = userOrderDao.getShopIdByOrderId(orderId);
            Object final_price = map.get("final_price");
            //修改余额
            userWalletDao.updateMoney(final_price, user.getId(), 1);
            //修改状态为待使用
            userOrderDao.paySuccess(orderId.toString(), new Date(), trade_type, Tools.generatorId().toString());
            Map<String, Object> list = userOrderDao.getDetails(orderId.toString());
            //修改会员状态
            if (4 == Integer.valueOf(list.get("type_of_id").toString())) {
                updateRole(user.getId(), list.get("item_id"), orderId.toString());
            }
            //添加流水
            FlowMoney flowMoney = new FlowMoney();
            flowMoney.setId(Tools.generatorId());
            flowMoney.setUser_id(user.getId());
            flowMoney.setValue(Double.valueOf(final_price.toString()));
            flowMoney.setItem_id(orderId);
            flowMoney.setFlow_state_id(1);
            flowMoneyDao.addFlowMoney(flowMoney);
        } else {
            throw new PwdErrorException("密码错误");
        }
        return "SUCCESS";
    }

    private void transferParent(String parent_id, String order_id, Object price) {
        //修改用户钱包余额 0 收入 1 支出
        userWalletDao.updateMoney(price, parent_id, 0);
        //添加流水记录
        FlowMoney flowMoney = new FlowMoney();
        flowMoney.setId(Tools.generatorId());
        flowMoney.setUser_id(Long.parseLong(parent_id));
        flowMoney.setValue(Double.valueOf(price.toString()));
        flowMoney.setItem_id(Long.parseLong(order_id));
        flowMoney.setFlow_state_id(10);
        flowMoneyDao.addFlowMoney(flowMoney);
    }

    public void updateRole( Object user_id, Object role, String order_id) {
        //修改充值用户id
        userDao.updateRole(user_id, role);
        //获得全部上级
        List<Map<String, Object>> myAllParent = shareOrginDao.getMyAllParent(user_id);
        //判断是否有父级
        if (myAllParent.size() > 0) {
            //获得全部上级id
            Map<String, Object> objectMap = myAllParent.get(0);
            //上级
            Long level2 = Long.parseLong(objectMap.get("Level2").toString());
            //上上级
            Long level3 = Long.parseLong(objectMap.get("Level3").toString());
            //验证开通会员返利 2黄金 3钻石
            if (Integer.valueOf(role.toString()) == 2) {
                //向上级转账
                if (level2 != null && (userDao.getUserRole(level2, 2) > 0 || userDao.getUserRole(level2, 3) > 0)) {
                    //获得单价
                    Object price = appConstantDao.getPriceConstant(app_constant.GOLD_REBATE);
                    //给父级转账
                    transferParent(level2.toString(), order_id, price);
                }
                //向上上级转账
                if (level3 != null && (userDao.getUserRole(level3, 2) > 0 || userDao.getUserRole(level3, 3) > 0)) {
                    //获得黄金会员充值返利 获得上级获得奖励金额id为4
                    Object price1 = appConstantDao.getPriceConstant(app_constant.GOLD_LEVEL_REBATE);
                    transferParent(level3.toString(), order_id, price1);
                }
            } else if (Integer.valueOf(role.toString()) == 3) {
                //向上级转账
                if (level2 != null && (userDao.getUserRole(level2, 2) > 0 || userDao.getUserRole(level2, 3) > 0)) {
                    //获得单价
                    Object price = appConstantDao.getPriceConstant(app_constant.DIAMOND_REBATE);
                    //给父级转账
                    transferParent(level2.toString(), order_id, price);
                }
                //向上上级转账
                if (level3 != null && (userDao.getUserRole(level3, 2) > 0 || userDao.getUserRole(level3, 3) > 0)) {
                    //获得黄金会员充值返利 获得上级获得奖励金额id为4
                    Object price1 = appConstantDao.getPriceConstant(app_constant.DIAMOND_LEVEL_REBATE);
                    transferParent(level3.toString(), order_id, price1);
                }
            }
        }
    }
}
