package com.ayl.seckil.dao;

import com.ayl.seckil.domain.SeckillUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author AYL    2018/8/15 1:04
 */
@Mapper
@Component
public interface SeckillUserDao {

    @Select("select * from seckill_user where seckill_user.phone = #{phone}")
    SeckillUser getUserByPhone(@Param("phone") Long phone);
}
