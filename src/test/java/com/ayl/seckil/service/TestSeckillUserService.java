package com.ayl.seckil.service;

import com.ayl.seckil.result.CodeMsg;
import com.ayl.seckil.result.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author AYL    2018/9/3 0:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSeckillUserService {
    @Autowired
    SeckillUserService seckillUserService;

    @Test
    public void testVerifyPassword() {
        String formpassword = "f12861d57082db6ee4d5b4eb9eaf7bd9";
//        Result result = seckillUserService.verifyPassword(13510768061L, formpassword);
//        Assert.assertTrue(result.getCodeMsg() == CodeMsg.SUCCESS);
//
//        result = seckillUserService.verifyPassword(445L, formpassword);
//        Assert.assertTrue(result.getCodeMsg() == CodeMsg.USER_NOT_EXIST);
//
//        result = seckillUserService.verifyPassword(13510768061L, "kijonoijf");
//        Assert.assertTrue(result.getCodeMsg() == CodeMsg.USER_PASSWORD_DISMATCHED);
    }



}
