package com.ayl.seckil.redis;

import com.alibaba.fastjson.JSON;
import com.ayl.seckil.redis.keyprefix.BasePrefix;
import com.ayl.seckil.util.FastJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

/**
 * @author AYL    2018/8/25 0:31
 */
@Component
public class RedisService {
    @Autowired
    JedisCluster jedisCluster;

    public String set(BasePrefix prefix, String key, Object value) {

        String val = JSON.toJSONString(value);
        key = getKey(prefix.getPrefixKey(), key);
        jedisCluster.set(key, val);

        if (prefix.getExpireSec() > 0) {
            jedisCluster.expire(key, prefix.getExpireSec());
        }
        return key;
    }

    public Object get(BasePrefix prefix, String key, Class clazz) {
        key = getKey(prefix.getPrefixKey(), key);
        String value = jedisCluster.get(key);
        return FastJsonUtil.jsongToObject(value,clazz);
    }

    public Long decr(BasePrefix prefix, String key) {
        key = getKey(prefix.getPrefixKey(), key);
        Long value = jedisCluster.decr(key);
        return value;
    }



    private String getKey(String prefix, String key) {
        return prefix + key;
    }
}
