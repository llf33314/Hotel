package com.gt.hotel.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gt.api.bean.session.Member;
import com.gt.api.util.SessionUtils;
import com.gt.api.util.sign.SignHttpUtils;
import com.gt.hotel.annotation.MobileLoginRequired;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.RedisCacheUtil;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.web.service.THotelService;
import org.apache.commons.collections.MapUtils;
import org.apache.velocity.texen.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 移动端登录、微信授权拦截器
 * <p>
 * 在需要验证登录的Controller方法上添加@MobileLoginRequired 注解 拦截器将会生效
 * </p>
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/11/08
 * @since 1.0
 */
public class MobileAuthenticationInterceptor extends HandlerInterceptorAdapter {


    private static final Logger LOGGER        = LoggerFactory.getLogger(MobileAuthenticationInterceptor.class);
    // 微信地授权 取会员信息 重定向3次
    private static       int    redirectCount = 3;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Autowired
    private WXMPApiUtil wxmpApiUtil;

    @Autowired
    private WebServerConfigurationProperties webServerConfigurationProperties;

    @Autowired
    private THotelService hotelService;

    public static final String CURRENT_BUS_ID = "hotel:current_bus_id";

    public static final String CURRENT_HOTEL_INFO = "hotel:current_hotel_info";

    public static final String CURRENT_HOTEL_ID = "hotel:current_hotel_id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        MobileLoginRequired annotation = method.getAnnotation(MobileLoginRequired.class);
        // 移动端有 @MobileLoginRequired 注解，需要验证
        if (annotation != null) {
            // 从session中获取 当前商家ID
            Integer busId = (Integer) request.getSession().getAttribute(CURRENT_BUS_ID);
            // 酒店信息
            Object hotelInfo = request.getSession().getAttribute(CURRENT_HOTEL_INFO);
            if (busId != null) {
                Member member = SessionUtils.getLoginMember(request, busId);
                if (member == null) {
                    LOGGER.info("会员信息为空，进去登录或授权 , 参数: busId : {} , member : {}", busId, member);
                    // 会员信息为空 重定向授权
                    String redirectUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/" + request.getRequestURL();
                    // 重定向到授权/登录地址 重定向地址 为什么不加密?
                    response.sendRedirect(authorizeMember(request, busId, redirectUrl));
                    return false;
                }
            } else {
                // 获取不到busid 从路劲信息获取  当Busid 不为空并且当前酒店信息所属于商家的
                Map attribute = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                String id = MapUtils.getString(attribute, CURRENT_HOTEL_ID);
                LOGGER.info("酒店id : {}", id);

            }
        }
        return true;
    }

    /**
     * 授权获取会员信息并重定向回来
     *
     * @param request
     * @param busId       商家ID
     * @param redirectUrl 重定向地址
     * @return  URL地址
     * @throws Exception
     */
    protected String authorizeMember(HttpServletRequest request, Integer busId, String redirectUrl) throws Exception {
        LOGGER.debug("进入--授权方法！");
        Integer browser = judgeBrowser(request);
        //参数uclogin 如果uclogin不为空值  是指微信端是要通过授权  其他浏览器需要登录
        JSONObject wxpublic = wxmpApiUtil.getWxPulbicMsg(busId);
        Integer code = wxpublic.getInteger("code");
        //判断商家信息 1是否过期 2公众号是否变更过
        if (code.equals(-1)) {
            //请求错误
            return "";
        } else if (code.equals(0)) {
            Object guoqi = wxpublic.get("guoqi");
            if (!StringUtils.isEmpty(guoqi)) {//商家已过期
                Object guoqiUrl = wxpublic.get("guoqiUrl");
                return "redirect:" + guoqiUrl;
            }
            Object remoteUcLogin = wxpublic.get("remoteUcLogin");
            if (!StringUtils.isEmpty(remoteUcLogin)) {
                return "";
            }
        }
//        String otherRedisKey = getCode();
//        redisCacheUtil.set(otherRedisKey, redirectUrl, 5 * 60L);
        Map<String, Object> queryMap = new HashMap<>();
//        queryMap.put("otherRedisKey", "hotel:" + otherRedisKey);
        Map< String,Object > redisMap = new HashMap<>();
//        redisMap.put( "redisKey", otherRedisKey );
//        redisMap.put( "redisValue", requestUrl );
        redisMap.put( "setime", 300 );
        // wxmp 存取
        SignHttpUtils.WxmppostByHttp( "/8A5DA52E/redis/SetExApi.do", redisMap, "" );
        queryMap.put("browser", browser);
        queryMap.put("busId", busId);
        queryMap.put("uclogin", null);
        LOGGER.info("queryMap=" + JSON.toJSONString(queryMap));
        String params = URLEncoder.encode(JSON.toJSONString(queryMap), "utf-8");
        return "redirect:" + webServerConfigurationProperties.getMemberService().getApiMap().get("authorizeMember") + params;
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


}