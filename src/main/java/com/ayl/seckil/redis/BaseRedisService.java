package com.ayl.seckil.redis;

import com.alibaba.fastjson.JSON;
import com.ayl.seckil.util.FastJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

/**
 * @author AYL    2018/8/25 0:31
 */
@Component
public class BaseRedisService {
    @Autowired
    JedisCluster jedisCluster;

    public void set(BasePrefix prefix, String key, Object value) {

        String val = JSON.toJSONString(value);
        key = getKey(prefix.getPrefixKey(), key);
        jedisCluster.set(key, val);

        if (prefix.getExpireSec() > 0) {
            jedisCluster.expire(key, prefix.getExpireSec());
        }
    }

    public Object get(BasePrefix prefix, String key, Class clazz) {
        key = getKey(prefix.getPrefixKey(), key);
        String value = jedisCluster.get(key);
        return FastJsonUtil.jsongToObject(value,clazz);
    }

    private String getKey(String prefix, String key) {
        return prefix + key;
    }
}
