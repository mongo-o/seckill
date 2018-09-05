package com.ayl.seckil.service;

import com.ayl.seckil.dao.OrderDao;
import com.ayl.seckil.domain.OrderInfo;
import com.ayl.seckil.domain.SeckillOrder;
import com.ayl.seckil.redis.RedisService;
import com.ayl.seckil.redis.keyprefix.SeckillOrderPrefix;
import com.ayl.seckil.vo.SeckillGoodsDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author AYL    2018/9/5 0:46
 */
@Service
public class OrderInfoService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RedisService redisService;

    public Long createOrder(Long goodsId, Long userId) {
        SeckillGoodsDetailVo goodsDetailVo = goodsService.getSeckillGoodsDetail(goodsId);

        //生成订单
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(1L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsId);
        orderInfo.setGoodsName(goodsDetailVo.getGoodsName());
        orderInfo.setGoodsPrice(goodsDetailVo.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(1);
        orderInfo.setUserId(userId);
        orderDao.insertOrder(orderInfo);

        //生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goodsId);
        seckillOrder.setOrderId(orderInfo.getId());
        seckillOrder.setUserId(userId);
        orderDao.insertSeckillOrder(seckillOrder);

        //保存秒杀订单信息到redis
        redisService.set(SeckillOrderPrefix.SECKILL_ORDER, goodsId.toString()+ "_" + userId, seckillOrder);
        return orderInfo.getId();
    }
}
