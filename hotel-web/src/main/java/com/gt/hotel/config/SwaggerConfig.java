package com.gt.hotel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
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
    @Value( "${swagger.show}" )
    private boolean swaggerShow;

    // 扫描包
    private static final String  APPLET_PACKAGE      = "com.gt.hotel.controller.applet";
    private static final String  ERP_PACKAGE         = "com.gt.hotel.controller.erp";
    private static final String  BACK_PACKAGE        = "com.gt.hotel.controller.back";
    private static final String  MOBILE_PACKAGE      = "com.gt.hotel.controller.mobile";
    // 标题
    private static final String  TITLE               = "Hotel - API";
    // 描述
    private static final String  DESC                = "酒店ERP";
    // 版本
    private static final String  VERSION             = "1.0.0";
    //
    private static final String  TERMS_OF_SERVICEURL = "NO terms of service";
    // 作者信息
    private static final Contact CONTACT             = null;
    //
    private static final String  LICENSE             = "swagger-ui";
    //
    private static final String  LICENSE_URL         = "/swagger/index.html";
	
	/* .tags 第一个参数必须是Tag，后面的是 Tag 类型的可选参数 new Tag(String,String) 第一个参数是key，第二个参数是Value。注解@Api#tags传入的是tag的key */
    /** * 可以定义多个组，比如本类中定义把test和demo区分开了 * （访问页面就可以看到效果了） * */
	
	/* */

    /**
     * 小程序接口
     *
     * @return
     */
    @Bean
    public Docket appletRestApi() {
	return new Docket(DocumentationType.SWAGGER_2).enable(swaggerShow).groupName("applet").genericModelSubstitutes(DeferredResult.class).useDefaultResponseMessages(false)
			.forCodeGeneration(true).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage(APPLET_PACKAGE)).paths(PathSelectors.any()).build();
    }

    /**
     * erp 接口
     *
     * @return
     */
    @Bean
    public Docket erpRestApi() {
	return new Docket(DocumentationType.SWAGGER_2).enable(swaggerShow).groupName("erp").genericModelSubstitutes(DeferredResult.class).useDefaultResponseMessages(false)
			.forCodeGeneration(true).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage(ERP_PACKAGE)).paths(PathSelectors.any()).build();
    }

    /**
     * 后台管理接口
     *
     * @return
     */
    @Bean
    public Docket backRestApi() {
	return new Docket(DocumentationType.SWAGGER_2).enable(swaggerShow).groupName("back").genericModelSubstitutes(DeferredResult.class).useDefaultResponseMessages(false)
			.forCodeGeneration(true).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage(BACK_PACKAGE)).paths(PathSelectors.any()).build();
    }

    /**
     * 移动端接口
     *
     * @return
     */
    @Bean
    public Docket mobileRestApi() {
	return new Docket(DocumentationType.SWAGGER_2).enable(swaggerShow).groupName("mobile").genericModelSubstitutes(DeferredResult.class).useDefaultResponseMessages(false)
			.forCodeGeneration(true).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage(MOBILE_PACKAGE)).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
	return new ApiInfo(TITLE, // 大标题
			DESC, // 小标题
			VERSION, // 版本
			TERMS_OF_SERVICEURL, CONTACT, // 作者
			LICENSE, // 链接显示文字
			LICENSE_URL// 网站链接
	);
    }

}
