package com.springcloud.springcloudgateway8080;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.springcloud")
@SpringBootApplication
public class SpringCloudGateway8080Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGateway8080Application.class, args);
    }

}
