package com.soecode.lyf.util.Handle;

import com.soecode.lyf.entity.result.ResultModel;
import com.soecode.lyf.enums.WizardAuditEnum;
import com.soecode.lyf.service.RedisUtilService;
import com.soecode.lyf.util.ResultUtil;
import com.soecode.lyf.util.entity.token.UserTokenData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorityAnnotationInterceptor extends HandlerInterceptorAdapter {

    static final Logger logger = LoggerFactory.getLogger(AuthorityAnnotationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Auth-Interception===============start=====================================");
        String jspvalue = request.getRequestURL().toString();

        HandlerMethod handler2 = (HandlerMethod) handler;
        handler2.getMethodParameters();
        AuthRoleValidate authRole = ((HandlerMethod) handler).getMethodAnnotation(AuthRoleValidate.class);
        //AuthRole authRole = handler2.getMethodAnnotation(AuthRole.class);
        ResultModel result = ResultUtil.error(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        ServletContext context = request.getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(context);
        RedisUtilService redisService = wac.getBean(RedisUtilService.class);

        Boolean resultStatus = false;
        if (null == authRole) {
            logger.info("Auth-not-statement====================================================");
            //没有声明权限,放行
            // SendMsgUtil.sendJsonMessage(response, result);
            return true;
        } else {
            logger.info("Auth-yes-statement=====================================");
            logger.debug("authRole", authRole.toString());
            UserTokenData token = redisService.getMemberToken(request);
            return false;
        }
    }
}
