package com.springcloud.orderserviceprovider.service;

import com.springcloud.orderserviceprovider.dto.OrderDto;

/**
 * 订单业务处理
 * 2020-07-28
 */
public interface IOrderService {
    String createOrder(OrderDto orderDto);
}
