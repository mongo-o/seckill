package com.ayl.seckil.service;

import com.ayl.seckil.dao.GoodsDao;
import com.ayl.seckil.vo.SeckillGoodsDetailVo;
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

    List<SeckillGoodsVo> listSeckillGoods() {
        return goodsDao.listSeckillGoods();
    }


    SeckillGoodsDetailVo getSeckillGoodsDetail(Long goodsId) {
        return goodsDao.getSeckillGoodsDetail(goodsId);
    }

}
