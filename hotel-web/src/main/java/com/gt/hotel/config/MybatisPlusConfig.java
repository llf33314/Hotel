package com.gt.hotel.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Mybatis-plus 配置类
 * <pre>
 *     @update 2017-12-25 开启SQL效率分析 仅在 开发与测试环境 执行
 * </pre>
 *
 * @author zhangmz
 * @create 2017/6/20
 */
@MapperScan("com.gt.hotel.dao")
@Configuration
public class MybatisPlusConfig {


    /**
     * mybatis-plus分页插件
     *
     * @return PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 开启 PageHelper 的支持
        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }

    /**
     * SQL执行效率插件
     * 设置 dev test 环境开启
     */
    @Bean
    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /*@Component
    class HotelCommonMetaObjectHandler extends MetaObjectHandler {

        @Override
        public void insertFill(MetaObject metaObject) {
            Object createdBy = metaObject.getValue("createdBy");

            Object createdAt = metaObject.getValue("createdAt");

            //获取当前登录用户
            Integer id = 333;

            if (null == createdBy) {
                metaObject.setValue("createdBy", id);

            }
            if (null == createdAt) {
                metaObject.setValue("createdAt", new Date());

            }
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            Object updatedBy = metaObject.getValue("updatedBy");

            Object updatedAt = metaObject.getValue("updatedAt");

            //获取当前登录用户
            Integer id = 333;

            if (null == updatedBy) {
                metaObject.setValue("updatedBy", id);

            }
            if (null == updatedAt) {
                metaObject.setValue("updatedAt", new Date());

            }
        }
    }*/

}



