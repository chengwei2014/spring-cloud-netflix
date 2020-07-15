package com.springcloud;

import com.springcloud.dto.OrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * OrderService
 * 2020-07-14
 **/
public interface OrderService {

    @GetMapping("/orders")
    String orders();

    @PostMapping("/order")
    int insert(OrderDto dto);
}
