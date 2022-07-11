package com.digov.api.config.shiro.entity;

import com.alibaba.fastjson.JSONObject;
import com.digov.api.entity.result.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 对于跨域的POST请求，浏览器发起POST请求前都会发送一个OPTIONS请求已确定服务器是否可用，OPTIONS请求通过后继续执行POST请求，
 * 而shiro自带的权限验证是无法处理OPTIONS请求的，所以这里需要重写isAccessAllowed方法。
 */
@Slf4j
public class UserFormAuthorizationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(servletRequest);
        if ("OPTIONS".equals(httpServletRequest.getMethod())) {
            return true;
        }
        return super.isAccessAllowed(servletRequest, servletResponse, o);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            //添加重写的方法
            HttpServletRequest req = (HttpServletRequest)request;
            HttpServletResponse resp = (HttpServletResponse) response;
            if(req.getMethod().equals(RequestMethod.OPTIONS.name())) {
                resp.setStatus(HttpStatus.OK.value());
                return true;
            }

            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }
            //前端Ajax请求，则不会重定向
            resp.setHeader("Access-Control-Allow-Origin",  req.getHeader("Origin"));
            resp.setHeader("Access-Control-Allow-Credentials", "true");
            resp.setContentType("application/json; charset=utf-8");
            resp.setCharacterEncoding("UTF-8");

            PrintWriter out = resp.getWriter();
            R<String> stringR = R.error("用户未登录", 10001);
            out.println(JSONObject.toJSONString(stringR));
            out.flush();
            out.close();
            return false;
        }
    }
}
