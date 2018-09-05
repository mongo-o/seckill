package com.ayl.seckil.dao;

import com.ayl.seckil.domain.OrderInfo;
import com.ayl.seckil.domain.SeckillOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Component;

/**
 * @author AYL    2018/9/5 0:25
 */
@Component
@Mapper
public interface OrderDao {


    @Insert("insert into order_info(user_id, goods_id, delivery_addr_id, goods_name, goods_count, goods_price, " +
            "order_channel, status, create_date, pay_date, gmt_modified)" +
            "values(#{orderInfo.userId},#{orderInfo.goodsId},#{orderInfo.deliveryAddrId},#{orderInfo.goodsName}," +
            "#{orderInfo.goodsCount},#{orderInfo.goodsPrice},#{orderInfo.orderChannel},#{orderInfo.status},#{orderInfo.createDate},#{orderInfo.payDate},#{orderInfo.gmtModified})")
    @SelectKey(keyColumn="id", keyProperty="orderInfo.id", resultType=long.class, before=false, statement="select last_insert_id()")
    long insertOrder(@Param("orderInfo") OrderInfo orderInfo);

    @Insert({"insert into seckill_order(user_id, order_id, goods_id)" +
            " values(#{seckillOrder.userId},#{seckillOrder.orderId},#{seckillOrder.goodsId})"})
    int insertSeckillOrder(@Param("seckillOrder") SeckillOrder seckillOrder);
}
