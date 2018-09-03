package com.ayl.seckil.dao;

import com.ayl.seckil.domain.SeckillUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author AYL    2018/8/15 15:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillUserDaoTest {
    @Autowired
    SeckillUserDao seckillUserDao;

    @Test
    public void testGetUserByPhone(){
        SeckillUser user = seckillUserDao.getUserByPhone(13510768061L);
        Assert.assertTrue(user.getNickName().equals("mongo"));
    }
}
