package com.digov.api.config.listener.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class ApplicationEventListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            printLog("环境初始化！！");
        } else if (event instanceof ApplicationPreparedEvent) {
            printLog("初始化完成！！");
        } else if (event instanceof ContextRefreshedEvent) {
            printLog("应用刷新！！");
        } else if (event instanceof ApplicationReadyEvent) {
            printLog("项目启动完成！！");

            this.showTipInfo(event);
        } else if (event instanceof ContextStartedEvent) {
            printLog("应用启动！！");
        } else if (event instanceof ContextStoppedEvent) {
            printLog("项目停止！！");
        } else if (event instanceof ContextClosedEvent) {
            printLog("应用关闭！！");
        }
    }

    private void printLog(String info){
        log.info(info);
    }

    /**
     * 项目启动成功后的打印信息
     */
    private void showTipInfo(ApplicationEvent event){
        /********** 打印文档地址信息 ***********/
        ConfigurableApplicationContext applicationContext = ((ApplicationReadyEvent) event).getApplicationContext();
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String swaggerUrl = environment.getProperty("swagger.domain");
        printLog("swagger访问地址：" + swaggerUrl + "doc.html");


    }
}
