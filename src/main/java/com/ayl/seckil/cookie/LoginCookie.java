package com.ayl.seckil.cookie;

import com.ayl.seckil.redis.RedisUserKey;
import lombok.Getter;
import lombok.ToString;

/**
 * @author AYL    2018/9/4 12:29
 */
@Getter
@ToString(callSuper = true)
public class LoginCookie extends BaseCookie {

    public LoginCookie(int expireSec, String cookieName) {
        super(expireSec, cookieName);
    }

    public static LoginCookie tokenCookie = new LoginCookie(RedisUserKey.USER_TOKEN_EXPIRE_SECONDS, "token");
}
