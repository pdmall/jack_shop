package com.pdkj.jack_shop.service;


import com.pdkj.jack_shop.configurer.XCXInfo;
import com.pdkj.jack_shop.dao.UserOrderDao;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.util.PayUtil;
import com.pdkj.jack_shop.util.Tools;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class WxPayService extends BaseService {


    /**
     * 微信支付
     * @param open_id
     * @param order_id 订单id
     * @param ip  ip地址
     * @return
     * @throws Exception
     */
    public Map<String,String> getPaymentInfo(String open_id, String order_id, String ip) throws Exception {
        String payPrice = userOrderDao.getOrderPrice(order_id);
        return getPaymentInfo(open_id,order_id,payPrice,"订单付款",ip);
    }
    public Map<String,String> refund( String order_id,String refund_fee, String ip) throws Exception {
        Map<String,Object> map = userOrderDao.getOrderRefund(order_id);
        return refund(map.get("pay_on").toString(),map.get("final_price").toString(),refund_fee ,ip);
    }
    /**
     *
     * @param openid
     * @param orderId
     * @param payMoney  微信付款金额  角为单位。 100 = 一元
     * @param desc  付款详情
     * @param ip
     * @return
     * @throws Exception
     */
    public  Map<String, String> getPaymentInfo(String openid,String orderId, String payMoney, String desc,String ip) throws Exception {
        //微信付款 是 以 分为单位。所有要将元* 100
        payMoney = new BigDecimal(payMoney).multiply(new BigDecimal(100)).setScale(0).toString();
        //test:
        payMoney = "1";
        String nonce_str = PayUtil.generateNonceStr();
        Long aLong = Tools.generatorId();
        //组装参数，用户生成统一下单接口的签名
        Map<String, String> packageParams = new HashMap ();
        packageParams.put("appid", XCXInfo.APPID);
        packageParams.put("mch_id", XCXInfo.PAY_MCH_ID);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", desc);
        packageParams.put("out_trade_no", aLong.toString());//商户订单号
        packageParams.put("attach", orderId);//商户订单号
        packageParams.put("total_fee", payMoney);//支付金额，这边需要转成字符串类型，否则后面的签名会失败
        packageParams.put("spbill_create_ip", ip);
        packageParams.put("notify_url", XCXInfo.PAY_NOTIFY_URL);//支付成功后的回调地址
        packageParams.put("trade_type", XCXInfo.PAY_TRADETYPE);//支付方式
        packageParams.put("openid", openid);

        String signedXml = PayUtil.generateSignedXml(packageParams, XCXInfo.PAY_KEY);

        String resultData = PayUtil.httpRequest(XCXInfo.PAY_URL, "POST", signedXml);

        Map map = PayUtil.doXMLParse(resultData);
        String return_code = (String) map.get("return_code");//返回状态码
        String timeStamp = String.valueOf(PayUtil.getCurrentTimestamp());

        Map<String, String> data = new HashMap<>();//返回给小程序端需要的参数
        if (return_code == "SUCCESS" || return_code.equals(return_code)) {
            data.put("appId", (String) map.get("appid"));
            data.put("nonceStr", (String) map.get("nonce_str"));
            data.put("package", "prepay_id=" + map.get("prepay_id"));
            data.put("timeStamp", timeStamp);
            data.put("signType", "MD5");
            String signature = PayUtil.generateSignature(data, XCXInfo.PAY_KEY);
            data.put("paySign", signature);
        }
        data.put("payState", "wx");
        return data;
    }
    //退款
    public  Map<String, String> refund(String pay_on, String payMoney,String refund_fee,String ip) throws Exception {
        //微信付款 是 以 分为单位。所有要将元* 100
        payMoney = new BigDecimal(payMoney).multiply(new BigDecimal(100)).setScale(0).toString();
        refund_fee = new BigDecimal(refund_fee).multiply(new BigDecimal(100)).setScale(0).toString();
        //test:
        payMoney = "1";
        String nonce_str = PayUtil.generateNonceStr();
        Long aLong = Tools.generatorId();
        //组装参数，用户生成统一下单接口的签名
        Map<String, String> packageParams = new HashMap ();
        packageParams.put("appid", XCXInfo.APPID);
        packageParams.put("mch_id", XCXInfo.PAY_MCH_ID);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("out_trade_no", aLong.toString());//退款订单号
        packageParams.put("out_refund_no", pay_on);//退款的那个订单
        packageParams.put("total_fee", payMoney);//支付金额，这边需要转成字符串类型，否则后面的签名会失败
        packageParams.put("refund_fee", refund_fee);//退款金额
        packageParams.put("spbill_create_ip", ip);
        packageParams.put("notify_url", XCXInfo.REFUND_INFO_URL);//退款完成后调用的地址
        String signedXml = PayUtil.generateSignedXml(packageParams, XCXInfo.PAY_KEY);
        String resultData = PayUtil.httpRequest(XCXInfo.PAY_URL, "POST", signedXml);
        Map map = PayUtil.doXMLParse(resultData);
        String return_code = (String) map.get("return_code");//返回状态码
        Map<String, String> data = new HashMap<>();//返回给小程序端需要的参数
        if (return_code == "SUCCESS" ) {
            data.put("out_refund_no", (String) map.get("out_refund_no"));
            data.put("refund_fee", (String) map.get("refund_fee"));
            data.put("total_fee", (String)map.get("total_fee"));
            userOrderDao.updateOrderRefund((String)map.get("out_refund_no"),6);
        }
        return data;
    }

}
