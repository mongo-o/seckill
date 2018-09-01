package com.ayl.seckil.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author AYL    2018/8/25 20:23
 */
@Component
public class RedisClusterConfig {
    @Autowired
    RedisConfig redisConfig;

    @Bean
    public JedisCluster getJedisCluster() {
        String hostNodesStr = redisConfig.getClusterNodes();
        String[] hostNodes = hostNodesStr.split(",");

        Set<HostAndPort> hostAndPortSet = new HashSet<>();
        for (int i = 0; i < hostNodes.length; i++) {
            String[] hostNode = hostNodes[i].split(":");
            HostAndPort hostAndPort = new HostAndPort(hostNode[0], Integer.valueOf(hostNode[1]));
            hostAndPortSet.add(hostAndPort);
        }

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxWaitMillis(redisConfig.getJedis().getPoolMaxWait() * 1000);
        jedisPoolConfig.setMaxIdle(redisConfig.getJedis().getPoolMaxIdle());
        jedisPoolConfig.setMinIdle(redisConfig.getJedis().getPoolMinIdle());
        jedisPoolConfig.setMaxTotal(redisConfig.getJedis().getPoolMaxActive());

        JedisCluster jedisCluster = new JedisCluster(hostAndPortSet, jedisPoolConfig);
        return jedisCluster;
    }
}
