package com.gt.hotel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

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
}
