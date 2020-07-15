package com.springcloud.clients;

import com.springcloud.OrderService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * OrderServiceFeignClient
 * 2020-07-14
 **/

@FeignClient("order-service")
public interface OrderServiceFeignClient extends OrderService {

}
