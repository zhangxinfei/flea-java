//package com.soecode.lyf.util.auth;
//
//import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
//import com.soecode.lyf.controller.UserController;
//import com.soecode.lyf.enums.CommonInfoEnum;
//import com.soecode.lyf.util.JwtUtils;
//import io.jsonwebtoken.Claims;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Map;
//import java.util.logging.Logger;
//
//import static com.soecode.lyf.util.auth.loginFilter.isAjaxRequest;
//
//public class SessionFilter extends HttpServlet implements Filter {
//
//    //日志
//    final static org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
//
//    public void destroy() {
//        return;
//    }
//    //权限过滤器，除了一些不需要过滤的url,其余都过滤
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//
//        ServletContext context = request.getServletContext();
//        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(context);
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        //String userid = (String)((HttpServletRequest) request).getSession().getAttribute("userId");//httpRequest.getHeader("iv-user");
//        String host = httpRequest.getHeader("host");
//        String sp = ((HttpServletRequest) request).getServletPath();
//        //请求的uri
//        String jspvalue = httpRequest.getRequestURL().toString();
////        Map map = httpRequest.getParameterMap();
////        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
////        String loginUrl = httpRequest.getContextPath() + "/jsp/login.jsp";
////        String username = (String) httpRequest.getSession().getAttribute("user_studentId");
//
//        logger.info("======================restful SessionFilter=start=========================");
//        if (jspvalue.indexOf("/User/selectByKeyAndPassword") != -1) {//判断获取的URL是否需要存在，存在为true
//            logger.info("======================restful SessionFilter=访问登录页面进行放心start=========================");
//            chain.doFilter(request, response);
//            logger.info("======================restful SessionFilter=访问登录页面进行放心end=========================");
//        }
//
////        Boolean token_enter = true;
////        //校验token
////        try {
////            String token = "";
////            if (jspvalue.indexOf("bd/poi/download/budget") != -1) {
////                String paramsString = httpRequest.getQueryString();
////                token = paramsString.substring(paramsString.indexOf("token") + 6, paramsString.length());
////                httpRequest.setAttribute("SY-TOKEN", token);
////            } else {
////                token = httpRequest.getHeader("SY-TOKEN");
////            }
////            token_enter = tokenValidate(token_enter, httpResponse, httpRequest, jspvalue, token);
////        } catch (Exception e) {
////            e.printStackTrace();
////            logger.info("======================restful SessionFilter=放行token校验错误=========================");
////        }
////        //是否放行
////        if (token_enter) {
////            logger.info("======================restful SessionFilter=放行token=========================");
////            chain.doFilter(request, response);
////            logger.info("======================restful SessionFilter=放行token=========================");
////        }
//    }
//    @Override
//    public void init(FilterConfig fConfig) throws ServletException {
//
//    }
//
//
//
//    /**
//     * token校验方法
//     * @param token_enter
//     * @param httpResponse
//     * @param httpRequest
//     * @throws Exception
//     */
//    private boolean tokenValidate(Boolean token_enter,HttpServletResponse httpResponse,HttpServletRequest httpRequest,String jspvalue,String token)throws Exception{
//
//        try {
//            //判断token是否存在
//            if(StringUtils.isBlank(token)){
//                //返回toke不存在
//                logger.info("======================restful SessionFilter=未携带token=========================");
//                redirectErrorInfo(CommonInfoEnum.accessStatus.REST_TOKEN_NOT_EXIST_STATUS.getValue(),CommonInfoEnum.accessStatus.REST_TOKEN_NOT_EXIST_STATUS.getDesc(),httpResponse,httpRequest);
//            }else{
//                //转化token数据
//                Claims claims = JwtUtils.getClaims(token);
//                //获取有效时间值
//                long effectiveTime = claims.getExpiration().getTime();
//                //比较有效时间是否小于当前时间，则为超时操作,返回超时编码
//                if(System.currentTimeMillis()>effectiveTime){
//                    //返回数据超时
//                    logger.info("======================restful SessionFilter=token超时=========================");
//                    redirectErrorInfo(CommonInfoEnum.accessStatus.REST_TOKEN_TIME_OUT_STATUS.getValue(),CommonInfoEnum.accessStatus.REST_TOKEN_TIME_OUT_STATUS.getDesc(),httpResponse,httpRequest);
//                }else if(!httpRequest.getRemoteAddr().equals(claims.getIssuer())){
//                    //返回访问ip不正确
//                    logger.info("======================restful SessionFilter=设备更换ip不正确=========================");
//                    redirectErrorInfo(CommonInfoEnum.accessStatus.REST_ACCESS_IP_ERROR_STATUS.getValue(),CommonInfoEnum.accessStatus.REST_ACCESS_IP_ERROR_STATUS.getDesc(),httpResponse,httpRequest);
//                }else {
//                    return true;
//                    //token_enter = true;
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            //token无效
//            logger.info("======================restful SessionFilter=token无效转换异常=========================");
//            try {
//                redirectErrorInfo(CommonInfoEnum.accessStatus.REST_TOKEN_ERROR_STATUS.getValue(),CommonInfoEnum.accessStatus.REST_TOKEN_ERROR_STATUS.getDesc(),httpResponse,httpRequest);
//            }catch (Exception ex){
//                ex.printStackTrace();
//            }
//        }
//        return  false;
//    }
//    /**
//     * toen验证错误返回登录页面传回错误信息
//     * @param code
//     * @param desc
//     * @param response
//     * @param httpRequest
//     * @throws Exception
//     */
//    private void redirectErrorInfo(Integer code,String desc,HttpServletResponse response,HttpServletRequest httpRequest)throws Exception{
//        response.setHeader("error",code+"_"+desc);
//        //response.sendRedirect("http://localhost:8080"+"/restful/login/gopage");
//    }
//    /**
//     * 打印提示信息
//     */
//    public void print(ServletResponse response, String str) {
//        try {
//
//            PrintWriter writer = response.getWriter();
//
//            writer.write(str);
//
//            writer.flush();
//
//            writer.close();
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//    }
//
//
//}
