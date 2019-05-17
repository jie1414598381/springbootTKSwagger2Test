package com.springboot.btest.common;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.springboot.btest.common.annotation.Auth;
import com.springboot.btest.common.exception.AppException;
import com.springboot.btest.model.entity.SysUser;
import com.springboot.btest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.springboot.btest.common.Const.*;
import static com.springboot.btest.common.exception.ExceptionEnum.UNAUTHORIZED;

/**
 * 拦截器
 */
public class AppInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // 权限校验
        if (handler instanceof HandlerMethod) {
            if (null != ((HandlerMethod) handler).getMethod().getDeclaringClass().getAnnotation(Auth.class)) {// 类级别
                validateToken(request);
            } else if (((HandlerMethod) handler).hasMethodAnnotation(Auth.class)) { // 方法级别
                validateToken(request);
            }
        }

        return true;
    }

    /**
     * 校验token
     *
     * @param request
     */
    private void validateToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN);
        if (Strings.isNullOrEmpty(token)) {
            token = request.getParameter(TOKEN);
            if (Strings.isNullOrEmpty(token)) {
                throw new AppException(UNAUTHORIZED);
            }
        }
        String user = redisService.get(token);
        if (null == user) {
            throw new AppException(UNAUTHORIZED);
        }
        request.setAttribute(CURRENT_SYSUSER, JSONObject.parseObject(user, SysUser.class));
        request.setAttribute(CURRENT_TOKEN, token);
    }
}
