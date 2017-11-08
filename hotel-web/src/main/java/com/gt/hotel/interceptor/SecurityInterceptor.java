package com.gt.hotel.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 拦截器
 * @author Reverien9@gmail.com
 * 2017年11月8日 下午2:54:16
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.err.println(request.getLocalAddr() + " " + request.getRequestURL());
		if(request.getRequestURL().indexOf("78CDF1") != -1) {
			return true;            	
		}
		/*if (SessionUtils.getLoginUser(request) != null)*/
		return true;
		//            response.sendRedirect("/");
		//            return false;
	}
	
}
