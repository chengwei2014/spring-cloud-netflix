package com.springcloud.exception;

/**
 * BizException
 * 2020-07-28
 */
public class BizException extends BaseException {
    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
        this.msg = message;
    }

    public BizException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
}
