package com.takeout.interceptor;

import com.takeout.constant.JwtClaimsConstant;
import com.takeout.context.BaseContext;
import com.takeout.properties.JwtProperties;
import com.takeout.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Resource
    private JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断当前拦截到的是 Controller 的方法还是其他静态资源
        if (!(handler instanceof HandlerMethod)) {
            // 如果拦截到的是静态资源，直接放行
            return true;
        }

        // 1. 从请求头中获取令牌（根据配置中的 token 键名获取，即 "token"）
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        // 2. 校验令牌
        try {
            log.info("JWT 令牌校验: {}", token);
            Claims claims = JwtUtil.parseJwt(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("当前员工 ID: {}", empId);

            // 核心：将当前登录员工的 ID 存入 ThreadLocal 线程上下文中
            BaseContext.setCurrentId(empId);

            // 3. 校验通过，放行
            return true;
        } catch (Exception ex) {
            log.warn("JWT 校验失败，原因: {}", ex.getMessage());
            // 4. 校验不通过，响应 401 状态码，并拦截请求
            response.setStatus(401);
            return false;
        }
    }
}