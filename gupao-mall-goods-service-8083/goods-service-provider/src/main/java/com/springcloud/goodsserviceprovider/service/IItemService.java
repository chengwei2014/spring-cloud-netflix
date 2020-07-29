package com.springcloud.goodsserviceprovider.service;

import com.springcloud.goodsserviceprovider.domain.ItemStockDo;
import com.springcloud.goodsserviceprovider.mapper.entitys.TbItem;

import java.util.List;

/**
 * 商品业务处理
 * 2020-07-28
 */
public interface IItemService {
    List<TbItem> findItemsByIds(List<Long> ids);

    boolean decreaseStock(List<ItemStockDo> itemStockDos);
}
