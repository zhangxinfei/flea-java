package com.soecode.lyf.controller;

import com.alibaba.fastjson.JSON;
import com.soecode.lyf.entity.User;
import com.soecode.lyf.entity.result.ResultModel;
import com.soecode.lyf.enums.WizardAuditEnum;
import com.soecode.lyf.mapper.UserMapper;
import com.soecode.lyf.service.UserService;
import com.soecode.lyf.util.ResultUtil;
import com.soecode.lyf.util.RsaJsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
@ResponseBody
@CrossOrigin(origins = "http://cxuniversity.top") //允许这个域名就行跨域访问
//@CrossOrigin(origins = "http://127.0.0.1:80") //允许这个域名就行跨域访问
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    //日志
    final static Logger logger = LoggerFactory.getLogger(AdministratorsController.class);

    /**
     * Describe: 用户登录
     * @author 张新飞
     * @date 2018/12/23
     * @parms  * @param null
     * @return
     */
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST, produces = "application/json")
    public ResultModel userLogin(HttpServletRequest request, @RequestBody String user_login) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(), WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        try {
            logger.info("==========================解密=========================");
            String user_loginRsa = user_login.substring(11);
            //解密
            String jsonStr = RsaJsUtils.decryptByPrivateKeyData(user_loginRsa);
            //把json转换成po类
            User user = JSON.parseObject(jsonStr, User.class);
            //查询数据库判断用户
            logger.info("======================验证本地库是否存在数据=========================");
            result = userService.userLogin(request,user);
            if (result.getCode() == 20) {
                logger.info("======================未查询到用户数据=========================");
                return result;
            }
        } catch (Exception e) {
            result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(), WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Describe: 用户注册
     * @author 张新飞
     * @date 2018/12/23
     * @parms  * @param null
     * @return
     */
    @RequestMapping(value = "/userRegister", method = RequestMethod.POST, produces = "application/json")
    public ResultModel userRegister(HttpServletRequest request, @RequestBody User user) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(), WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("用户注册-->start");
        int Influence = userMapper.userRegister(user);
        if(Influence >0){
            result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(), WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
        }
        logger.info("用户注册-->end");
        return result;
    }

    /**
     * Describe: 查看用户我的信息
     * @author 张新飞
     * @date 2018/12/23
     * @parms  * @param null
     * @return
     */
    @RequestMapping(value = "/Customer/findUserAll")
    public ResultModel findUserAll(String username){
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(), WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("===============查看我的信息开始===============");
        String where = "username = "+"'"+username+"'";
        List<Map<String,Object>> userList = userMapper.selectAllByWhere(where);
        if (userList.size() > 0) {
            result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(), WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
            result.setData(userList);
        }
        logger.info("===============查看我的信息结束===============");
        return result;
    }


}
