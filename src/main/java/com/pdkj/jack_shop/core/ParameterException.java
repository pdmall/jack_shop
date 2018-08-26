package com.pdkj.jack_shop.core;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.core
 * @author lvchong
 * @date 2018/8/14 13:50
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

/**
 * @author lvchong
 * @ClassName ParameterException
 * @Description 类描述
 * @date 2018/8/14
 */
public class ParameterException extends RuntimeException {
    private String message;

    public ParameterException() {
    }

    public ParameterException(String message) {
        super(message);
        this.message = message;

    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
