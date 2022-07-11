package com.digov.api.config.exception;

import com.digov.api.entity.result.R;
import com.digov.api.util.common.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R<String> globalExceptionHandler(Exception e) {
        String exceptionInfo = CommonUtil.getExceptionInfo(e);
        log.error("全局异常捕获:{}", exceptionInfo);
        R<String> r = R.error("服务器忙，稍后再试");
        return r;
    }

    /**
     * 处理自定义的业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public R<String> customExceptionHandler(CustomException e) {
        String exceptionInfo = CommonUtil.getExceptionInfo(e);
        log.error("自定义异常捕获:{}", exceptionInfo);
        R<String> r = R.error(e.getErrMsg(), e.getErrCode());
        return r;
    }
}
