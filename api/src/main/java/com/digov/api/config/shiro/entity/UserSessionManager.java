package com.digov.api.config.shiro.entity;

import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class UserSessionManager extends DefaultWebSessionManager {
    public static final String AUTHORIZATION = "Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public UserSessionManager() {
        super();
        this.setDeleteInvalidSessions(true);
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //从前端ajax headers中获取这个参数用来判断授权
        String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        String sessionidParam = WebUtils.toHttp(request).getParameter("sessionid");

        if (StringUtils.hasLength(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        } else if(StringUtils.hasLength(sessionidParam)){
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionidParam);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionidParam;
        }else {
            //从前端的cookie中取值
            return super.getSessionId(request, response);
        }
    }

}
