package com.pdkj.jack_shop.web;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.web
 * @author lvchong
 * @date 2018/7/22 14:14
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.util.NetUtils;
import com.pdkj.jack_shop.util.PayUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName pay
 * @Description 类描述
 * @date 2018/7/22
 */
@RestController
@RequestMapping("pay")
public class PayController extends BaseController {
    @RequestMapping("notifyInfo")
    public void notifyInfo(HttpServletRequest request,HttpServletResponse response) {
        //获取付款类型
        try {
            String reqParams = NetUtils.getStringFromInputStream(request.getInputStream());
            Map<String, String> mapData = PayUtil.xmlToMap(reqParams);
            System.out.println("transaction_id:" + mapData.get("transaction_id"));
            if ("SUCCESS".equals(mapData.get("return_code"))) {
                Integer trade_type = 1;  // 交易类型
                String time_end = mapData.get("time_end");//支付完成时间
                String order_id = mapData.get("attach");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                userOrderService.paySuccess(order_id, sdf.parse(time_end), trade_type);
                this.sendWeChat(response, "SUCCESS", "");
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

        }
    }


}
