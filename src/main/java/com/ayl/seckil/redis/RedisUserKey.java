package com.ayl.seckil.redis;

/**
 * @author AYL    2018/8/25 0:29
 */
public class RedisUserKey extends BasePrefix {
    public static final int USER_TOKEN_EXPIRE_SECONDS = 2 * 24 * 3600;

    public RedisUserKey(int expiredSecs, String prefixKey) {
        super(expiredSecs, prefixKey);
    }

    public static final RedisUserKey USER_TOKEN = new RedisUserKey(USER_TOKEN_EXPIRE_SECONDS,"u_t");
}

