package com.springcloud.orderserviceprovider.controller;

import com.springcloud.api.R;
import com.springcloud.orderserviceprovider.dto.OrderDto;
import com.springcloud.orderserviceprovider.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderController
 * 2020-07-28
 */
@RestController
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping("/createOrder")
    public R order(@RequestBody @Validated OrderDto orderDto, BindingResult bindingResult){
        orderDto.validData(bindingResult);
        String orderId = orderService.createOrder(orderDto);
        return new R.Builder<>().setData(orderId).buildOk();
    }
}
