package com.springcloud.controller;

import com.springcloud.customHystrix.hystrix.GPHystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * GPHystrixController前端控制器（通过编写@GPHystrixCommand实现熔断）
 * 2020-07-22
 */
@RestController
public class GPHystrixController {
    @Autowired
    private RestTemplate restTemplate;

    @GPHystrixCommand(fallback = "fallback",timeout = 10000)
    @GetMapping("/hystrix/test")
    public String test(){
        return restTemplate.getForObject("http://localhost:8082/orders", String.class);
    }

    public String fallback(){
        return "请求被降级！";
    }
}
