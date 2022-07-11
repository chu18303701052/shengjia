package com.digov.api.util.shiro;

import com.digov.api.entity.db.sys.user.SysUser;
import com.digov.api.util.common.CommonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.Set;

public class ShiroUtil {

    /**
     * 判断当前用户是否拥有某些权限
     * @param roles
     * @return
     */
    public static boolean hasRoles (Set<String> roles){
        if (CommonUtil.isEmpty(roles)) {
            return false;
        }
        Subject subject = SecurityUtils.getSubject();
        for (String role : roles) {
            if (subject.hasRole(role)) {
                return true;
            }
        }
        return false;
    }

    public static Long getUserId (){
        SysUser sysUser = getSysUser();

        if (CommonUtil.isEmpty(sysUser)) {
            return null;
        }

        Long sysUserId = sysUser.getId();
        return sysUserId;
    }

    public static SysUser getSysUser (){
        Subject subject = SecurityUtils.getSubject();
        if (CommonUtil.isEmpty(subject)) {
            return null;
        }
        SysUser sysUser = (SysUser) subject.getPrincipal();
        if (CommonUtil.isEmpty(sysUser)) {
            return null;
        }
        return sysUser;
    }
}
