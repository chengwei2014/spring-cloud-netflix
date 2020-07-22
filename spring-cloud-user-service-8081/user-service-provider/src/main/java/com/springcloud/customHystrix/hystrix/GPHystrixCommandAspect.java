package com.springcloud.customHystrix.hystrix;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * GPHystrixCommandAspect
 * 2020-07-22
 */
@Component
@Aspect
public class GPHystrixCommandAspect {
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Pointcut(value = "@annotation(GPHystrixCommand)")
    public void pointCut(){}

    @Around(value = "pointCut() && @annotation(hystrixCommand)")
    public Object doPointCut(final ProceedingJoinPoint joinPoint, GPHystrixCommand hystrixCommand) throws InterruptedException, ExecutionException, TimeoutException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int timeout = hystrixCommand.timeout();
        //前置判断
        Future<Object> future = executorService.submit(() -> {
            try {
                return joinPoint.proceed();//执行目标方法
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        });

        //是否超时执行
        Object result;
        try {
            result = future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            future.cancel(true);
            if (StringUtils.isBlank(hystrixCommand.fallback())){
                throw e;
            }

            //调用fallback
            result = invokeFallBack(joinPoint,hystrixCommand.fallback());
        }

        return result;
    }

    private Object invokeFallBack(ProceedingJoinPoint joinPoint, String fallback) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //获取被代理方法和参数
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Class<?>[] parameterTypes = method.getParameterTypes();

        //获取fallback方法
        try {
            Method fallbackMethod = joinPoint.getTarget().getClass().getMethod(fallback, parameterTypes);
            fallbackMethod.setAccessible(true);
            return fallbackMethod.invoke(joinPoint.getTarget(), joinPoint.getArgs());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
