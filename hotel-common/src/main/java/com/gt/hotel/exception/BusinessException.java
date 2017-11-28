package com.gt.hotel.exception;

import com.gt.hotel.enums.ResponseEnums;

/**
 * 业务异常
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/08/03
 * @since 1.0.0
 */
public class BusinessException extends BaseException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code, String message) {
        super(code, message);
    }

    public BusinessException(ResponseEnums responseEnums) {
        super(responseEnums);
    }
}
