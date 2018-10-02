package com.online.seva.config;

import com.online.seva.config.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        // registry.addViewController("/home").setViewName("hello");
        //    registry.addViewController("/").setViewName("/home");
        //  registry.addViewController("/register").setViewName("index");
        /*registry.addViewController("/login").setViewName("login");*/
        registry.addViewController("/forbidden").setViewName("forbidden");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor());
    }
}
