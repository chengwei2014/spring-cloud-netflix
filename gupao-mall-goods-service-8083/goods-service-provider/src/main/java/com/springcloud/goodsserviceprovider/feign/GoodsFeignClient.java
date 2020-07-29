package com.springcloud.goodsserviceprovider.feign;

import com.springcloud.api.R;
import com.springcloud.clients.IGoodsFeignClient;
import com.springcloud.dto.ItemStockDto;
import com.springcloud.goodsserviceprovider.convert.ItemConverter;
import com.springcloud.goodsserviceprovider.domain.ItemStockDo;
import com.springcloud.goodsserviceprovider.mapper.entitys.TbItem;
import com.springcloud.goodsserviceprovider.service.IItemService;
import com.springcloud.vo.ItemDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * GoodsFeignClient
 * 2020-07-28
 */
@Slf4j
@RestController
public class GoodsFeignClient implements IGoodsFeignClient {
    @Autowired
    private IItemService itemService;
    @Autowired
    private ItemConverter itemConverter;

    @Override
    public R decreaseStock(List<ItemStockDto> itemStockDtos) {
        log.info("begin GoodsFeignClient.decreaseStock:" + itemStockDtos);
        List<ItemStockDo> itemStockDos = itemConverter.itemStockDto2DoList(itemStockDtos);
        boolean rs = itemService.decreaseStock(itemStockDos);
        return new R.Builder<>().buildOk();
    }

    @Override
    public R<List<ItemDetailVo>> getItemByIds(List<Long> ids) {
        log.info("begin GoodsFeignClient.getItemByIds:" + ids);
        List<TbItem> itemList = itemService.findItemsByIds(ids);
        List<ItemDetailVo> itemDetailVos = itemConverter.itemDetail2VoList(itemList);
        return new R.Builder<List<ItemDetailVo>>().setData(itemDetailVos).buildOk();
    }
}
