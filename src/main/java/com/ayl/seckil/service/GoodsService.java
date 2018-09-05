package com.ayl.seckil.service;

import com.ayl.seckil.dao.GoodsDao;
import com.ayl.seckil.vo.SeckillGoodsDetailVo;
import com.ayl.seckil.vo.SeckillGoodsStock;
import com.ayl.seckil.vo.SeckillGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AYL    2018/9/4 11:32
 */
@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;

    public List<SeckillGoodsVo> listSeckillGoods() {
        List<SeckillGoodsVo> list =goodsDao.listSeckillGoods();
        return list;
    }


    public SeckillGoodsDetailVo getSeckillGoodsDetail(Long goodsId) {
        SeckillGoodsDetailVo goodsDetailVo = goodsDao.getSeckillGoodsDetail(goodsId);
        return goodsDetailVo;
    }

    public List<SeckillGoodsDetailVo> listSeckillGoodsDetail() {
        return goodsDao.listSeckillGoodsDetail();
    }

    public int subtractSeckillGoodsStock(Long goodsId) {
        return goodsDao.subtractSeckillGoodsStock(goodsId);
    }

}
