package com.pdkj.jack_shop.configurer;

public class XCXInfo {


//    public static final String APPID = "wx903822fb7d2f3961";
//    public static final String APPID = "wx9d82360ba0304046"; //二维码

    public static final String APPID = "wxda787e4bfeafe899";
    public static final String APPSECRET = "043f4cd27f377f78e96c016e84116a58";

//    public static final String APPSECRET = "022ae660f86828b6e61f40d24d2215eb";
    //public static final String APPSECRET = "cef00252dbb5c92677f456be9bbb26ff";
    //微信支付使用

    public static String CLIENT_CREDENTIAL = "client_credential";
    public static String AUTHORIZATION_CODE = "authorization_code";

    public final static String URL_OPENID = "https://api.weixin.qq.com/sns/jscode2session";
    public final static String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";

    //微信支付的商户id
    public static final String PAY_MCH_ID = "1504111201";
    //微信支付的商户密钥
    public static final String PAY_KEY = "pdkj2d8a71606ad80744b3c981c3384b";
    //支付成功后的服务器回调url
    //public static final String PAY_NOTIFY_URL = "https://www.lichuanshipin.ltd/lichuan/wx/pay/notifyInfo";
    public static final String PAY_NOTIFY_URL = "https://www.paiduikeji.com/jack_shop/pay/notifyInfo";
    public static final String REFUND_INFO_URL = "https://www.paiduikeji.com/jack_shop/pay/refundInfo";
    //签名方式，固定值
    public static final String PAY_SIGNTYPE = "MD5";
    //交易类型，小程序支付的固定值为JSAPI
    public static final String PAY_TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";


}