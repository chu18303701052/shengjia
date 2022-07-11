package com.digov.api.config.shiro;


import com.digov.api.config.redis.RedisPrefix;
import com.digov.api.config.shiro.entity.*;
import com.digov.api.util.common.CommonUtil;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置类
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/6/8
 * Time: 15:06
 * Description: No Description
 */

@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.profiles.active}")
    private String active;
    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * redis中session失效时间单位秒
     */
    private final int SESSION_EXPIRE = 24 * 60 * 60;

//    private final int SESSION_EXPIRE = 1 * 60;
    /**
     * 权限规则配置
     **/
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authc", new UserFormAuthorizationFilter());

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //swagger资源不拦截
        filterChainDefinitionMap.put("/digov_api/doc.html", "anon");
        filterChainDefinitionMap.put("/digov_api/swagger-resources/**/**", "anon");
        filterChainDefinitionMap.put("/digov_api/v2/**", "anon");
        filterChainDefinitionMap.put("/digov_api/webjars/**/**", "anon");

        //默认不需要登录
        filterChainDefinitionMap.put("/digov_api/pc/**", "anon");
        //表示认证之后才能访问
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /**
     * shiro安全管理器（权限验证核心配置）
     **/
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //自定义session设置
        securityManager.setSessionManager(sessionManager());
        //自定义缓存设置
        securityManager.setCacheManager(cacheManager());
        //自定义realm设置
        securityManager.setRealm(userRealm());

        return securityManager;
    }

    /**
     * 会话管理
     **/
    @Bean
    public SessionManager sessionManager() {
        UserSessionManager sessionManager = new UserSessionManager();
        sessionManager.setSessionDAO(sessionDAO());
        //设置cookie
        sessionManager.setSessionIdCookie(cookieDAO());
        return sessionManager;
    }

    /**
     * 使用的是shiro-redis开源插件 缓存依赖
     **/
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host+":"+port);
        redisManager.setTimeout(timeout);
        if (!CommonUtil.isEmpty(password)) {
            redisManager.setPassword(password);
        }
        return redisManager;
    }

    /**
     * 使用的是shiro-redis开源插件 session持久化
     **/
    @Bean
    public RedisSessionDAO sessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setSessionIdGenerator(new CustomSessionIdGenerator());
        redisSessionDAO.setKeyPrefix(RedisPrefix.SHIRO_SESSION_ID.name() + applicationName);
        redisSessionDAO.setExpire(SESSION_EXPIRE);
        return redisSessionDAO;
    }


    /**
     * 缓存管理
     **/
    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setKeyPrefix(RedisPrefix.SHIRO_CACHE.name() + applicationName);
        // 配置缓存的话要求放在session里面的实体类必须有个id标识
        redisCacheManager.setPrincipalIdFieldName("id");
        return redisCacheManager;
    }

    /**
     * 权限管理
     **/
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
//        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        userRealm.setCredentialsMatcher(new CustomCredentialsMatcher());//自定义密码管理器
        return userRealm;
    }

    //凭证匹配器（由于密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
    public HashedCredentialsMatcher hashedCredentialsMatcher(){

        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);//散列算法:这里使用MD5算法;
//        hashedCredentialsMatcher.setHashAlgorithmName(Sha1Hash.ALGORITHM_NAME);//散列算法:这里使用SHA-1算法;
        hashedCredentialsMatcher.setHashIterations(1);//散列的次数，比如散列1次，相当于 md5("");

        return hashedCredentialsMatcher;
    }

    /**
     * 同一个域下两个项目使用shiro，cookie值相同相互影响
     **/
    @Bean
    public Cookie cookieDAO() {
        Cookie cookie=new SimpleCookie();
        cookie.setName("JSESSIONID_" + applicationName.toUpperCase());
        return cookie;
    }

}
