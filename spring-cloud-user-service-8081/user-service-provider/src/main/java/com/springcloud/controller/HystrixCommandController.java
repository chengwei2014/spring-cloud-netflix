package com.springcloud.controller;

import com.springcloud.customHystrix.service.HystrixCommanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * HystrixCommandController前端控制器(通过继承HystrixCommand来实现熔断)
 * 2020-07-22
 */
@RestController
public class HystrixCommandController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hystrix/command/{num}")
    public String hystrixCommand(@PathVariable("num") int num){
        HystrixCommanService hystrixCommanService = new HystrixCommanService(num, restTemplate);
        return hystrixCommanService.execute();
    }
}
