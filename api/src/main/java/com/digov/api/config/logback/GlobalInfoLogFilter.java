package com.digov.api.config.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class GlobalInfoLogFilter extends Filter<ILoggingEvent> {
    /**
     * 过滤日志规则:
     * 1.loggerName 不为 com.digov.api.config.aop.AccessLogger 的日志进行记录,其他的不处理
     * 2.日志级别为info
     * @param event
     * @return
     */
    @Override
    public FilterReply decide(ILoggingEvent event) {
//        DENY：表示不用看后面的过滤器了，这里就给拒绝了，不作记录。
//        NEUTRAL：表示需不需要记录，还需要看后面的过滤器。若所有过滤器返回的全部都是NEUTRAL，那么需要记录日志。
//        ACCEPT：表示不用看后面的过滤器了，这里就给直接同意了，需要记录。

        String loggerName = event.getLoggerName();
//        System.out.println(loggerName);
        String ignoreLoggerName = "com.digov.api.config.aop.AccessLogger";
        if (ignoreLoggerName.equals(loggerName)) {
            return FilterReply.DENY;
        }
        return FilterReply.NEUTRAL;
    }
}
