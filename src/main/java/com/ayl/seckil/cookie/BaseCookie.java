package com.ayl.seckil.cookie;

import lombok.Getter;
import lombok.ToString;

/**
 * @author AYL    2018/9/4 12:30
 */
@Getter
@ToString(callSuper = true)
public class BaseCookie {
    /**
     * 过期时间默认为0
     * 当过期时间 <= 0 , 表示cookie永不失效
     */
    private Integer expireSec;

    private String cookieName;

    public BaseCookie(int expireSec, String cookieName) {
        this.expireSec = expireSec;
        this.cookieName = cookieName;
    }
}
