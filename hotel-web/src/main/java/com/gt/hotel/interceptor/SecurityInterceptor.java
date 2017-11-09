package com.gt.hotel.interceptor;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gt.api.bean.session.Member;
import com.gt.api.util.SessionUtils;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.util.RedisCacheUtil;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.web.service.THotelService;

/**
 * 拦截器
 * @author Reverien9@gmail.com
 * 2017年11月8日 下午2:54:16
 */
@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {
	
	protected static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);
	
	@Value("${wxmp.api.memberserverurl}")
    private String memberUrl;
	
	@Autowired
    RedisCacheUtil redisCacheUtil;
    
    @Autowired
    WXMPApiUtil wxmpApiUtil;
    
    @Autowired
	THotelService tHotelService;
	
	private List<String> patterns;
	
	{
		patterns = new ArrayList<>();
		patterns.add(".*78CDF1/\\d+/home.*");
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.err.println(request.getLocalAddr() + " " + request.getRequestURL());
		System.err.println(regex(request.getRequestURL().toString()));
//		if (SessionUtils.getLoginUser(request) != null)
		if(request.getRequestURL().indexOf("78CDF1") != -1) {
//			if(regex(request.getRequestURL().toString())) {
//				//
//				THotel hotel = tHotelService.selectById(hotelId);
//		    	Member member = SessionUtils.getLoginMember(request, hotel.getBusId());
//		    	if(StringUtils.isEmpty(member) || StringUtils.isEmpty(member.getId())) {
//		    		Map<String, Object> param = new HashMap<>();
//		    		param.put("busId", hotel.getBusId());
//		    		param.put("requestUrl", request.getRequestURL().toString());
//		    		String url = null;
//					try {
//						url = authorizeMember(request, param);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//		    		if(!StringUtils.isEmpty(url)) {
//		    			response.sendRedirect(url);
//		    			return false;
//		    		}
//		    	}
//		    	//
//			}else {
				return true;
//			}
		}else {
//			response.sendRedirect("/");
//			return false;
			return true;            	
		}
	}
	
	private boolean regex(String url) {
		for(String pattern : patterns) {
			if(Pattern.matches(pattern, url)) {
				return true;
			}
		}
		return false;
	}
	
	/**
     * @param request
     * @param map{    busId: 42,
     *                requestUrl: http://shuzheng.tunnel.qydev.com/login
     *                }
     * @return
     * @throws Exception
     */
    private String authorizeMember(HttpServletRequest request, Map<String, Object> map) throws Exception {
     /*   map.put("busId", 33);
        map.put("requestUrl", "http://shuzheng.tunnel.qydev.com/login");*/
        logger.debug("进入--授权方法！");
        
        Integer busId = Integer.valueOf(map.get("busId").toString());
        Integer browser = judgeBrowser(request);
        Object uclogin = map.get("uclogin");	//参数uclogin 如果uclogin不为空值  是指微信端是要通过授权  其他浏览器不需要授权   反之其他浏览器需要登录
        
        JSONObject wxpublic = wxmpApiUtil.getWxPulbicMsg(busId);
        Integer code = Integer.parseInt(wxpublic.get("code").toString());
        if (code.equals(-1)) {	//判断商家信息 1是否过期 2公众号是否变更过
            return "";//请求错误
        } else if (code.equals(0)) {
            Object guoqi = wxpublic.get("guoqi");
            if (!StringUtils.isEmpty(guoqi)) {//商家已过期
                Object guoqiUrl = wxpublic.get("guoqiUrl");
                return "redirect:" + guoqiUrl;
            }
            Object remoteUcLogin = wxpublic.get("remoteUcLogin");
            if (!StringUtils.isEmpty(uclogin) || !StringUtils.isEmpty(remoteUcLogin)) {
                return "";
            }
        }
        String requestUrl = map.get("requestUrl").toString();
        String otherRedisKey = getCode();
//        commRedisService.setExApi(otherRedisKey, requestUrl, 5 * 60L);
        redisCacheUtil.set(otherRedisKey, requestUrl, 5*60L);
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("otherRedisKey", "hotel:" + otherRedisKey);
        queryMap.put("browser", browser);
        queryMap.put("busId", busId);
        queryMap.put("uclogin", uclogin);
        logger.info("queryMap=" + JSON.toJSONString(queryMap));
        String params = URLEncoder.encode(JSON.toJSONString(queryMap), "utf-8");
        return "redirect:" + memberUrl + "/remoteUserAuthoriPhoneController/79B4DE7C/authorizeMember.do?queryBody=" + params;
    }
    
    /**
     * 判断浏览器
     *
     * @return 1:微信浏览器,99:其他浏览器
     */
    private static Integer judgeBrowser(HttpServletRequest request) {
    	Integer result = null;
    	String ua = request.getHeader("user-agent")
    			.toLowerCase();
    	if (ua.indexOf("micromessenger") > 0) {// 微信-1
    		result = 1;
    	} else {//其他 -99
    		result = 99;
    	}
    	return result;
    }
    
    private static String getCode() {
        Long date = System.currentTimeMillis();
        String cardNo = date.toString().substring(1);
        return cardNo;
    }
    
}
