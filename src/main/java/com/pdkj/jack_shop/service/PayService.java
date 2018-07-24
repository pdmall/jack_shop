package com.pdkj.jack_shop.service;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.service
 * @author lvchong
 * @date 2018/7/24 14:26
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.dao.DaoBase;
import com.pdkj.jack_shop.model.FlowMoney;
import com.pdkj.jack_shop.model.UserCouponRel;
import com.pdkj.jack_shop.model.UserGroupBuyRel;
import com.pdkj.jack_shop.util.NetUtils;
import com.pdkj.jack_shop.util.PayUtil;
import com.pdkj.jack_shop.util.Tools;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
    public void notifyInfo(HttpServletRequest request, HttpServletResponse response) {
        //获取付款类型
        try {
            String reqParams = NetUtils.getStringFromInputStream(request.getInputStream());
            Map<String, String> mapData = PayUtil.xmlToMap(reqParams);
            System.out.println("transaction_id:" + mapData.get("transaction_id")+"----------------------------------");
            if ("SUCCESS".equals(mapData.get("return_code"))) {
                this.sendWeChat(response, "SUCCESS", "");
                Integer trade_type = 1;  // 交易类型
                String time_end = mapData.get("time_end");//支付完成时间
                String order_id = mapData.get("attach");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                userOrderDao.paySuccess(order_id, sdf.parse(time_end), trade_type);
                Map<String ,Object> map = userOrderDao.getOrder(order_id);
                //添加卷(团餐)到卷包
                if(1==Integer.valueOf(map.get("type_of").toString())){
                    UserGroupBuyRel userGroupBuyRel = new UserGroupBuyRel();
                    userGroupBuyRel.setIs_use(1);
                    userGroupBuyRel.setGroup_buy_id(Long.parseLong(map.get("item_id").toString()));
                    userGroupBuyRel.setUser_id(Long.parseLong(map.get("user_id").toString()));
                    groupBuyDao.addUserGroupBuyRel(userGroupBuyRel);
                }else if (2==Integer.valueOf(map.get("type_of").toString())) {
                    UserCouponRel userCouponRel = new UserCouponRel();
                    userCouponRel.setIs_use(1);
                    userCouponRel.setCoupon_id(Long.parseLong(map.get("item_id").toString()));
                    userCouponRel.setUser_id(Long.parseLong(map.get("user_id").toString()));
                    couponDao.addUserCouponRel(userCouponRel);
                }
                //流水记录 用户
                FlowMoney flowMoney = new FlowMoney();
                flowMoney.setId(Tools.generatorId());
                flowMoney.setUser_id(Long.parseLong(map.get("user_id").toString()));
                flowMoney.setValue(Double.parseDouble(map.get("final_price").toString()));
                flowMoney.setUser_order_id(Long.parseLong(order_id));
                flowMoney.setFlow_state(1);
                flowMoneyDao.addFlowMoney(flowMoney);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * 发送请求响应微信支付平台
     *
     * @param response      响应对象
     * @param returnCode    返回状态码
     * @param returnMessage 返回信息
     */
    private void sendWeChat(HttpServletResponse response, String returnCode, String returnMessage) {
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
        }
    }
}
