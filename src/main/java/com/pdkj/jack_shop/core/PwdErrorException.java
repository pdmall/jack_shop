package com.pdkj.jack_shop.core;

public class PwdErrorException extends RuntimeException {
    private String message;

    public PwdErrorException() {
    }

    public PwdErrorException(String message) {
        super(message);
        this.message = message;

    }

    public PwdErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
