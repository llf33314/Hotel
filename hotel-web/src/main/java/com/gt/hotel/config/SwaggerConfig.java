package com.gt.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * RestFul API 文档
 *
 * @author zhangmz
 * @create 2017/6/15
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 可以定义多个组，比如本类中定义把test和demo区分开了 （访问页面就可以看到效果了）
     */
    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 获取接口的Package包
                .apis(RequestHandlerSelectors.basePackage("com.gt.demo.core.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("SpringBootDemo", // 大标题
                "SpringBootDemo", // 小标题
                "1.0", // 版本
                "NO terms of service", new Contact("zhangmz", "http://www.zhangmz.me", "3001417980@qq.com"), // 作者
                "", // 链接显示文字
                ""// 网站链接
        );
    }
}
