package org.example.webmanagement.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.webmanagement.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.logging.LogRecord;
@Slf4j
//@WebFilter(filterName = "TokenFilter",urlPatterns = "/*")
public class TokenFilter implements Filter {



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/login")){
            log.info("登录请求，放行");
            filterChain.doFilter(request,response);
            return;
        }
        String token=request.getHeader("token");
        if (token==null||token.isEmpty()){
            log.info("token为空，拦截");
            response.setStatus(401);
            return;
        }
        try {
            JwtUtils.parseToken(token);
            log.info("token有效，放行");
            filterChain.doFilter(request,response);
        }catch (Exception e){
            log.info("token无效，拦截");
            response.setStatus(401);
        }
    }
}
