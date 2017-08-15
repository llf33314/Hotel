package com.gt.hotel.base;

import com.gt.hotel.entity.BusUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * BaseController
 *
 * @author zhangmz
 * @create 2017/7/10
 */
public abstract class BaseController {
    /**
     * 日志
     */
    protected static final Logger logger = LoggerFactory.getLogger( BaseController.class );

    /**
     * 获取Sessionid
     *
     * @param session HttpSession
     *
     * @return
     */
    protected String getSessionId( HttpSession session ) {
	return session.getId();
    }

    protected BusUser getUser( HttpSession session ) {
        BusUser bu = new BusUser();
        bu.setId(1);
        bu.setName("test user");
        bu.setPhone(15012345678L);
        return bu;
    }
}
