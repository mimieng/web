package org.example.webmanagement.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.webmanagement.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
@Component
public class DemoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/login")) {
            log.info("登录请求，放行");
            return true;
        }
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            log.info("token为空，拦截");
            response.setStatus(401);
            return false;
        }
        try {
            JwtUtils.parseToken(token);
            log.info("token有效，放行");
            return true;
        } catch (Exception e) {
            log.info("token无效，拦截");
            response.setStatus(401);
        }

    log.info("令牌合法");
        return true;
    }
}
