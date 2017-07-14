package com.gt.hotel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gt.hotel.enums.ResponseEnums;

import java.io.Serializable;

/**
 * Json 异常处理
 *
 * @author zhangmz
 * @create 2017/6/21
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ErrorInfo<T> extends ServerResponse<T> implements Serializable {

    private String url;

    private ErrorInfo(int status, String msg, T data, String url) {
        super(status, msg, data);
        this.url = url;
    }

    public static <T> ErrorInfo<T> createByError() {
        return createByErrorCodeMessage( ResponseEnums.ERROR.getCode(), ResponseEnums.ERROR.getDesc() );
    }

    public static <T> ErrorInfo<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return createByErrorCodeMessage(errorCode, errorMessage, null);
    }

    public static <T> ErrorInfo<T> createByErrorCodeMessage(int errorCode, String errorMessage, String url) {
        return createByErrorCodeMessage(errorCode, errorMessage, null, url);
    }

    public static <T> ErrorInfo<T> createByErrorCodeMessage(int errorCode, String errorMessage, T data, String url) {
        return new ErrorInfo<>(errorCode, errorMessage, data, url);
    }

    public String getUrl() {
        return url;
    }

}
