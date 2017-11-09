package com.gt.hotel.config;

import com.gt.hotel.interceptor.MobileAuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.gt.hotel.interceptor.SecurityInterceptor;
import org.springframework.web.servlet.config.annotation.*;

/**
 * SpringMVC 配置类
 *
 * @author zhangmz
 * @create 2017/6/20
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    // 快速解决页面转向问题
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/nav.html");
        registry.addViewController("/error").setViewName("/error/defaultError.html");
    }

    /**
     * 静态资源访问地址修改
     *
     * @param registry ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
        	.addResourceLocations("classpath:/pages/")
        	.addResourceLocations("classpath:/templates/")
        	.addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
        	.addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
        	.addResourceLocations("classpath:/META-INF/resources/webjars/");
        //		super.addResourceHandlers(registry);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
//        addInterceptor.addPathPatterns("/**");
//        addInterceptor.excludePathPatterns("/backstage/home", "/error", "/v2/**", "/webjars/**", "/swagger-resources/**");
        //        super.addInterceptors(registry);
        registry.addInterceptor(new MobileAuthenticationInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }



    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
            }
        };
    }

}

