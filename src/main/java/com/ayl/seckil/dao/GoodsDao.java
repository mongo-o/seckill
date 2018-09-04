package com.ayl.seckil.dao;

import com.ayl.seckil.vo.SeckillGoodsDetailVo;
import com.ayl.seckil.vo.SeckillGoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author AYL    2018/9/3 23:39
 */
@Mapper
@Component
public interface GoodsDao {

    @Select("select sg.goods_id, g.goods_name, g.goods_title, g.goods_img, g.goods_price, sg.seckill_price " +
            " from seckill_goods sg inner join goods g on sg.goods_id = g.id;")
    List<SeckillGoodsVo> listSeckillGoods();


    @Select("select sg.goods_id, g.goods_name, g.goods_title, g.goods_img, g.goods_detail, sg.stock_count, sg.start_date, sg.end_date, g.goods_price, sg.seckill_price " +
            " from seckill_goods sg inner join goods g on sg.goods_id = g.id" +
            " where g.id = #{goodsId}")
    SeckillGoodsDetailVo getSeckillGoodsDetail(@Param("goodsId") Long goodsId);
}
