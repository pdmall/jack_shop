package com.pdkj.jack_shop.core;

public class CustomException extends RuntimeException {
    private String message;

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
        this.message = message;

    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
