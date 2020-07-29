package com.springcloud.orderserviceprovider.dto;

import com.springcloud.exception.ValidException;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * OrderDto
 * 2020-07-28
 */
@Data
public class OrderDto {
    @NotNull(message = "name不能为空")
    private String name;
    @NotNull(message = "tel不能为空")
    private String tel;
    private String userId;
    @NotEmpty(message = "商品列表不能为空")
    private List<ItemsDto> items;

    public void validData(BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            for (ObjectError oe:bindingResult.getAllErrors()){
                stringBuilder.append(oe.getDefaultMessage() + "\n");
            }
            throw new ValidException(stringBuilder.toString());
        }
    }
}
