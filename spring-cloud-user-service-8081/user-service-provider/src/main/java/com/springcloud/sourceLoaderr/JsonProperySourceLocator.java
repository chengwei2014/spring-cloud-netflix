package com.springcloud.sourceLoaderr;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * JsonProperySourceLocator
 * springboot中提供的一个属性文件的扩展接口(spring boot启动的过程中会触发调用）
 * 2020-07-16
 */
public class JsonProperySourceLocator implements PropertySourceLocator {
    private final static String DEFAULT_LOCATION = "classpath:gupao.json";
    private final ResourceLoader resourceLoader = new DefaultResourceLoader(getClass().getClassLoader());

    @Override
    public PropertySource<?> locate(Environment environment) {

        return null;
    }

    private Map<String,Object> mapProperySource(){
        Resource resource = this.resourceLoader.getResource(DEFAULT_LOCATION);
        if (null == resource){
            return null;
        }

        Map<String,Object> result = new HashMap<>();
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> fileMap = jsonParser.parseMap(readFile(resource));
        processorMap("", result, fileMap);
        return null;
    }

    private void processorMap(String prefix,Map<String,Object>result,Map<String,Object>fileMap){
        if (prefix.length() > 0){
            prefix+=".";
        }

        for (Map.Entry<String,Object> entrySet:fileMap.entrySet()){
            if (entrySet.getValue() instanceof Map){
                processorMap(prefix + entrySet.getKey(), result, (Map<String, Object>) entrySet.getValue());
            }else {
                result.put(prefix + entrySet.getKey(), entrySet.getValue());
            }
        }
    }

    /**
     * JSON格式
     * {
     *     {
     *         {
     *
     *         }
     *     }
     * }
     * @param resource
     * @return
     */
    private String readFile(Resource resource) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(resource.getFile());
            byte[] readByte = new byte[(int) resource.getFile().length()];
            fileInputStream.read(readByte);
            return new String(readByte, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != fileInputStream){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public Collection<PropertySource<?>> locateCollection(Environment environment) {
        return null;
    }
}
