package com.springcloud.exception;

/**
 * 基础异常
 * 2020-07-28
 */
public class BaseException extends RuntimeException {
    protected String code;
    protected String msg;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
        this.msg = message;
    }

    public BaseException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
}
