package com.ayl.seckil.dao;

import com.ayl.seckil.domain.SeckillUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author AYL    2018/8/15 1:04
 */
@Mapper
@Component
public interface SeckillUserDao {

    @Select("select id,phone,nickname,password,salt,head_img,register_date,last_login_date,login_count from seckill_user where seckill_user.phone = #{phone}")
    SeckillUser getUserByPhone(@Param("phone") Long phone);

    @Insert("insert into seckill_user(phone,nickname,password,salt,head_img,register_date) " +
            "values(#{user.phone},#{user.nickName},#{user.password},#{user.salt},#{user.headImg}, Now());")
    int insertUser(@Param("user") SeckillUser seckillUser);
}
