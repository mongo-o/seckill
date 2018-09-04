package com.ayl.seckil.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author AYL    2018/9/4 12:16
 */
public class CookieUtil {
    public static void setCookie(HttpServletResponse response, BaseCookie baseCookie, String cookieValue) {
        Cookie cookie = new Cookie(baseCookie.getCookieName(), cookieValue);
        cookie.setMaxAge(baseCookie.getExpireSec());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for(Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
