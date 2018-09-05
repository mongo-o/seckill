package com.ayl.seckil.redis.keyprefix;

/**
 * @author AYL    2018/9/5 0:55
 */
public class SeckillOrderPrefix extends BasePrefix {

    public SeckillOrderPrefix(String prefixKey) {
        super(prefixKey);
    }

    public static final SeckillOrderPrefix SECKILL_ORDER = new SeckillOrderPrefix("s_o");
}
