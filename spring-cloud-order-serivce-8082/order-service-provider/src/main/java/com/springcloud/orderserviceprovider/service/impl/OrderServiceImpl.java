package com.springcloud.orderserviceprovider.service.impl;

import com.springcloud.api.R;
import com.springcloud.clients.IGoodsFeignClient;
import com.springcloud.exception.BizException;
import com.springcloud.orderserviceprovider.converter.OrderConverter;
import com.springcloud.orderserviceprovider.dto.ItemsDto;
import com.springcloud.orderserviceprovider.dto.OrderDto;
import com.springcloud.orderserviceprovider.mapper.entitys.TbOrder;
import com.springcloud.orderserviceprovider.mapper.entitys.TbOrderItem;
import com.springcloud.orderserviceprovider.mapper.persistence.TbOrderItemMapper;
import com.springcloud.orderserviceprovider.mapper.persistence.TbOrderMapper;
import com.springcloud.orderserviceprovider.service.IOrderService;
import com.springcloud.vo.ItemDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 订单业务处理
 * 2020-07-28
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IGoodsFeignClient goodsFeignClient;
    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;
    @Autowired
    private OrderConverter orderConverter;

    @Transactional
    @Override
    public String createOrder(OrderDto orderDto) {
        /*
        * 1.锁库存
        * 2.查询商品信息
        * 3.创建订单
        * */
        R r = goodsFeignClient.decreaseStock(orderConverter.itemsDto2StockDtoList(orderDto.getItems()));
        if (r.getCode() != 200){
            throw new BizException(r.getMsg());
        }
        List<Long> ids = orderDto.getItems().stream().map(dto -> Long.parseLong(dto.getItemId())).collect(Collectors.toList());
        R<List<ItemDetailVo>> listR = goodsFeignClient.getItemByIds(ids);
        BigDecimal totalPrice = new BigDecimal(0);
        String orderId = UUID.randomUUID().toString().replace("-", "");
        for (ItemsDto itemsDto:orderDto.getItems()){
            for (ItemDetailVo itemDetailVo:listR.getData()){
                if (itemDetailVo.getId().toString().equals(itemsDto.getItemId())){
                    BigDecimal totalFee = itemDetailVo.getPrice().multiply(BigDecimal.valueOf(itemsDto.getNum()));
                    totalPrice = totalPrice.add(totalFee);
                    TbOrderItem tbOrderItem = new TbOrderItem();
                    tbOrderItem.setItemId(itemDetailVo.getId());
                    tbOrderItem.setNum(itemDetailVo.getNum());
                    tbOrderItem.setCreateTime(new Date());
                    tbOrderItem.setOrderId(orderId);
                    tbOrderItem.setPicPath(itemDetailVo.getImage());
                    tbOrderItem.setPrice(itemDetailVo.getPrice());
                    tbOrderItem.setTotalFee(totalFee);
                    tbOrderItem.setStatus(1);
                    tbOrderItem.setTitle(itemDetailVo.getTitle());
                    tbOrderItem.setId(UUID.randomUUID().toString().replace("-", ""));
                    tbOrderItemMapper.insert(tbOrderItem);
                }
            }
        }
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderId(orderId);
        tbOrder.setPayment(totalPrice);
        tbOrder.setPaymentTime(new Date());
        tbOrder.setStatus(0);
        tbOrder.setCreateTime(new Date());
        tbOrder.setUpdateTime(new Date());
        tbOrder.setUserId(1000000l);
        tbOrder.setOrderId(orderId);
        tbOrderMapper.insert(tbOrder);
        return orderId;
    }
}
