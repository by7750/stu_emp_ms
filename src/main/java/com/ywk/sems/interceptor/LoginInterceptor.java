package com.ywk.sems.interceptor;

import com.ywk.sems.bean.User;
import com.ywk.sems.exceptions.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        log.info("开始拦截");
        // 获取请求携带的token
        User user = (User) request.getSession().getAttribute("user");

        log.info("路径：" + request.getContextPath() + request.getRequestURI());
        if (user == null) {
            throw new AuthException("请先登录");
        }
        log.info("当前操作的用户是" + user.getUsername());
        return true;
    }
}
