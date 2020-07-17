package com.springcloud.sourceLoaderr;

import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * GpDefineJsonProperySource
 * 2020-07-16
 */
public class GpDefineJsonProperySource extends EnumerablePropertySource<Map<String, Object>> {
    public GpDefineJsonProperySource(String name, Map<String, Object> source) {
        super(name, source);
    }

    @Override
    public String[] getPropertyNames() {
        return StringUtils.toStringArray(this.source.keySet());
    }

    @Override
    public Object getProperty(String s) {
        return this.source.get(s);
    }
}
