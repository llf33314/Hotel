package com.gt.hotel.exception;

import com.gt.hotel.enums.ResponseEnums;

/**
 * Ajax 异常
 * <pre>
 *     ajax 请求的异常处理类
 * </pre>
 *
 * @author zhangmz
 * @create 2017/6/21
 */
public class ResponseEntityException extends BaseException {

    /**
     * 构造函数
     * @param message 错误消息
     */
    public ResponseEntityException(String message) {
        super(message);
    }

    /**
     * 自定义异常
     * @param code 错误码
     * @param message 消息
     */
    public ResponseEntityException(int code, String message) {
        super(code, message);
    }

    /**
     * 构造异常信息
     * @param responseEnums 枚举
     */
    public ResponseEntityException(ResponseEnums responseEnums) {
        super(responseEnums);
    }

    /**
     * 枚举方式实现异常类
     *
     * @param responseEnums 枚举
     */
    public ResponseEntityException(ResponseEnums responseEnums, String redirectUrl) {
        super(responseEnums,redirectUrl);
    }

    /**
     * 实现code 和msg
     *
     * @param code        错误码
     * @param message     消息
     * @param redirectUrl 重定向地址
     */
    public ResponseEntityException(int code, String message, String redirectUrl) {
        super(code,message,redirectUrl);
    }

}
