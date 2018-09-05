package com.ayl.seckil.service;

import com.ayl.seckil.redis.RedisService;
import com.ayl.seckil.redis.keyprefix.SeckillGoodsPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author AYL    2018/9/4 23:09
 */
@Service
public class SeckillGoodsService {
    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderInfoService orderInfoService;

    @Transactional
    public Long doSeckill(Long goodsId, Long userId) {
        //1. 减库存
        Long stock = redisService.decr(SeckillGoodsPrefix.SECKILL_GOODS_STOCK_PREFIX, goodsId.toString());
        goodsService.subtractSeckillGoodsStock(goodsId);

        //2.生成订单
         Long orderId = orderInfoService.createOrder(goodsId, userId);
         return orderId;
    }
}
