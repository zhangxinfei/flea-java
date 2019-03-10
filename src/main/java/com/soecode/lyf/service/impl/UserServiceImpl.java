package com.soecode.lyf.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.soecode.lyf.controller.AdministratorsController;
import com.soecode.lyf.entity.User;
import com.soecode.lyf.entity.result.ResultModel;
import com.soecode.lyf.enums.WizardAuditEnum;
import com.soecode.lyf.mapper.UserMapper;
import com.soecode.lyf.service.UserService;
import com.soecode.lyf.util.JwtUtils;
import com.soecode.lyf.util.ResultUtil;
import com.soecode.lyf.util.entity.token.UserTokenData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    //日志
    final static Logger logger = LoggerFactory.getLogger(AdministratorsController.class);


    @Override
    public ResultModel userLogin(HttpServletRequest request,User user) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(), WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("判断用户是否存在-->start");
         int loginInfluence = userMapper.userLogin(user);
         if(loginInfluence > 0){
             logger.info("======================用户登录成功封装用户数据返回token=========================");
             long ttlMillis = 1000 * 60 * 60;//设定token时间，设定一个3600秒token过期
             UserTokenData userTokenData = new UserTokenData();
             SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
             userTokenData.setIphone(String.valueOf(user.getIphone()));
             //获取当前系统时间并存入实体类
             String time = df.format(new Date());
             userTokenData.setDate(time);
             String subject = JSONObject.toJSONString(userTokenData, SerializerFeature.WriteMapNullValue);
             //生成token
             String token = JwtUtils.createJWT(String.valueOf(user.getIphone()), request.getRemoteAddr(), subject, ttlMillis, time);

             result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
         }
        logger.info("判断用户是否存在-->end");
        return result;
    }


}
