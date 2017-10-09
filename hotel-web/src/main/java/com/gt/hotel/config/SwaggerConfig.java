package com.gt.hotel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
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
    private static final Contact CONTACT             = new Contact("zhangmz", "http://www.zhangmz.me", "3001417980@qq.com");
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
			.forCodeGeneration(true).apiInfo(apiInfo()).tags(new Tag("Hotel-Applet", "小译 - Hotel-Applet-API"), getAppletTags()).select()
			.apis(RequestHandlerSelectors.basePackage(APPLET_PACKAGE)).paths(PathSelectors.any()).build();
    }

    /**
     * 定义标签
     * 标签等于模块：分组管理API接口
     *
     * @return Tag[]
     */
    private Tag[] getAppletTags() {
	Tag[] tags = {new Tag("客房管理", "客房管理相关接口"), new Tag("酒店管理", "酒店管理相关接口")};
	return tags;
    }

    /**
     * erp 接口
     *
     * @return
     */
    @Bean
    public Docket erpRestApi() {
	return new Docket(DocumentationType.SWAGGER_2).enable(swaggerShow).groupName("erp").genericModelSubstitutes(DeferredResult.class).useDefaultResponseMessages(false)
			.forCodeGeneration(true).apiInfo(apiInfo()).tags(new Tag("Hotel-ERP", "小译 - Hotel-ERP-API"), getAppletTags()).select()
			.apis(RequestHandlerSelectors.basePackage(ERP_PACKAGE)).paths(PathSelectors.any()).build();
    }

    /**
     * 定义标签
     * 标签等于模块：分组管理API接口
     *
     * @return Tag[]
     */
    private Tag[] getERPTags() {
	Tag[] tags = {new Tag("客房管理", "客房管理相关接口"), new Tag("酒店管理", "酒店管理相关接口")};
	return tags;
    }

    /**
     * 后台管理接口
     *
     * @return
     */
    @Bean
    public Docket backRestApi() {
	return new Docket(DocumentationType.SWAGGER_2).enable(swaggerShow).groupName("back").genericModelSubstitutes(DeferredResult.class).useDefaultResponseMessages(false)
			.forCodeGeneration(true).apiInfo(apiInfo()).tags(new Tag("Hotel-Back", "小译 - Hotel-Back-API"), getBackTags()).select()
			.apis(RequestHandlerSelectors.basePackage(BACK_PACKAGE)).paths(PathSelectors.any()).build();
    }

    /**
     * 定义标签
     * 标签等于模块：分组管理API接口
     *
     * @return Tag[]
     */
    private Tag[] getBackTags() {
	Tag[] tags = {new Tag("客房管理", "客房管理相关接口"), new Tag("酒店管理", "酒店管理相关接口")};
	return tags;
    }

    /**
     * 移动端接口
     *
     * @return
     */
    @Bean
    public Docket mobileRestApi() {
	return new Docket(DocumentationType.SWAGGER_2).enable(swaggerShow).groupName("mobile").genericModelSubstitutes(DeferredResult.class).useDefaultResponseMessages(false)
			.forCodeGeneration(true).apiInfo(apiInfo()).tags(new Tag("Hotel-Mobile", "小译 - Hotel-Mobile-API"), getMobileTags()).select()
			.apis(RequestHandlerSelectors.basePackage(MOBILE_PACKAGE)).paths(PathSelectors.any()).build();
    }

    /**
     * 定义标签
     * 标签等于模块：分组管理API接口
     *
     * @return Tag[]
     */
    private Tag[] getMobileTags() {
	Tag[] tags = {new Tag("客房管理", "客房管理相关接口"), new Tag("酒店管理", "酒店管理相关接口")};
	return tags;
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


   /* @Bean
    public Docket testApi() {
	return new Docket(DocumentationType.SWAGGER_2)
			.enable(this.swaggerShow)
			.groupName("test")
			.genericModelSubstitutes(DeferredResult.class)
			.useDefaultResponseMessages(false)
			.forCodeGeneration(true)
			.pathMapping("/")// base，最终调用接口后会和paths拼接在一起
			.select()
			.paths(or(regex("/test/.*")))//过滤的接口
			.build()
			.apiInfo(apiInfo());
    }*/

}
