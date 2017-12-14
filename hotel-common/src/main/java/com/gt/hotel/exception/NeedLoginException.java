package com.gt.hotel.exception;

import com.gt.hotel.enums.ResponseEnums;
import lombok.Getter;

/**
 * 登录异常
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/11/14
 * @since
 */
@Getter
public class NeedLoginException extends BaseException{

    private Integer busId;
    
    private Integer hotelId;

    public NeedLoginException(String message) {
        super(message);
    }

    public NeedLoginException(ResponseEnums responseEnums) {
        super(responseEnums);
    }

    public NeedLoginException(ResponseEnums responseEnums, String redirectUrl) {
        super(responseEnums, redirectUrl);
    }

    public NeedLoginException(ResponseEnums responseEnums,Integer busId, String redirectUrl) {
        super(responseEnums, redirectUrl);
        this.busId = busId;
    }
    
    public NeedLoginException(ResponseEnums responseEnums,Integer busId, Integer hotelId, String redirectUrl) {
    	super(responseEnums, redirectUrl);
    	this.busId = busId;
    	this.hotelId = hotelId;
    }

    public NeedLoginException(int code, String message) {
        super(code, message);
    }

    public NeedLoginException(int code, String message, String redirectUrl) {
        super(code, message, redirectUrl);
    }

    public NeedLoginException(int code, String message,Integer busId, String redirectUrl) {
        super(code, message, redirectUrl);
        this.busId = busId;
    }
    
    public NeedLoginException(int code, String message,Integer busId, Integer hotelId, String redirectUrl) {
    	super(code, message, redirectUrl);
    	this.busId = busId;
    	this.hotelId = hotelId;
    }
}
