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

    private int code;//状态码

    private String message;//错误消息

    public BaseException(String message) {
        super(message);
        this.code = ResponseEnums.ERROR.getCode();
        this.message = message;
    }

    /**
     * 枚举方式实现异常类
     *
     * @param responseEnums
     */
    public BaseException(ResponseEnums responseEnums) {
        super(responseEnums.getMsg());
        this.message = responseEnums.getMsg();
        this.code = responseEnums.getCode();
    }

    /**
     * 实现code 和msg
     *
     * @param code
     * @param message
     */
    public BaseException(int code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
