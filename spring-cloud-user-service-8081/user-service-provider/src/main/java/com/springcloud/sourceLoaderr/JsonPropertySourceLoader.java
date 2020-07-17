package com.springcloud.sourceLoaderr;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

/**
 * JsonPropertySourceLoader
 * 2020-07-16
 */
public class JsonPropertySourceLoader implements PropertySourceLoader {
    @Override
    public String[] getFileExtensions() {
        return new String[]{"txt"};
    }

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        //拿到本地文件
        //解析文件
        //保存PropertySource
//        return new GpDefineJsonProperySource();
        return null;
    }
}
