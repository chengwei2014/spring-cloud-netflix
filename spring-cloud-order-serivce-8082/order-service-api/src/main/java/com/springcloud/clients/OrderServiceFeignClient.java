package com.springcloud.clients;

import com.springcloud.OrderService;
import com.springcloud.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * OrderServiceFeignClient
 * 2020-07-14
 **/

@FeignClient(value = "order-service",fallback = OrderServiceFeignClient.OrderServiceFeignClientFallback.class)
public interface OrderServiceFeignClient extends OrderService {
    @Component
    class OrderServiceFeignClientFallback implements OrderServiceFeignClient{

        @Override
        public String orders() {
            return "查询订单失败，请稍后重试！";
        }

        @Override
        public int insert(OrderDto dto) {
            return -1;
        }
    }
}
