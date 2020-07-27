package com.springcloud.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义过滤器--GpDefineGatewayFilterFactory
 * 2020-07-27
 */
@Component
public class GpDefineGatewayFilterFactory extends AbstractGatewayFilterFactory<GpDefineGatewayFilterFactory.GpConfig> {
    private static final String NAME_KEY = "name";
    private Logger logger = LoggerFactory.getLogger(GpDefineGatewayFilterFactory.class);

    public GpDefineGatewayFilterFactory() {
        super(GpConfig.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(NAME_KEY);
    }

    @Override
    public GatewayFilter apply(GpConfig config) {
        return ((exchange,chain)->{
            logger.info("[pre] Filter Request, name:" + config.getName());
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                logger.info("[post]: Response Filter!");
            }));
        });
    }

    public static class GpConfig{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
