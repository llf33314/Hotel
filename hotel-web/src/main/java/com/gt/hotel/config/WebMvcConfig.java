package com.gt.hotel.config;

import com.gt.hotel.filter.RepeatedlyReadFilter;
import com.gt.hotel.interceptor.RepeatedlyReadInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * SpringMVC 配置类
 *
 * @author zhangmz
 * @create 2017/6/20
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    //快速解决页面转向问题
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
	registry.addViewController("/").setViewName("/index.html");
	registry.addViewController("/error").setViewName("/error/defaultError.html");
    }

    /**
     * 静态资源访问地址修改
     *
     * @param registry ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/**").addResourceLocations("classpath:/pages/").addResourceLocations("classpath:/static/");
	super.addResourceHandlers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
	registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
	super.addCorsMappings(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new RepeatedlyReadInterceptor()).addPathPatterns("/**");
	super.addInterceptors(registry);
    }

    @Bean
    public FilterRegistrationBean repeatedlyReadFilter() {
	FilterRegistrationBean registration = new FilterRegistrationBean();
	RepeatedlyReadFilter repeatedlyReadFilter = new RepeatedlyReadFilter();
	registration.setFilter(repeatedlyReadFilter);
	registration.addUrlPatterns("/*");
	return registration;
    }
}
