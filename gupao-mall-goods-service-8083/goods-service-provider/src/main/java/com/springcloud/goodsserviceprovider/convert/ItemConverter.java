package com.springcloud.goodsserviceprovider.convert;

import com.springcloud.dto.ItemStockDto;
import com.springcloud.goodsserviceprovider.domain.ItemStockDo;
import com.springcloud.goodsserviceprovider.mapper.entitys.TbItem;
import com.springcloud.vo.ItemDetailVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 商品转换
 * 2020-07-28
 */
@Mapper(componentModel = "spring")
public interface ItemConverter {
    @Mappings({})
    ItemDetailVo tbItemDetail2Vo(TbItem tbItem);

    List<ItemDetailVo> itemDetail2VoList(List<TbItem> tbItemList);

    @Mappings({})
    ItemStockDo itemStockDto2Do(ItemStockDto itemStockDto);

    List<ItemStockDo> itemStockDto2DoList(List<ItemStockDto> itemStockDtos);
}
