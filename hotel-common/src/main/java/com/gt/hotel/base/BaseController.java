package com.gt.hotel.base;

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
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 获取Sessionid
     *
     * @param session HttpSession
     *
     * @return
     */
    public String getSessionId(HttpSession session) {
	return session.getId();
    }
    
   /* public BusUser getUser( HttpSession session ) {
    	BusUser bu = new BusUser();
    	bu.setId(33);
    	bu.setName("test user");
    	bu.setPhone(15012345678L);
    	return bu;
    }*/

    /**
     * 暂时写死一个 id
     * TODO: 待完善 登录流程
     *
     * @param session HttpSession
     *
     * @return int
     */
    public Integer getLoginUserId(HttpSession session) {
	//       Object o = session.getAttribute(CommonSessionConst.CURRENT_BUS_USER);
	return 33;
    }
}
