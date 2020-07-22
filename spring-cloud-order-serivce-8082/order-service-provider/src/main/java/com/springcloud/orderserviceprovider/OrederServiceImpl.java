package com.springcloud.orderserviceprovider;

import com.springcloud.OrderService;
import com.springcloud.dto.OrderDto;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单调用前端控制中心
 * 2020-07-14
 */
@RestController
public class OrederServiceImpl implements OrderService {
    @Override
    public String orders() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Return All Orders";
    }

    @Override
    public int insert(OrderDto dto) {
        System.out.println("OrderDto ID：" + dto.getOrderId());
        return 1;
    }
}
