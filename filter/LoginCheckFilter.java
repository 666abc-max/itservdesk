package com.qingda.tliaswebmanagement.filter;

import com.alibaba.fastjson.JSONObject;
import com.qingda.tliaswebmanagement.pojo.Result;
import com.qingda.tliaswebmanagement.utils.JwtUtils;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import com.qingda.tliaswebmanagement.pojo.Result;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;


@Slf4j
//@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
       //获取请求url
        HttpServletRequest req = (HttpServletRequest) request;
        ServletResponse resp =(ServletResponse) response;
        String url = req.getRequestURL().toString();
        log.info("请求的url:{}",url);
        //判断url请求中是否包含login若包含就登录放行
        if(url.contains("login")){
            log.info("登录操作放行");
            chain.doFilter(request,response);
            return;
        }
        //不是登录操作需要获取请求头中的令牌
        String jwt = req.getHeader("token");

        //判断令牌是否存在 若不存在 返回错误结果
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空 返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象-》》json
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        //解析token如果解析失败 返回错误结果（未登录）
        try{
            JwtUtils.parseJWT(jwt);
        }catch(Exception e){
            log.info("解析令牌失败 返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象-》》json
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //放行
        log.info("令牌合法 放行");
        chain.doFilter(request,response);
    }

}
