package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 熔断器--RestTemplate
 * 2020-07-19
 */
@DefaultProperties(defaultFallback = "fallback")
@RestController
public class HystrixContorller {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50")
    }
//    ,groupKey = "",threadPoolKey = "order-service"
    )
    @GetMapping("/hystrix/order/{num}")
    public String queryOrder(@PathVariable("num")int num){
        if (num%2==0){
            return "正常访问";
        }
        return restTemplate.getForObject("http://localhost:8082/orders", String.class);
    }

    public String fallback(int num){
        return "请求出错！";
    }

    @HystrixCommand(fallbackMethod = "timeoutFallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    @GetMapping("/hrstrix/tmeout")
    public String queryTimeOut(){
        return restTemplate.getForObject("http://localhost:8082/orders", String.class);
    }

    public String timeoutFallback(){
        return "请求超时！";
    }
}
