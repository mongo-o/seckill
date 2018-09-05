package com.ayl.seckil.service;

import com.ayl.seckil.cookie.CookieUtil;
import com.ayl.seckil.cookie.LoginCookie;
import com.ayl.seckil.dao.SeckillUserDao;
import com.ayl.seckil.domain.SeckillUser;
import com.ayl.seckil.exception.GlobalException;
import com.ayl.seckil.redis.RedisService;
import com.ayl.seckil.redis.keyprefix.UserPrefix;
import com.ayl.seckil.result.CodeMsg;
import com.ayl.seckil.result.Result;
import com.ayl.seckil.util.Md5Util;
import com.ayl.seckil.vo.UserLoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author AYL    2018/8/15 12:53
 */
@Service
public class SeckillUserService {
    @Autowired
    private SeckillUserDao seckillUserDao;

    @Autowired
    private RedisService redisService;

    public Result<String> doLogin(HttpServletResponse response, UserLoginVo userLoginVo) {
        Long phone = Long.valueOf(userLoginVo.getPhone());
        SeckillUser user = seckillUserDao.getUserByPhone(phone);
        if (user != null) {
            String formPassword = userLoginVo.getFormPassword();
            String dbPassword = Md5Util.formPasswordToDbPassword(formPassword, user.getSalt());
            boolean result = dbPassword.equals(user.getPassword());
            if(result) {
                //将登陆信息存储到redis
                String token = storeLoginUserIntoRedis(user);

                //登陆token通过cookie返回
                CookieUtil.setCookie(response, LoginCookie.tokenCookie, token);

                return Result.success(token);
            } else {
                throw new GlobalException(CodeMsg.USER_PASSWORD_DISMATCHED);
            }
        } else {
            throw new GlobalException(CodeMsg.USER_NOT_EXIST);
        }
    }


    public SeckillUser getLoginUser(HttpServletRequest request, HttpServletResponse response) {
        String cookieToken = CookieUtil.getCookie(request, LoginCookie.tokenCookie.getCookieName());

        String pathToken = request.getParameter(LoginCookie.tokenCookie.getCookieName());

        String token;
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(pathToken)) {
            return null;
        }
        token = StringUtils.isEmpty(cookieToken) ? pathToken : cookieToken;

        return (SeckillUser) redisService.get(UserPrefix.USER_TOKEN, token, SeckillUser.class);
    }

    private String storeLoginUserIntoRedis(SeckillUser user) {
        String token = "mongo"; //UUID.randomUUID().toString().replace("-", "");
        redisService.set(UserPrefix.USER_TOKEN, token, user);
        return token;
    }

}
