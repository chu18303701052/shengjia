package com.digov.api.config.shiro.entity;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 自定义加密认证
 * 实现强一致性hash认证
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        //客户端输入的密码
        char[] password = token.getPassword();
        String tokenPassword = String.valueOf(password);
        //数据库存储密码
        Object accountCredentials = getCredentials(info);
        String dbPassword = (String) accountCredentials;
        //使用强hash进行校验
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean passBool = passwordEncoder.matches(tokenPassword, dbPassword);
        //将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
        return passBool;
    }

}
