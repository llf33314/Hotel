package com.gt.hotel.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * Controller 抽象类
 *
 * @author zhangmz
 * @create 2017/6/20
 */
public abstract class BaseController {
    /** 日志 */
    protected static final Logger logger = LoggerFactory.getLogger( BaseController.class );

    /**
     * 获取Sessionid
     *
     * @return sessionid
     */
    public String getSessionId( HttpSession session ) {
	return session.getId();
    }
}
