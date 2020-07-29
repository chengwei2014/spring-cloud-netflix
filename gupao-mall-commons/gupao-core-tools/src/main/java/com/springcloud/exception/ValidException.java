package com.springcloud.exception;

/**
 * 校验异常
 * 2020-07-28
 */
public class ValidException extends BaseException {
    public ValidException() {
        super();
    }

    public ValidException(String message) {
        super(message);
        this.msg = message;
    }

    public ValidException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
}
