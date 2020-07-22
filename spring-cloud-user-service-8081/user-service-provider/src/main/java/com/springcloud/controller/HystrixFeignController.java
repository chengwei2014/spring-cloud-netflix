package com.springcloud.controller;

import com.springcloud.clients.OrderServiceFeignClient;
import com.springcloud.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 熔断器-Feign
 * 2020-07-19
 */
@RestController
public class HystrixFeignController {
    @Autowired
    private OrderServiceFeignClient orderServiceFeignClient;

    @GetMapping("/hystrix/feign/order")
    public String queryOrder(){
        return orderServiceFeignClient.orders();
    }

    @PostMapping("/hystrix/feign/order")
    public String inserOrder(){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId("100");
        int res = orderServiceFeignClient.insert(orderDto);
        String result = res > 0 ? "SUCCESS":"FAILED";
        return result;
    }
}
