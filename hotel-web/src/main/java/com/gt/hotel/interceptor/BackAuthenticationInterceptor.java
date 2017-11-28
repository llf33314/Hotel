package com.gt.hotel.interceptor;

import com.gt.api.bean.session.BusUser;
import com.gt.api.util.SessionUtils;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.NeedLoginException;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台登录拦截器
 * @author Reverien9@gmail.com
 * 2017年11月16日 下午5:44:24
 */
public class BackAuthenticationInterceptor extends HandlerInterceptorAdapter {


    @SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(BackAuthenticationInterceptor.class);

    @Autowired
    private WebServerConfigurationProperties properties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	BusUser busUser = SessionUtils.getLoginUser(request);
//    	if(busUser == null) {
//    		throw new NeedLoginException(ResponseEnums.NEED_LOGIN, null, properties.getWxmpService().getWxmpLogin());
//    	}
        return true;
    }
}