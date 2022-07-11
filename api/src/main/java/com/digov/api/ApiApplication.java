package com.digov.api;

import com.digov.api.config.listener.application.ApplicationEventListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.digov.api.repository")
@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApiApplication.class);
        app.addListeners(new ApplicationEventListener());
        app.run(args);
    }

}
