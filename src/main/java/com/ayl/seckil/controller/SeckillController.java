package com.ayl.seckil.controller;

import com.ayl.seckil.domain.SeckillUser;
import com.ayl.seckil.redis.RedisService;
import com.ayl.seckil.redis.keyprefix.SeckillGoodsPrefix;
import com.ayl.seckil.redis.keyprefix.SeckillOrderPrefix;
import com.ayl.seckil.result.CodeMsg;
import com.ayl.seckil.result.Result;
import com.ayl.seckil.service.GoodsService;
import com.ayl.seckil.service.SeckillGoodsService;
import com.ayl.seckil.vo.SeckillGoodsDetailVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


/**
 * @author AYL    2018/9/4 21:19
 */
@Controller
@ResponseBody
@RequestMapping("seckill")
public class SeckillController implements InitializingBean {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("====afterPropertiesSet======");
        List<SeckillGoodsDetailVo> seckillGoodsDetailVos = goodsService.listSeckillGoodsDetail();
        if (seckillGoodsDetailVos != null && seckillGoodsDetailVos.size() > 0) {
            for(SeckillGoodsDetailVo seckillGoodsDetailVo : seckillGoodsDetailVos) {
                redisService.set(SeckillGoodsPrefix.SECKILL_GOODS_STOCK_PREFIX,
                                 String.valueOf(seckillGoodsDetailVo.getGoodsId()),
                                 seckillGoodsDetailVo.getStockCount());
                redisService.set(SeckillGoodsPrefix.SECKILL_GOODS_DETAIL_PREFIX,
                        seckillGoodsDetailVo.getGoodsId().toString(),
                        seckillGoodsDetailVo);

            }
        }
    }

    @RequestMapping(path = "do_seckill/{goodsId}", method = RequestMethod.GET)
    public Result doSeckill(@PathVariable("goodsId") Long goodsId, SeckillUser seckillUser) {
        //查库存
        Integer seckillGoodsStock = (Integer) redisService.get(SeckillGoodsPrefix.SECKILL_GOODS_STOCK_PREFIX,
                goodsId.toString(), Integer.class);

        if (seckillGoodsStock < 1) {
            return Result.error(CodeMsg.SECKILL_OUT_OF_STOCK);
        }

        //当前时间是否在活动时间范围内
        SeckillGoodsDetailVo seckillGoodsDetailVo = (SeckillGoodsDetailVo)redisService.get(SeckillGoodsPrefix.SECKILL_GOODS_DETAIL_PREFIX,
                goodsId.toString(), SeckillGoodsDetailVo.class);
        Date now = new Date();
        if (now.getTime() - seckillGoodsDetailVo.getStartDate().getTime() < 0) {
            return Result.error(CodeMsg.SECKILL_UN_START);
        }
        if (seckillGoodsDetailVo.getEndDate().getTime() - now.getTime() < 0) {
            return Result.error(CodeMsg.SECKILL_IS_OVER);
        }

        //是否重复秒杀
        SeckillUser redisSeckillUser = (SeckillUser)redisService.get(SeckillOrderPrefix.SECKILL_ORDER,
                goodsId.toString()+ "_" + seckillUser.getId(),
                SeckillUser.class);
        if (redisSeckillUser != null) {
            return Result.error(CodeMsg.SECKILL_REPEATED);
        }

        //秒杀（减库存，生成订单）
        seckillGoodsService.doSeckill(goodsId, seckillUser.getId());
        return Result.success("success");
    }

}
