package com.gt.hotel.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis-plus 配置类
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



