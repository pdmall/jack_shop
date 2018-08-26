package com.pdkj.jack_shop.core;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.core
 * @author lvchong
 * @date 2018/8/15 11:13
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

/**
 * @author lvchong
 * @ClassName TokenException
 * @Description 类描述
 * @date 2018/8/15
 */
public class TokenException extends RuntimeException {
    private String message;

    public TokenException() {
    }

    public TokenException(String message) {
        super(message);
        this.message = message;

    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
