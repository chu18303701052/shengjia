package com.digov.api.config.shiro.entity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.digov.api.entity.db.sys.user.SysUser;
import com.digov.api.repository.sys.role.menu.SysRoleMenuRepository;
import com.digov.api.repository.sys.user.SysUserRepository;
import com.digov.api.repository.sys.user.role.SysUserRoleRepository;
import com.digov.api.util.common.CommonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * 自定义Realm，实现授权与认证
 * 自定义权限匹配和账号密码匹配
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    private SysRoleMenuRepository sysRoleMenuRepository;

    @Autowired
    private RedisSessionDAO redisSessionDAO;
    /**
     * 用户授权
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalCollection) {

        System.out.println("===执行授权===");

        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        if(user != null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 角色字符串集合
            Collection<String> rolesCollection = new HashSet<>();
            // 权限字符串集合
            Collection<String> premissionCollection = new HashSet<>();
            // 读取并赋值用户角色与权限
            Long sysUserId = user.getId();

            //添加角色
            List<String> userRoleNameList = sysUserRoleRepository.getRoleNameList(sysUserId);
            if (!CommonUtil.isEmpty(userRoleNameList)) {
                info.addRoles(userRoleNameList);
            }

            //添加权限
            List<String> permissionUrlList = sysRoleMenuRepository.getPermissionUrlList(sysUserId);
            if (!CommonUtil.isEmpty(permissionUrlList)) {
                info.addStringPermissions(permissionUrlList);
            }
            return info;
        }
        return null;
    }

    /**
     * 用户认证
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("===执行认证===");

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //userName 账号
        String username = token.getUsername();

        //判断是用户名字
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = sysUserRepository.selectOne(wrapper);

        if(sysUser == null){
            // 用户不存在
            throw new UnknownAccountException("用户不存在");
        }

        //判断用户是否注销了
        Integer status = sysUser.getStatus();
        if(CommonUtil.isEmpty(status) || status.intValue() != 1){
            throw new DisabledAccountException("用户已被注销");
        }
        String password = sysUser.getPassword();

        //清空敏感信息
        sysUser.setPassword(null);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(sysUser, password,
                getName());
        //验证成功开始踢人(清除缓存和Session)
        //从缓存中获取Session
        Session session = null;
//        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        Collection<Session> sessions = null;
        SysUser sysUserLast;
        Object attribute = null;
        if (!CommonUtil.isEmpty(sessions)) {
            for(Session sessionInfo : sessions){
                //遍历Session,找到该用户名称对应的Session
                attribute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                if (attribute == null) {
                    continue;
                }
                sysUserLast = (SysUser) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
                if (sysUser == null) {
                    continue;
                }
                if (Objects.equals(sysUserLast.getUsername(), username)) {
                    session=sessionInfo;
                }
            }
        }
        if (session != null && attribute != null) {
            //删除session
            redisSessionDAO.delete(session);
            //删除Cache，在访问受限接口时会重新授权
            DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
            Authenticator authc = securityManager.getAuthenticator();
            ((LogoutAware) authc).onLogout((SimplePrincipalCollection) attribute);
            SecurityUtils.getSubject().getSession(false);
        }

        return simpleAuthenticationInfo;
    }

}
