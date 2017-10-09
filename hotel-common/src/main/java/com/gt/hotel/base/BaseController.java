package com.gt.hotel.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.gt.hotel.entity.BusUser;

/**
 * BaseController
 *
 * @author zhangmz
 * @create 2017/7/10
 */
public abstract class BaseController {
	
	private static final String SESSION_KEY = "hotel:session";
	
    /**
     * 日志
     */
    protected Logger logger = LoggerFactory.getLogger( this.getClass() );

    /**
     * 获取Sessionid
     *
     * @param session HttpSession
     *
     * @return
     */
    public String getSessionId( HttpSession session ) {
	return session.getId();
    }
    
    public BusUser getUser( HttpSession session ) {
    	BusUser bu = new BusUser();
    	bu.setId(33);
    	bu.setName("test user");
    	bu.setPhone(15012345678L);
    	return bu;
    }
    
    public BusUser getUser( HttpServletRequest request) {
    	Object object = request.getSession().getAttribute(SESSION_KEY);
    	BusUser bu = JSONObject.parseObject(object.toString(), BusUser.class);
    	return bu;
    }
}
