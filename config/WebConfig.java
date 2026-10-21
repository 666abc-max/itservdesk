package com.qingda.tliaswebmanagement.config;

import com.qingda.tliaswebmanagement.interceptor.LoginCheckIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//配置类
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginCheckIntercepter loginCheckIntercepter;
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginCheckIntercepter).addPathPatterns("/**");
    }
}
