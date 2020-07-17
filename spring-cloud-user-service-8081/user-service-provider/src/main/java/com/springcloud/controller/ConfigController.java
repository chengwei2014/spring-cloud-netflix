package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置文件前端控制中心
 * 2020-07-14
 */
@RefreshScope
@RestController
public class ConfigController {
    @Value("${env}")
    private String txt;

    @Value("${java.version}")
    private String javaVersion;

    @Autowired
    private Environment environment;

    @GetMapping("/config")
    public String config(){
        return this.txt;
    }

    @GetMapping("/getJavaVersion")
    public String getJavaVersion(){
        System.out.println("java version:" + environment.getProperty("java.version"));
        return this.javaVersion;
    }
}
