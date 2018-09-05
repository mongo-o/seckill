package com.ayl.seckil.redis.keyprefix;

/**
 * @author AYL    2018/8/25 0:29
 */
public class UserPrefix extends BasePrefix {
    public static final int USER_TOKEN_EXPIRE_SECONDS = 2 * 24 * 3600;

    public UserPrefix(int expiredSecs, String prefixKey) {
        super(expiredSecs, prefixKey);
    }

    public static final UserPrefix USER_TOKEN = new UserPrefix(USER_TOKEN_EXPIRE_SECONDS,"u_t");
}

