package com.digov.api.config.redis;

import com.digov.api.entity.db.sys.user.SysUser;
import com.digov.api.util.common.CommonUtil;
import com.digov.api.util.shiro.ShiroUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import com.digov.api.config.redis.annotation.RepeatSubmit;

/**
 * 表单重复提交的
 * 拦截器
 */
@Component
public class RepeatSubmitService {
    @Resource
    private RedisService redisService;

    private static final Logger LOGGER = LogManager
            .getLogger(RepeatSubmitService.class);


    // 分布式锁的 超时时间  单位：ms
    private final int LOCK_TIME_OUT = 5 * 60 * 1000;

    /**
     * 获取key
     *
     * @return
     */
    private String getKey(MethodSignature methodSignature) {

        Method method = methodSignature.getMethod();
        RepeatSubmit repeatSubmit = method.getAnnotation(RepeatSubmit.class);
        if (repeatSubmit == null) {
            return null;
        }

        SysUser sysUser = ShiroUtil.getSysUser();
        if (sysUser == null) {
            return null;
        }
        String username = sysUser.getUsername();
        if (username == null) {
            return null;
        }
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        StringBuffer requestURL = request.getRequestURL();

        //获取key: 格式：url + ":" + username
        String key = requestURL
                .append(":")
                .append(username)
                .toString();
        return key;
    }

    /**
     * 添加锁
     * 是否存在 未处理完的请求
     *
     * @param methodSignature
     * @return
     */
    public boolean addLock(MethodSignature methodSignature) {
        //获取key: 格式：url + ":" + creditNo
        String key = this.getKey(methodSignature);
        if (CommonUtil.isEmpty(key)) {
            return false;
        }

        //获取锁
        boolean success = redisService.addLock(key, LOCK_TIME_OUT);
        if (!success) {
            return true;
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param methodSignature
     */
    public void deleteLock(MethodSignature methodSignature) {
        try {
            //获取key: 格式：url + ":" + username
            String key = this.getKey(methodSignature);
            if (CommonUtil.isEmpty(key)) {
                return;
            }
            //获取锁
            redisService.delLock(key);
        } catch (Exception e) {
            String exceptionInfo = CommonUtil.getExceptionInfo(e);
            LOGGER.error("释放锁错误：" + exceptionInfo);
        }

    }

}
