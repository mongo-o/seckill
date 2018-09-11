package com.ayl.seckil.service;

import com.ayl.seckil.redis.RedisService;
import com.ayl.seckil.redis.keyprefix.SeckillGoodsPrefix;
import com.ayl.seckil.result.CodeMsg;
import com.ayl.seckil.result.Result;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.LogManager;

/**
 * @author AYL    2018/9/4 23:09
 */
@Service
public class SeckillGoodsService {
    private static Logger logger = org.apache.logging.log4j.LogManager.getLogger(SeckillGoodsService.class);

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderInfoService orderInfoService;

    @Transactional
    public Result<Long> doSeckill(Long goodsId, Long userId) {
        //1. 减库存
        Long stock = redisService.decr(SeckillGoodsPrefix.SECKILL_GOODS_STOCK_PREFIX, goodsId.toString());
        int subStock = goodsService.subtractSeckillGoodsStock(goodsId);
        if (subStock == 1) {
            //2.生成订单
            Long orderId = orderInfoService.createOrder(goodsId, userId);
            logger.debug("秒杀成功。userid:{},orderid:{}", userId, orderId);
            return Result.success(orderId);
        } else {
            logger.debug("秒杀失败，商品抢光了。userid:{}", userId);
            return Result.error(CodeMsg.SECKILL_OUT_OF_STOCK);
        }
    }
}
