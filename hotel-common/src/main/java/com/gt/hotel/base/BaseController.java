package com.gt.hotel.base;

import static com.gt.hotel.constant.CommonConst.CURRENT_BUS_ID;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gt.api.bean.session.BusUser;
import com.gt.api.bean.session.Member;
import com.gt.api.util.SessionUtils;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.RedisCacheUtil;
import com.gt.hotel.util.WXMPApiUtil;

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
    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    
    @Autowired
    protected RedisCacheUtil redisCacheUtil;
    
    @Autowired
    protected WXMPApiUtil wxmpApiUtil;

    @Autowired
    private WebServerConfigurationProperties webServerConfigurationProperties;
    
//    @Value("${wxmp.api.memberserverurl}")
//    private String memberUrl;

    /**
     * 获取Sessionid
     *
     * @param session HttpSession
     * @return
     */
    public String getSessionId(HttpSession session) {
        return session.getId();
    }

    /**
     * 暂时写死一个 id
     * TODO: 待完善 登录流程
     *
     * @param session HttpSession
     * @return int
     */
//    public Integer getLoginUserId(HttpSession session) {
//        Object o = session.getAttribute(CommonSessionConst.CURRENT_BUS_USER);
//        return 33;
//    }
    public BusUser getLoginUser(HttpServletRequest request) {
    	BusUser b = new BusUser();
    	b.setId(33);
    	return b;
//    	return SessionUtils.getLoginUser(request);
    }
    
    /**
     * 获取会员
     * @param request
     * @return
     */
    public Member getMember(HttpServletRequest request) {
    	Integer busId = (Integer) request.getSession().getAttribute(CURRENT_BUS_ID);
    	return SessionUtils.getLoginMember(request, busId);
    }
    
    /**
     * 参数校验
     *
     * @param result BindingResult
     */
    protected void InvalidParameter(BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errorList = result.getAllErrors();
            for (ObjectError error : errorList) {
                logger.warn(error.getDefaultMessage());
                throw new ResponseEntityException(error.getDefaultMessage());
            }
        }
    }
    
    /**
     * 参数校验
     *
     * @param result BindingResult
     * @return 
     */
    @SuppressWarnings("rawtypes")
	protected ResponseDTO InvalidParameterII(BindingResult result) {
    	if (result.hasErrors()) {
    		List<ObjectError> errorList = result.getAllErrors();
    		for (ObjectError error : errorList) {
    			logger.warn(error.getDefaultMessage());
    			return ResponseDTO.createByErrorMessage(error.getDefaultMessage());
    		}
    	}
		return null;
    }

    /**
     * @param request
     * @param map{    busId: 42,
     *                requestUrl: http://shuzheng.tunnel.qydev.com/login
     *                }
     * @return
     * @throws Exception
     */
    protected String authorizeMember(HttpServletRequest request, Map<String, Object> map) throws Exception {
        logger.debug("进入--授权方法！");
        Integer busId = Integer.valueOf(map.get("busId").toString());
        Integer browser = judgeBrowser(request);
        //参数uclogin 如果uclogin不为空值  是指微信端是要通过授权  其他浏览器不需要授权   反之其他浏览器需要登录
        Object uclogin = map.get("uclogin");

        JSONObject wxpublic = wxmpApiUtil.getWxPulbicMsg(busId);
        Integer code = Integer.parseInt(wxpublic.get("code").toString());
        //判断商家信息 1是否过期 2公众号是否变更过
        if (code.equals(-1)) {
            //请求错误
            return "";
        } else if (code.equals(0)) {
            Object guoqi = wxpublic.get("guoqi");
            //商家已过期
            if (!StringUtils.isEmpty(guoqi)) {
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
        redisCacheUtil.set(otherRedisKey, requestUrl, 5*60L);
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("otherRedisKey", "hotel:" + otherRedisKey);
        queryMap.put("browser", browser);
        queryMap.put("busId", busId);
        queryMap.put("uclogin", uclogin);
        logger.info("queryMap=" + JSON.toJSONString(queryMap));
        String params = URLEncoder.encode(JSON.toJSONString(queryMap), "utf-8");
        String authorizeMemberNew = webServerConfigurationProperties.getMemberService().getApiMap().get("authorizeMemberNew");
        return "redirect:" + authorizeMemberNew + params;
    }
    
    /**
     * 判断浏览器
     *
     * @return 1:微信浏览器,99:其他浏览器
     */
    public static Integer judgeBrowser(HttpServletRequest request) {
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
    
    public static String getCode() {
        Long date = System.currentTimeMillis();
        String cardNo = date.toString().substring(1);
        return cardNo;
    }
    
    public static String getHost(HttpServletRequest request) {
		String path = request.getContextPath();
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    }
}
