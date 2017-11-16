package com.gt.hotel.exception;

import com.gt.hotel.enums.ResponseEnums;
import lombok.Getter;

/**
 * 系统统一异常类
 * <pre>
 *     所有自定义的异常，都继承此类。
 * </pre>
 *
 * @author zhangmz
 * @create 2017/6/16
 */
@Getter
public class BaseException extends RuntimeException {
    /**
     * 状态码
     */
    private int    code;
    /**
     * 错误消息
     */
    private String message;
    /**
     * 重定向地址
     */
    private String redirectUrl;
    /**
     * 数据包 Json
     */
    private String data;

    public BaseException(String message) {
        super(message);
        this.code = ResponseEnums.ERROR.getCode();
        this.message = message;
    }

    /**
     * 枚举方式实现异常类
     *
     * @param responseEnums 枚举
     */
    public BaseException(ResponseEnums responseEnums) {
        super(responseEnums.getMsg());
        this.message = responseEnums.getMsg();
        this.code = responseEnums.getCode();
    }

    /**
     * 枚举方式实现异常类
     *
     * @param responseEnums 枚举
     */
    public BaseException(ResponseEnums responseEnums, String redirectUrl) {
        super(responseEnums.getMsg());
        this.message = responseEnums.getMsg();
        this.code = responseEnums.getCode();
        this.redirectUrl = redirectUrl;
    }

    /**
     * 实现code 和msg
     *
     * @param code    错误码
     * @param message 消息
     */
    public BaseException(int code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    /**
     * 实现code 和msg
     *
     * @param code        错误码
     * @param message     消息
     * @param redirectUrl 重定向地址
     */
    public BaseException(int code, String message, String redirectUrl) {
        super(message);
        this.message = message;
        this.code = code;
        this.redirectUrl = redirectUrl;
    }


    public int getCode() {
        return this.code;
    }

}
