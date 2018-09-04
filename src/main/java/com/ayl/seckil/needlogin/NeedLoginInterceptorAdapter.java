package com.ayl.seckil.needlogin;

import com.ayl.seckil.domain.SeckillUser;
import com.ayl.seckil.service.SeckillUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author AYL    2018/9/4 12:06
 */
@Component
public class NeedLoginInterceptorAdapter extends HandlerInterceptorAdapter {
    @Autowired
    SeckillUserService seckillUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod)handler;
            NeedLogin needLogin = method.getMethodAnnotation(NeedLogin.class);

            if (needLogin == null) {
                needLogin = method.getBeanType().getAnnotation(NeedLogin.class);
            }

            if (needLogin != null) {
                SeckillUser user = seckillUserService.getLoginUser(request,response);
                if (user == null) {
                    request.getRequestDispatcher("www.baidu.com").forward(request, response);
                    return false;
                }
            }
        }
        return true;
    }
}
