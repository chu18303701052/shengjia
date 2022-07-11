package com.digov.api.config.swagger2;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Set;

/**
 * @Author scott
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config implements WebMvcConfigurer {

    /**
     * 是否启用
     */
    @Value("${swagger.if.use}")
    private boolean ifUse;

    /**
     * 域名
     */
    @Value("${swagger.domain}")
    private String domain;
    /**
     * 项目名
     */
    @Value("${swagger.project.name}")
    private String projectName;


    /**
     * 显示swagger-ui.html文档展示页，还必须注入swagger资源：
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 企业端接口
     *
     * @return
     */
    @Bean
    public Docket apiApp() {

        Predicate<RequestHandler> predicate = input -> {
            if (input.isAnnotatedWith(ApiOperation.class)) {
                Set<String> stringSet = input.getPatternsCondition().getPatterns();
                for (String string : stringSet) {
                    if (string.startsWith("/pc/")) {
                        return true;
                    }
                }
            }
            return false;
        };

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(projectName.concat("demo项目接口文档"))
                .protocols(Sets.newHashSet("http"))
                .select()
                .apis(predicate)
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .enable(ifUse)
                ;

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 任意，请稍微规范点
                .title(projectName.concat("项目文档"))
                // 任意，请稍微规范点
                .description(projectName.concat("后台API接口"))
                // 将“url”换成自己的ip:port
                .termsOfServiceUrl(domain)
                .version("1.1.0")
                .build();
    }
}
