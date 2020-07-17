package com.springcloud.dto;

/**
 * OrderDto
 * 2020-07-14
 **/
public class OrderDto {
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
