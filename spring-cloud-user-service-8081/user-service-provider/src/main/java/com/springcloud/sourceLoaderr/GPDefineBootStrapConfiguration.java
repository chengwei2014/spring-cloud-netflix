package com.springcloud.sourceLoaderr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * GPDefineBootStrapConfiguration
 * 2020-07-16
 */
@Configuration
public class GPDefineBootStrapConfiguration {
    @Bean
    public JsonProperySourceLocator jsonProperySourceLocator(){
        return new JsonProperySourceLocator();
    }
}
