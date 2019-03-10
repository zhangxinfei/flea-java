package com.soecode.lyf.util.aop.config;

import com.alibaba.fastjson.JSON;
import com.soecode.lyf.dto.Result;
import com.soecode.lyf.entity.User;
import com.soecode.lyf.mapper.UserMapper;
import com.soecode.lyf.util.Handle.LoginRequired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 登录认证的拦截器
 */
@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    UserMapper userMapper;

    /**
     * Handler执行之前调用这个方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final String authHeaderVal = httpRequest.getHeader("token");

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String name = request.getServletPath().toString();

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        if (methodAnnotation != null) {
            if (StringUtils.isNotEmpty(authHeaderVal)) {
                try {
                    //通过token签名获取信息
                    User user = userMapper.getToken(authHeaderVal);
                    int userId = Integer.valueOf(user.getId());
                    System.out.println("========"+name+"===>LoginInterceptor preHandle 验证成功放行");
                    return true;
                } catch (Exception e) {
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(JSON.toJSONString(new Result(false,"登录已过期，请重新登录！")));
                    System.out.println("========"+name+"===>LoginInterceptor preHandle 拦截，登录已过期，请重新登录！");
                    return false;
                }
            } else {
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(JSON.toJSONString(new Result(false,"尚未登录！")));
                System.out.println("========"+name+"===>LoginInterceptor preHandle 拦截，尚未登录！");
                return false;
            }
        }
        System.out.println("========"+name+"===>LoginInterceptor preHandle 没加验证注解放行");
        return true;
    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        String name = request.getServletPath().toString();
        System.out.println("========"+name+"===>LoginInterceptor postHandle");
    }

    /**
     * Handler执行完成之后调用这个方法
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exc)
            throws Exception {
        String name = request.getServletPath().toString();
        System.out.println("========"+name+"===>LoginInterceptor afterCompletion");
    }
}