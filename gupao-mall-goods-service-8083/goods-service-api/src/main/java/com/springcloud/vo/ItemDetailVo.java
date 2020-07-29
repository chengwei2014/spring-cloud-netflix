package com.springcloud.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * ItemDetailVo
 * 2020-07-28
 */
@Data
public class ItemDetailVo {
    private Long id;
    private String title;
    private BigDecimal price;
    private Integer num;
    private String image;
    private Long cid;
}
