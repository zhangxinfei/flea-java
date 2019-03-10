package com.soecode.lyf.service;

import com.soecode.lyf.entity.User;
import com.soecode.lyf.entity.result.ResultModel;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    /**
     * 用户登录
     * @param request
     * @param user
     * @return
     */
    ResultModel userLogin(HttpServletRequest request,User user);

}
