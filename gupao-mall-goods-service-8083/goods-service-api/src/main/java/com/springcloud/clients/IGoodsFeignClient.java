package com.springcloud.clients;

import com.springcloud.api.R;
import com.springcloud.dto.ItemStockDto;
import com.springcloud.vo.ItemDetailVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * IGoodsFeignClient
 * 2020-07-28
 */
@FeignClient(value = "goods-service")
public interface IGoodsFeignClient {
    /**
     * 锁定并扣减库存
     * @Param  商品id
     * @Param 库存数量
     * */
    @PutMapping(value = "/item/stock",consumes = MediaType.APPLICATION_JSON_VALUE)
    R decreaseStock(@RequestBody List<ItemStockDto> itemStockDtos);

    /**
     * 根据商品id查询商品列表
     * */
    @GetMapping("/item/{ids}")
    R<List<ItemDetailVo>> getItemByIds(@PathVariable("ids")List<Long> ids);
}
