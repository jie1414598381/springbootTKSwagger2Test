package com.springboot.btest.util;

import com.springboot.btest.model.entity.SysUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.springboot.btest.common.Const.CURRENT_SYSUSER;
import static com.springboot.btest.common.Const.CURRENT_TOKEN;

public class ReqHolder {
    public static SysUser currentSysUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (SysUser) request.getAttribute(CURRENT_SYSUSER);
    }

    public static Long currentUId() {
        SysUser user = currentSysUser();
        if (null != user) {
            return user.getUid();
        }
        return null;
    }
    public static String currentToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (String) request.getAttribute(CURRENT_TOKEN);
    }
}
