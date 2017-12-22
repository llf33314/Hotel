package com.gt.hotel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 移动端客房数量检测
 * TODO: 下单前检测客房数量是否
 * @author Reverien9@gmail.com
 * 2017年11月13日 下午4:16:35
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MobileRoomCheckAnnotation {
	String value();
}
