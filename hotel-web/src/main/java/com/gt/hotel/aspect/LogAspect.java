package com.gt.hotel.aspect;

import com.gt.hotel.annotation.LogAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * <pre>
 * 1.0.0 2017/12/20 zhangmz -
 * ---------------------------------------------------------------------------------------
 * Version Date Author Description
 * 操作日志记录
 * ---------------------------------------------------------------------------------------
 * </pre>
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017-12-20
 * @since 1.0.0
 */

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Before("@annotation(logAop)")
    public void beforeTest(JoinPoint joinPoint, LogAop logAop) throws Throwable {
        // TODO: 2017/12/20 完善日志功能
        log.info("进入：" + logAop.name());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        log.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
    }

    @After("@annotation(logAop)")
    public void afterTest(JoinPoint point, LogAop logAop) {
        log.info("退出：" + logAop.name());
    }
}
