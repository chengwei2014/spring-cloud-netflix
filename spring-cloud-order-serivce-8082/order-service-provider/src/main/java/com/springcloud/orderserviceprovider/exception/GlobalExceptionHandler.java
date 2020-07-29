package com.springcloud.orderserviceprovider.exception;

import com.springcloud.api.R;
import com.springcloud.exception.ValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 * 2020-07-28
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e, HttpServletRequest request){
        log.info("GlobalExceptionHandler.handleException:{},{}",request.getRequestURI(),e);
        String msg = "系统繁忙：" + e.getMessage();
        if (e instanceof ValidException){
            msg = e.getMessage();
        }
        return new R.Builder().buildCustomize(msg);
    }
}
