package com.digov.api.config.aop;

import com.alibaba.fastjson.JSONObject;
import com.digov.api.config.redis.RepeatSubmitService;
import com.digov.api.entity.result.R;
import com.digov.api.util.common.CommonUtil;
import com.digov.api.util.request.IpUtil;
import com.digov.api.util.request.RequestUtil;
import com.digov.api.util.str.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * 添加访问日志的切面
 */

@Component
@Aspect
@Slf4j
public class AccessLogger {

    @Resource
    private RepeatSubmitService repeatSubmitService;

    /**
     * 访问日志的记录日志
     */
    /**
     * 日志分隔符 |
     **/
    private final static String REGEX = "|";
    /**
     * 切面的入口
     */
    private static final String EXECUTION_PATH = "execution(* com.digov.api.controller..*.*(..))";
    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间

    private Map<String, Object> outputParamMap = null; // 存放输出结果
    /**
     * 请求的所有参数
     */
    private String paramsJson = "";

    /**
     * @Title：doBeforeInServiceLayer
     * @Description: 方法调用前触发
     * 记录开始时间
     * @author shaojian.yu
     * @date 2014年11月2日 下午4:45:53
     */
    @Before(EXECUTION_PATH)
    public void doBeforeAccess() {
        startTimeMillis = System.currentTimeMillis();
    }

    /**
     * @Title：doAfterInServiceLayer
     * @Description: 方法调用后触发
     * 记录结束时间
     * @author shaojian.yu
     * @date 2014年11月2日 下午4:46:21
     */
    @After(EXECUTION_PATH)
    public void doAfterInServiceLayer() {

        endTimeMillis = System.currentTimeMillis();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        StringBuffer accessStrBuf = new StringBuffer();
        //响应时间
        long userTime = endTimeMillis - startTimeMillis;
        accessStrBuf.append("time:");
        accessStrBuf.append(userTime);
        accessStrBuf.append(REGEX);

        //ip信息
        IpUtil ipUtilInstance = IpUtil.getInstance();
        String ipInfo = ipUtilInstance.getIpInfo(request, REGEX);
        accessStrBuf.append(ipInfo);

        //agent
        String accessType = RequestUtil.getHeaderInfo(request, "user-agent");
        accessStrBuf.append(accessType);
        accessStrBuf.append(REGEX);

        //url
        String originalURL = RequestUtil.getOriginalURL(request);
        accessStrBuf.append(originalURL);
        accessStrBuf.append(REGEX);

        //用户信息
//        Long userId = ShiroUtil.getUserId();
//        accessStrBuf.append("userId=");
//        accessStrBuf.append(userId);
//        accessStrBuf.append(REGEX);

        String string = accessStrBuf.toString();
        string = StringUtil.merge(string, "\n", paramsJson);
        log.info(string);

        //返回结果
        if (!CommonUtil.isEmpty(outputParamMap)) {
            String resultStr = JSONObject.toJSONString(outputParamMap);
            log.info(resultStr);
        }
    }

    /**
     * @return
     * @throws Throwable
     * @Title：doAround
     * @Description: 环绕触发
     * @author shaojian.yu
     * @date 2014年11月3日 下午1:58:45
     */
    @Around(EXECUTION_PATH)
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        outputParamMap = new HashMap<>();
        //获取参数
        paramsJson = getParamsJsonTotal(pjp);
        //判断是否为企业端用户
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();

        // 加锁 数据唯一注解判断 + redis加锁
        boolean addLock = repeatSubmitService.addLock(methodSignature);
        //存在正在处理的请求
        if (addLock) {
            result = R.error("正在处理，请勿重复提交", 200);
            outputParamMap.put("result", result);
            return result;
        }

        try {
            result = pjp.proceed();
        } catch (Exception e) {
            //让 GlobalExceptionHandler 统一处理
            throw e;
        } finally {
            //添加返回值
//        outputParamMap.put("result", result);
            //释放锁
            repeatSubmitService.deleteLock(methodSignature);
        }

        return result;
    }

    private String getParamsJsonTotal(ProceedingJoinPoint pjp) {
        String result = "";
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String method = request.getMethod();
        if (!"POST".equalsIgnoreCase(method)) {
            return "";
        }

        String contentType = request.getContentType();
        //不打印文件类型的参数
        if (!CommonUtil.isEmpty(contentType)
                && !contentType.contains("application/json")) {
            return "";
        }
        Object[] args = pjp.getArgs();
        if (CommonUtil.isEmpty(args)) {
            return "";
        }
        int i = 0;
        for (Object arg : args) {
            if (arg == null) {
                continue;
            }
            Class<?> aClass = arg.getClass();
            String simpleName = aClass.getSimpleName();
            if (!simpleName.toLowerCase().contains("request")) {
                String jsonString = JSONObject.toJSONString(arg);
                String line = "\n";
                if (i == 0) {
                    line = "";
                }
                result = StringUtil.merge(result, line, jsonString);
                i++;
            }
        }
        return result;
    }
}
