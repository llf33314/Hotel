package com.gt.hotel.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 1.0.0 2017/12/20 zhangmz -
 * ---------------------------------------------------------------------------------------
 * Version Date Author Description
 * ---------------------------------------------------------------------------------------
 * </pre>
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017-12-20
 * @since 1.0.0
 */
@Target(ElementType.METHOD)//字段注解 , 用于描述方法
@Retention(RetentionPolicy.RUNTIME)//在运行期保留注解信息
public @interface LogAop {
    String name() default "Log";
}
