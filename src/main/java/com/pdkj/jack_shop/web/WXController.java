package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.util.NetUtils;
import com.pdkj.jack_shop.util.PayUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("wx")
public class WXController extends BaseController {

    @GetMapping("getOpenId")
    public Result getOpenId(String wxCode) throws Exception {
        String openId = wxService.getOpenId(wxCode);
        return ResultGenerator.genSuccessResult(openId);
    }

    @PostMapping("payment")
    public Result payment(String order_id,String ip) throws Exception {
        Map<String, String> data = wxPayService.getPaymentInfo(getUser(), order_id, ip);
        return ResultGenerator.genSuccessResult(data);
    }

    @RequestMapping("notifyInfo")
    public Result notifyInfo(HttpServletRequest request) throws Exception {
        String reqParams = NetUtils.getStringFromInputStream(request.getInputStream());
        Map<String, String> mapData = PayUtil.xmlToMap(reqParams);
        if ("SUCCESS".equals(mapData.get("return_code"))) {
            String orderId = mapData.get("attach");
            userOrderService.paySuccess(orderId);
        }
        String returnStr = "<xml>" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>" +
                "  <return_msg><![CDATA[OK]]></return_msg>" +
                "</xml>";
        return ResultGenerator.genSuccessResult(returnStr);
    }

}
