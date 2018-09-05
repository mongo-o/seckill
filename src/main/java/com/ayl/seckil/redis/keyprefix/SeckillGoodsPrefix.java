package com.ayl.seckil.redis.keyprefix;

/**
 * @author AYL    2018/9/4 22:31
 */

public class SeckillGoodsPrefix extends BasePrefix {
    public SeckillGoodsPrefix(String prefixKey) {
        super(prefixKey);
    }

    public SeckillGoodsPrefix(int expireSec, String prefixKey) {
        super(expireSec, prefixKey);
    }

    public static final SeckillGoodsPrefix SECKILL_GOODS_STOCK_PREFIX = new SeckillGoodsPrefix("g_s");
    public static final SeckillGoodsPrefix SECKILL_GOODS_DETAIL_PREFIX = new SeckillGoodsPrefix("g_s_d");
}
