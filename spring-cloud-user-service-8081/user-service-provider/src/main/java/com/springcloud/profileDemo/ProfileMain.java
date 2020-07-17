package com.springcloud.profileDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 自定义profile加载测试
 * 2020-07-16
 */
public class ProfileMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");
        context.register(ProfileConfiguration.class);
        context.refresh();
        System.out.println(context.getBean(ProfileService.class));
    }
}
