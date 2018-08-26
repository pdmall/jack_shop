package com.pdkj.jack_shop.core;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(200),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//未认证（签名错误）
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVER_ERROR(500),
    NULL_PARAMETER(201),//参数为空
    TOKEN_NULL(402),//token异常
    PWDERROR(403)//密码错误
    ;//服务器内部错误


    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
