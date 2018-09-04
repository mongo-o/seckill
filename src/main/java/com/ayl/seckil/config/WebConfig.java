package com.ayl.seckil.config;

import com.ayl.seckil.needlogin.LoginUserHandlerMethodArgumentResolver;
import com.ayl.seckil.needlogin.NeedLoginInterceptorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author AYL    2018/9/4 15:38
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginUserHandlerMethodArgumentResolver argumentResolver;

    @Autowired
    private NeedLoginInterceptorAdapter needLoginInterceptorAdapter;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(argumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(needLoginInterceptorAdapter).addPathPatterns("/**");
    }
}
