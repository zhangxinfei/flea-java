package com.soecode.lyf.util.auth;

import com.soecode.lyf.entity.params.AdminParams;
import com.soecode.lyf.enums.AuthCodeEnum;
import com.soecode.lyf.enums.WizardAuditEnum;
import com.soecode.lyf.mapper.AdminMapper;
import com.soecode.lyf.util.auth.Fileter.SysContent;
import com.soecode.lyf.util.exception.BizException;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;

public class authTokenAOPInterceptor {
    @Autowired
    AdminMapper adminMapper;


    private static final String authFieldName = "authToken";
    private static final String userIdFieldName = "userId";

    public void before(JoinPoint joinPoint, AuthToken authToken) throws Throwable{

        Object[] args =  joinPoint.getArgs(); //获取拦截方法的参数
        boolean isFound = false;
        for(Object arg : args){
            if(arg != null){
                Class<?> clazz = arg.getClass();//利用反射获取属性值
                Field[]  fields =  clazz.getDeclaredFields();
                int authIndex = -1;
                int userIdIndex = -1;
                for(int i = 0; i < fields.length; i++){
                    Field field = fields[i];
                    field.setAccessible(true);
                    if(authFieldName.equals(field.getName())){//包含校验Token
                        authIndex = i;
                    }else if(userIdFieldName.equals(field.getName())){//包含用户Id
                        userIdIndex = i;
                    }
                }

                if(authIndex >= 0 & userIdIndex >= 0){
                    isFound = true;
                    authTokenCheck(fields[authIndex], fields[userIdIndex], arg, authToken);//校验用户
                    break;
                }
            }
        }
        if(!isFound){
            throw new BizException(WizardAuditEnum.tokenStatusEnum.CHECK_AUTHTOKEN_FAIL.getDesc());
        }

    }

    private void  authTokenCheck(Field authField, Field userIdField, Object arg, AuthToken authToken) throws Exception{
        if(String.class == authField.getType()){
            String authTokenStr = (String)authField.get(arg);//获取到校验Token
            AdminParams adminParams = adminMapper.selectAdminByToken(authTokenStr);
            if(adminParams != null){
                userIdField.set(arg, adminParams.getId());
            }else{
                throw new BizException(WizardAuditEnum.tokenStatusEnum.CHECK_AUTHTOKEN_FAIL.getDesc());
            }
        }

    }





}

