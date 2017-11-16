package com.gt.hotel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在需要验证登录的Controlller的方法上使用次注解
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/11/08
 * @since 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MobileLoginRequired {

}
