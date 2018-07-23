package com.pdkj.jack_shop.web;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.web
 * @author lvchong
 * @date 2018/7/22 14:14
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.util.NetUtils;
import com.pdkj.jack_shop.util.PayUtil;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public void notifyInfo(HttpServletRequest request, HttpServletResponse response) {
        String returnStr = null;
        //获取付款类型
        try {
            String reqParams = NetUtils.getStringFromInputStream(request.getInputStream());
            Map<String, String> mapData = PayUtil.xmlToMap(reqParams);
            if("SUCCESS".equals(mapData.get("return_code"))){
                Integer trade_type = 1;  // 交易类型
                String time_end = mapData.get("time_end");//支付完成时间
                String order_id = mapData.get("attach");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                userOrderService.paySuccess(order_id,sdf.parse(time_end),trade_type);
                returnStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                response.getWriter().write(returnStr);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
