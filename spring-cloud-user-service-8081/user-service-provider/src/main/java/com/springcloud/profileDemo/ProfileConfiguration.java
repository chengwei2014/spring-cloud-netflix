package com.springcloud.profileDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 自定义profile配置
 * 2020-07-16
 */
@Configuration
public class ProfileConfiguration {
    @Profile("dev")
    @Bean
    public ProfileService profileServiceDev(){
        return new ProfileService("dev");
    }

    @Profile("prd")
    @Bean
    public ProfileService profileServicePrd(){
        return new ProfileService("prd");
    }
}
