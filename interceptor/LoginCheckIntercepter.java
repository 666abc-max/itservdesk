package com.qingda.tliaswebmanagement.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.qingda.tliaswebmanagement.pojo.Result;
import com.qingda.tliaswebmanagement.utils.JwtUtils;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class LoginCheckIntercepter implements HandlerInterceptor {
    @Override//目标资源方法运行前运行 返回true ：放行 返回false:不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String url = req.getRequestURL().toString();
        log.info("请求的url:{}",url);
        //判断url请求中是否包含login若包含就登录放行
        if(url.contains("login")){
            log.info("登录操作放行");
            return true;
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
            return false;
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
            return false;
        }

        //放行
        log.info("令牌合法 放行");
        return true;
    }

    @Override//目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override//视图渲染完毕后运行 最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
