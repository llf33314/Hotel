package com.gt.hotel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 酒店ERP 程序入口
 *
 * @author zhangmz
 * @create 2017/7/8
 */
@MapperScan( "com.gt.hotel.dao" )
@ServletComponentScan
@SpringBootApplication
public class HotelApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder application ) {
	return application.sources( HotelApplication.class );
    }

    public static void main( String[] args ) {
    	SpringApplication.run( HotelApplication.class, args );
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                	.addMapping("/**")
                	.allowedMethods("*")
                	.allowedOrigins("*");
            }
        };
    }
    
}
