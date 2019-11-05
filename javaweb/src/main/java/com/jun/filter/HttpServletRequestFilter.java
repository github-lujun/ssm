package com.jun.filter;

import javax.servlet.*;
import java.io.IOException;

public class HttpServletRequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //执行前
        //todo:字符编码
        chain.doFilter(request,response);
        //执行后操作
        //todo:字符编码
    }

    @Override
    public void destroy() {

    }
}
