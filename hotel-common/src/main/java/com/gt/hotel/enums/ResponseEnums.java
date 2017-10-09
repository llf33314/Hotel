package com.gt.hotel.enums;

/**
 * 响应成功Code 类
 *
 * @author zhangmz
 * @create 2017/6/12
 */
public enum ResponseEnums {
    SUCCESS(0, "成功"),
    ERROR(1, "错误"),
    NEED_LOGIN(1001, "需要登录"),
    AUTHENTICATION(10, "非法认证"),
    JWT_TOKEN_EXPIRED(11, "TOKEN失效"),
    SYSTEM_ERROR(9999, "系统错误");

    private final int    code;
    private final String msg;

    ResponseEnums(int code, String msg) {
	this.code = code;
	this.msg = msg;
    }

    public int getCode() {
	return code;
    }

    public String getMsg() {
	return msg;
    }
}
