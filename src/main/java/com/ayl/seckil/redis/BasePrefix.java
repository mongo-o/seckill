package com.ayl.seckil.redis;

import lombok.Getter;

/**
 * @author AYL    2018/8/25 0:27
 */
@Getter
public class BasePrefix {
    /**
     * 如歌expiresce为0或负数，则表示永不过期
     */
    private int expireSec;
    private String prefixKey;

    public BasePrefix(String prefixKey) {
        expireSec = 0;
        this.prefixKey = prefixKey;
    }

    public BasePrefix(int expireSec, String prefixKey) {
        this.expireSec = expireSec;
        this.prefixKey = prefixKey;
    }
}
