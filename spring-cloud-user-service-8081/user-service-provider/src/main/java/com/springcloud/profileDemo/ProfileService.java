package com.springcloud.profileDemo;

/**
 * 自定义profile实体
 * 2020-07-16
 */
public class ProfileService {
    private String profile;

    public ProfileService(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "ProfileService{" +
                "profile='" + profile + '\'' +
                '}';
    }
}
