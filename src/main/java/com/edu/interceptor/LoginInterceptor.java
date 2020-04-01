package com.edu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: spring-boot-eplatform-01
 * @description: 拦截器进行登录检查，（自定义拦截器类），与MyConfig一起使用
 * @author: Mr.jia
 * @date: 2020-03-26 18:44
 **/

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null) {
            // 未登录，返回登录页面
            request.setAttribute("msg", "没有权限，请登录");
            request.getRequestDispatcher("/login.html").forward(request, response);
            return false;
        } else {
            // 已登录,放行请求
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
