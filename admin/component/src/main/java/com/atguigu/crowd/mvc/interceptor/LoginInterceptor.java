package com.atguigu.crowd.mvc.interceptor;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.exception.AccessForbiddenException;
import com.atguigu.crowd.pojo.Admin;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author guyao
 * @create 2021-08-02 17:20
 */
public class LoginInterceptor  extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 通过request获取session
        HttpSession session = request.getSession();
        // 尝试获取admin
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN.getStr());
        // 没有就过不去，
        if(admin == null)
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDDEN.getStr());
        // 有就可以进
        return true;
    }
}
