package com.springcloud.orderserviceprovider.converter;

import com.springcloud.dto.ItemStockDto;
import com.springcloud.orderserviceprovider.dto.ItemsDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 订单转换
 * 2020-07-28
 */
@Mapper(componentModel = "spring")
public interface OrderConverter {
    ItemStockDto itemDtoStockDto(ItemsDto itemsDto);

    List<ItemStockDto> itemsDto2StockDtoList(List<ItemsDto> itemsDtos);
}
