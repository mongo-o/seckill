package com.ayl.seckil.service;

import com.ayl.seckil.dao.SeckillUserDao;
import com.ayl.seckil.domain.SeckillUser;
import com.ayl.seckil.exception.GlobalException;
import com.ayl.seckil.redis.BaseRedisService;
import com.ayl.seckil.redis.RedisUserKey;
import com.ayl.seckil.result.CodeMsg;
import com.ayl.seckil.result.Result;
import com.ayl.seckil.util.Md5Util;
import com.ayl.seckil.vo.UserLoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author AYL    2018/8/15 12:53
 */
@Service
public class SeckillUserService {
    @Autowired
    private SeckillUserDao seckillUserDao;

    @Autowired
    private BaseRedisService redisService;

    public Result doLogin(UserLoginVo userLoginVo) {
        Long phone = Long.valueOf(userLoginVo.getPhone());
        SeckillUser user = seckillUserDao.getUserByPhone(phone);
        if (user != null) {
            String formPassword = userLoginVo.getFormPassword();
            String dbPassword = Md5Util.formPasswordToDbPassword(formPassword, user.getSalt());
            boolean result = dbPassword.equals(user.getPassword());
            if(result) {
                String token = storeLoginUserIntoRedis(userLoginVo);
                return Result.success(token);
            } else {
                throw new GlobalException(CodeMsg.USER_PASSWORD_DISMATCHED);
            }
        } else {
            throw new GlobalException(CodeMsg.USER_NOT_EXIST);
        }
    }

    private String storeLoginUserIntoRedis(UserLoginVo userLoginVo) {
        String token = "mongo"; //UUID.randomUUID().toString().replace("-", "");
        token = redisService.set(RedisUserKey.USER_TOKEN, token, userLoginVo);
        return token;
    }

}
