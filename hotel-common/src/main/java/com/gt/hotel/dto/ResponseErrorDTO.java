package com.gt.hotel.dto;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gt.hotel.enums.ResponseEnums;
import lombok.Getter;

import java.io.Serializable;

import static com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing.DEFAULT_TYPING;

/**
 * Json 异常处理
 *
 * @author zhangmz
 * @create 2017/6/21
 */
@JsonSerialize(typing = DEFAULT_TYPING)
public class ResponseErrorDTO<T> extends ResponseDTO<T> implements Serializable {

    @Getter
    private String redirectUrl;
    @Getter
    private Integer busId;

    private ResponseErrorDTO(int status, String msg, T data, Integer busId, String url) {
        super(status, msg, data);
        this.busId = busId;
        this.redirectUrl = url;
    }

    public static <T> ResponseErrorDTO<T> createByError() {
        return createByErrorCodeMessage(ResponseEnums.ERROR.getCode(), ResponseEnums.ERROR.getMsg());
    }

    public static <T> ResponseErrorDTO<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return createByErrorCodeMessage(errorCode, errorMessage, null);
    }

    public static <T> ResponseErrorDTO<T> createByErrorCodeMessage(int errorCode, String errorMessage, String url) {
        return createByErrorCodeMessage(errorCode, errorMessage, null, url);
    }

    public static <T> ResponseErrorDTO<T> createByErrorCodeMessage(int errorCode, String errorMessage, Integer busId, String url) {
        return createByErrorCodeMessage(errorCode, errorMessage, null, busId, url);
    }

    public static <T> ResponseErrorDTO<T> createByErrorCodeMessage(int errorCode, String errorMessage, T data, Integer busId, String url) {
        return new ResponseErrorDTO<>(errorCode, errorMessage, data, busId, url);
    }


    public static void main(String[] args) {
        ResponseErrorDTO<Object> error = ResponseErrorDTO.createByErrorCodeMessage(ResponseEnums.ERROR.getCode(), ResponseEnums.ERROR.getMsg());
        System.out.println(" url " + error.getRedirectUrl());
        System.out.println(JSONObject.toJSON(error));
    }

}
