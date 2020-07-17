package com.springcloud.userserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;

@ComponentScan("com.springcloud")
@SpringBootApplication
public class UserServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceProviderApplication.class, args);
        /*
        * springboot中defaultProperties使用
        * */
//        SpringApplication springApplication = new SpringApplication(UserServiceProviderApplication.class);
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("key", "value");
//        springApplication.setDefaultProperties(map);
//        springApplication.run(args);


    }

}
