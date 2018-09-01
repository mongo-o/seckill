package com.ayl.seckil.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author AYL    2018/8/25 18:35
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {
    private Integer clusterMaxRedirects;
    private String clusterNodes;
    private final Jedis jedis = new Jedis();

    @Getter
    @Setter
    public static class Jedis {
        private Integer poolMaxActive;
        private Long poolMaxWait;
        private Integer poolMaxIdle;
        private Integer poolMinIdle;
    }
}
