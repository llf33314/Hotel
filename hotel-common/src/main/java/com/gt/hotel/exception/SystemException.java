package com.gt.hotel.exception;

import com.gt.hotel.enums.ResponseEnums;

/**
 * 系统统一异常类
 * <pre>
 *     所有自定义的异常，都继承此类。
 * </pre>
 *
 * @author zhangmz
 * @create 2017/6/16
 */
public class SystemException extends RuntimeException {

    private int code;//状态码

    private String message;//错误消息

    /**
     * @param message
     */
    public SystemException( String message ) {
	super( message );
	this.message = message;
    }

    /**
     * 枚举方式实现异常类
     * @param responseEnums
     */
    public SystemException( ResponseEnums responseEnums ) {
	super( responseEnums.getMsg() );
	this.message = responseEnums.getMsg();
	this.code = responseEnums.getCode();
    }

    /**
     * 实现code 和msg
     *
     * @param code
     * @param message
     */
    public SystemException( int code, String message ) {
	super( message );
	this.message = message;
	this.code = code;
    }

    public int getCode() {
	return code;
    }

    @Override
    public String getMessage() {
	return message;
    }
}
