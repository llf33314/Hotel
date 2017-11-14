package com.gt.hotel.interceptor;

import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gt.api.bean.session.Member;
import com.gt.api.util.SessionUtils;
import com.gt.hotel.annotation.MobileLoginRequired;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.RedisCacheUtil;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.web.service.THotelService;

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

    /**
     *
     */
    public static final String CURRENT_BUS_ID     = "hotel:current_bus_id";
    /**
     *
     */
    public static final String CURRENT_HOTEL_INFO = "hotel:current_hotel_info";
    /**
     *
     */
    public static final String CURRENT_HOTEL_ID   = "hotel:current_hotel_id";
    /**
     *
     */
    public static final String HOTEL_ID           = "hotelId";

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
    	System.err.println(annotation);
        // 移动端有 @MobileLoginRequired 注解，需要验证
        if (annotation != null) {
            LOGGER.debug("sessionId : {} ", request.getSession().getId());
            // 酒店信息
            String hotelInfoJson = (String) request.getSession().getAttribute(CURRENT_HOTEL_INFO);
            // 从session中获取 当前商家ID
            Integer busId = (Integer) request.getSession().getAttribute(CURRENT_BUS_ID);
            Integer sessionHotelId = (Integer) request.getSession().getAttribute(CURRENT_HOTEL_ID);
            Map attribute = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            // hotelId
            Integer hotelId = MapUtils.getInteger(attribute, HOTEL_ID);
            if (busId != null && busId > 0) {
                // 访问地址的酒店ID 与 redis 内存储的hotelid 不匹配，则重新获取
                if (!hotelId.equals(sessionHotelId)) {
                    // 数据库查询
                    Wrapper<THotel> wrapper = new EntityWrapper<>();
                    wrapper.eq("id", hotelId).eq("bus_id", busId).eq("mark_modified", 0);
                    THotel hotel = this.hotelService.selectOne(wrapper);
                    if (hotel == null) {
                        LOGGER.warn("获取酒店信息失败 参数列表：hotelId : {} , busId : {} ", hotelId, busId);
                        throw new ResponseEntityException(ResponseEnums.DATA_DOES_NOT_EXIST);
                    }
                    hotelInfoJson = JSONObject.toJSONString(hotel);
                    // 并重置更新缓存信息
                    request.getSession().setAttribute(CURRENT_HOTEL_INFO, hotelInfoJson);
                    request.getSession().setAttribute(CURRENT_HOTEL_INFO, JSONObject.toJSONString(hotel));
                    request.getSession().setAttribute(CURRENT_BUS_ID, hotel.getBusId());
                    request.getSession().setAttribute(CURRENT_HOTEL_ID, hotelId);
                }
                return findMemberByBusId(request, response, busId);
            } else {
                if (hotelId != null) {
                    THotel hotel = findHotelById(hotelId);
                    // 并重置更新缓存信息
                    request.getSession().setAttribute(CURRENT_HOTEL_INFO, hotelInfoJson);
                    request.getSession().setAttribute(CURRENT_HOTEL_INFO, JSONObject.toJSONString(hotel));
                    request.getSession().setAttribute(CURRENT_BUS_ID, hotel.getBusId());
                    request.getSession().setAttribute(CURRENT_HOTEL_ID, hotelId);
                    return findMemberByBusId(request, response, hotel.getBusId());
                } else {
                    throw new ResponseEntityException(ResponseEnums.BAD_REQUEST);
                }
            }
        }
        return true;
    }

    /**
     * 获取(商家粉丝)会员信息
     * <p>
     * 获取失败后，会自动重定向至授权or登录地址
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param busId    商家ID
     * @return boolean
     * @throws Exception
     */
    private boolean findMemberByBusId(HttpServletRequest request, HttpServletResponse response, Integer busId) throws Exception {
        // 获取会员信息
        Member member = SessionUtils.getLoginMember(request, busId);
        if (member == null) {
            // 会员信息无法获取 则需要重新授权
            String redirectUrl = request.getRequestURL() + "";
            LOGGER.info("会员信息为空，进去登录或授权 , 参数: busId : {} , redirectUrl : {} ", busId, redirectUrl);
            // 会员信息为空 重定向授权
            // 重定向到授权/登录地址 重定向地址 为什么不加密?
            response.sendRedirect(authorizeMember(request, busId, redirectUrl));
            return false;
        }
        return true;
    }

    public THotel findHotelById(Integer hotelId) {
        Wrapper<THotel> wrapper = new EntityWrapper<>();
        wrapper.eq("id", hotelId).eq("mark_modified", 0);
        try {
            THotel hotel = this.hotelService.selectOne(wrapper);
            if (hotel == null) {
                LOGGER.error("酒店信息不存在，参数信息：hotelId:{} ", hotelId);
                throw new ResponseEntityException(ResponseEnums.DATA_DOES_NOT_EXIST);
            }
            return hotel;
        } catch (ResponseEntityException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            LOGGER.error("异常信息 : {} ", ex);
            throw new ResponseEntityException(ResponseEnums.UNKNOWN_ERROR);
        }
    }

    /**
     * 授权获取会员信息并重定向回来
     *
     * @param request
     * @param busId       商家ID
     * @param redirectUrl 重定向地址
     * @return URL地址
     * @throws Exception
     */
    private String authorizeMember(HttpServletRequest request, Integer busId, String redirectUrl) throws Exception {
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
            String guoqi = wxpublic.getString("guoqi");
            //商家已过期
            if (!StringUtils.isBlank(guoqi)) {
                Object guoqiUrl = wxpublic.get("guoqiUrl");
                return "redirect:" + guoqiUrl;
            }
            String remoteUcLogin = wxpublic.getString("remoteUcLogin");
            if (!StringUtils.isBlank(remoteUcLogin)) {
                return "";
            }
        }
        String otherRedisKey = "hotel" + System.currentTimeMillis();
        Map<String, Object> queryMap = new HashMap<>(3);
        // redis
        this.wxmpApiUtil.setRedisStorage(otherRedisKey, redirectUrl, null);
        LOGGER.debug("otherRedisKey : {} , redirectUrl : {}", otherRedisKey,redirectUrl);
        queryMap.put("browser", browser);
        queryMap.put("busId", busId);
        queryMap.put("uclogin", null);
        String params = URLEncoder.encode(JSON.toJSONString(queryMap), "utf-8");
        LOGGER.info("授权地址：{}{}", webServerConfigurationProperties.getWxmpService().getApiMap().get("authorizeMember") + params, JSON.toJSONString(queryMap));
        return webServerConfigurationProperties.getWxmpService().getApiMap().get("authorizeMember") + params;
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
        // 微信-1
        if (ua.indexOf("micromessenger") > 0) {
            result = 1;
        } else {//其他 -99
            result = 99;
        }
        return result;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(2);
        map.put("1", "23");
        map.put("2", "Map存储超过了");
        map.put("3", "Map存储超过了3");
        map.put("4", "Map存储超过了2");

        System.out.println(map.get("1"));
        System.out.println(map.get("2"));
        System.out.println(map.get("4"));
        System.out.println(map.size());
    }


}