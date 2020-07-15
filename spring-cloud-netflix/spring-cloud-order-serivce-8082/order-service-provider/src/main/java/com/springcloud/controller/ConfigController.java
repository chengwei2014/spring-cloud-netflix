package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置文件前端控制中心
 * 2020-07-14
 */
@RefreshScope
@RestController
public class ConfigController {
    @Value("${hello}")
    private String txt;

    @GetMapping("/config")
    public String config(){
        return this.txt;
    }
}
