package com.soecode.lyf.util.auth.Fileter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InitContent implements Filter {

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        SysContent.setRequest((HttpServletRequest) arg0);
        SysContent.setResponse((HttpServletResponse) arg1);
        arg2.doFilter(arg0, arg1);
    }
    public void destroy() {
    }
    public void init(FilterConfig arg0) throws ServletException {
    }

}


