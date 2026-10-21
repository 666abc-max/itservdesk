package com.qingda.tliaswebmanagement.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化，不需要就空着，不要写Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // doFilter 放行必须写 chain.doFilter(request,response);
        // 如果不写放行，请求直接卡住，页面空白！
        System.out.println("filter执行前");
        chain.doFilter(request, response); // ✅放行，非常关键
        System.out.println("filter执行后");
    }

    @Override
    public void destroy() {
        //销毁资源，不需要就空着；不要写Filter.super.destroy();
    }
}
