package com.ayl.seckil.redis;

/**
 * @author AYL    2018/8/25 0:29
 */
public class RedisUserKey extends BasePrefix {

    public RedisUserKey(String prefixKey) {
        super(prefixKey);
    }

    public static final RedisUserKey USER_TOKEN = new RedisUserKey("u_t");

    public static final RedisUserKey TEST_TOKEN = new RedisUserKey("a_");
}

