package com.springcloud.customHystrix.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.web.client.RestTemplate;

/**
 * HystrixCommanService
 * 2020-07-22
 */
public class HystrixCommanService extends HystrixCommand<String> {
    private int num;
    private RestTemplate restTemplate;

    public HystrixCommanService(int num,RestTemplate restTemplate){
        //设置 @HystrixCommand中参数
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("order-service")).
                andCommandPropertiesDefaults(HystrixCommandProperties.Setter().
                        withCircuitBreakerEnabled(true).
                        withCircuitBreakerRequestVolumeThreshold(5)));

        this.num = num;
        this.restTemplate = restTemplate;
    }

    protected HystrixCommanService(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected String run() throws Exception {
        if (num%2==0){
            return "访问正常！";
        }
        return restTemplate.getForObject("http://localhost:8082/orders", String.class);
    }

    @Override
    protected String getFallback() {
        return "请求被降级！";
    }
}
