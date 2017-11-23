package com.gt.hotel.config;

import com.gt.hotel.dto.ResponseErrorDTO;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.BaseException;
import com.gt.hotel.exception.BusinessException;
import com.gt.hotel.exception.NeedLoginException;
import com.gt.hotel.exception.ResponseEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常统一处理
 * <pre>
 *
 * </pre>
 *
 * @author zhangmz
 * @create 2017/6/21
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    /**
     * 默认异常处理
     *
     * @param request 请求信息
     * @param e       Exception
     * @return ResponseErrorDTO
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseErrorDTO defaultErrorHandler(HttpServletRequest request, Exception e) {
        logger.error("请求地址：{} , 系统异常详细：", request.getRequestURL(), e);
        return ResponseErrorDTO.createByErrorCodeMessage(ResponseEnums.SYSTEM_ERROR.getCode(), ResponseEnums.SYSTEM_ERROR.getMsg());
    }

    /**
     * 统一自定义异常处理
     *
     * @param request 请求信息
     * @param e       BaseException
     * @return ResponseErrorDTO
     */
    @ResponseBody()
    @ExceptionHandler(value = BaseException.class)
    public ResponseErrorDTO defaultCustomErrorHandler(HttpServletRequest request, BaseException e) {
        logger.error("异常原因：{} , 异常信息：{} , 请求地址：{}", e.getCause(), e.getMessage(), request.getRequestURL(), e);
        if (e instanceof ResponseEntityException || e instanceof BusinessException) {
            return ResponseErrorDTO.createByErrorCodeMessage(e.getCode(), e.getMessage(), e.getRedirectUrl());
        } else if (e instanceof NeedLoginException) {
            return ResponseErrorDTO.createByErrorCodeMessage(e.getCode(), e.getMessage(), ((NeedLoginException) e).getBusId(), e.getRedirectUrl());
        } else {
            return ResponseErrorDTO.createByErrorCodeMessage(ResponseEnums.ERROR.getCode(), e.getMessage(), e.getRedirectUrl());
        }
    }
}
