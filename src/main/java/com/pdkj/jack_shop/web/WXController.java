package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.core.ParameterException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.util.Ognl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("wx")
public class WXController extends BaseController {

    @GetMapping("getOpenId")
    public Result getOpenId(String wxCode) throws Exception {
        if (Ognl.isEmpty(wxCode))
            throw new ParameterException("参数异常");
        String openId = wxService.getOpenId(wxCode);
        return ResultGenerator.genSuccessResult(openId);
    }

    @PostMapping("wxPayment")
    public Result payment(String order_id, String ip, String wxCode) throws Exception {
        if (Ognl.isEmpty(wxCode) || Ognl.isEmpty(ip) || Ognl.isEmpty(order_id))
            throw new ParameterException("参数异常");
        String openId = wxService.getOpenId(wxCode);
        if (Ognl.isEmpty(openId))
            throw new ParameterException("openId为空");
        Map<String, String> data = wxPayService.getPaymentInfo(openId, order_id, ip);
        return ResultGenerator.genSuccessResult(data);
    }

    @GetMapping("getToken")
    public Result getToken() throws Exception {
        return ResultGenerator.genSuccessResult(wxService.getToken());
    }

    @PostMapping("refund")
    //refund_order 用_连接订单id
    public Result refund(String refund_order, String ip, Long order_id) throws Exception {
        if (Ognl.isEmpty(refund_order) || Ognl.isEmpty(ip) || Ognl.isEmpty(order_id))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(wxPayService.refund(refund_order, ip, order_id));
    }
/*    @RequestMapping("notifyInfo")
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
    }*/

}
