package com.gt.hotel.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.gt.hotel.interceptor.MobileAuthenticationInterceptor;

/**
 * SpringMVC 配置类
 *
 * @author zhangmz
 * @create 2017/6/20
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    @Bean
    MobileAuthenticationInterceptor mobileAuthenticationInterceptor() {
        return new MobileAuthenticationInterceptor();
    }


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
        registry.addInterceptor(mobileAuthenticationInterceptor()).addPathPatterns("/mobile/**");
        super.addInterceptors(registry);
    }


    /**
     * 跨域配置
     * 默认设置全局跨域配置
     * TODO: 部署服务器需要注释掉。因为，nginx已配置跨域。否则会起冲突
     *
     * @param registry Corsregistry
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }



}

