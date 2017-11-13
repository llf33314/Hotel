package com.gt.hotel.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gt.api.bean.session.Member;
import com.gt.api.util.SessionUtils;
import com.gt.api.util.sign.SignHttpUtils;
import com.gt.hotel.annotation.MobileLoginRequired;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.RedisCacheUtil;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.web.service.THotelService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public static final String BUS_ID             = "busId";
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
        // 移动端有 @MobileLoginRequired 注解，需要验证
        if (annotation != null) {
            // 酒店信息
            String hotelInfoJson = (String) request.getSession().getAttribute(CURRENT_HOTEL_INFO);
            // 从session中获取 当前商家ID
            Integer busId = (Integer) request.getSession().getAttribute(CURRENT_BUS_ID);
            Integer sessionHotelId = (Integer) request.getSession().getAttribute(CURRENT_HOTEL_ID);
            Map attribute = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            // hotelId
            Integer hotelId = MapUtils.getInteger(attribute, HOTEL_ID);
            if (busId != null && busId > 0) {
                /*if (StringUtils.isBlank(hotelInfoJson)) {
                    // 数据库查询
                    Wrapper<THotel> wrapper = new EntityWrapper<>();
                    wrapper.eq("id", hotelId).eq("bus_id", busId).eq("mark_modified", 0);
                    THotel hotel = this.hotelService.selectOne(wrapper);
                    if (hotel == null) {
                        LOGGER.warn("获取酒店信息失败 参数列表：hotelId : {} ", hotelId);
                        throw new ResponseEntityException(ResponseEnums.DATA_DOES_NOT_EXIST);
                    }
                    hotelInfoJson = JSONObject.toJSONString(hotel);
                    request.getSession().setAttribute(CURRENT_HOTEL_INFO, hotelInfoJson);
                } else {
                    THotel hotel = JSONObject.parseObject(hotelInfoJson, THotel.class);
                    if (!hotel.getId().equals(hotelId) || !hotel.getBusId().equals(busId)) {
                        // 数据库查询
                        Wrapper<THotel> wrapper = new EntityWrapper<>();
                        wrapper.eq("id", hotelId).eq("bus_id", busId).eq("mark_modified", 0);
                        hotel = this.hotelService.selectOne(wrapper);
                        if (hotel == null) {
                            LOGGER.warn("获取酒店信息失败 参数列表：hotelId : {} ", hotelId);
                            throw new ResponseEntityException(ResponseEnums.DATA_DOES_NOT_EXIST);
                        }
                        hotelInfoJson = JSONObject.toJSONString(hotel);
                        request.getSession().setAttribute(CURRENT_HOTEL_INFO, hotelInfoJson);
                    }
                }*/

                Member member = SessionUtils.getLoginMember(request, busId);
                if (member == null) {
                    // 如果busid 不为空，但会员信息无法获取 判断为当前商家与session 商家不相符 则需要重新授权
                    THotel hotel = findHotelById(hotelId);
                    String redirectUrl = request.getRequestURL()+"";
                    LOGGER.info("会员信息为空，进去登录或授权 , 参数: busId : {} , redirectUrl : {} ", busId, redirectUrl);
                    // 会员信息为空 重定向授权
                    // 重定向到授权/登录地址 重定向地址 为什么不加密?
                    response.sendRedirect(authorizeMember(request, busId, redirectUrl));
                    return false;
                }
            } else {
                if (StringUtils.isNotBlank(hotelInfoJson)) {
                    JSONObject jsonObject = JSONObject.parseObject(hotelInfoJson);
                    // 访问路径上的busId 与 当前酒店信息内的busId不相等，则重新获取酒店信息
                    if (!jsonObject.getInteger(BUS_ID).equals(busId)) {
                        THotel hotel = findHotelById(hotelId);
                        // 存入当前session需要的数据
                        request.getSession().setAttribute(CURRENT_HOTEL_INFO, JSONObject.toJSONString(hotel));
                        request.getSession().setAttribute(CURRENT_BUS_ID, busId);
                        request.getSession().setAttribute(CURRENT_HOTEL_ID, hotelId);
                    }
                }/*else{
                    // 获取酒店信息
                    THotel hotel = findHotelById(hotelId);
                    // 存入当前session需要的数据
                    request.getSession().setAttribute(CURRENT_HOTEL_INFO, JSONObject.toJSONString(hotel));
                    request.getSession().setAttribute(CURRENT_BUS_ID, hotel.getBusId());
                    request.getSession().setAttribute(CURRENT_HOTEL_ID, hotelId);
                }*/
            }
        }
        return true;
    }

    public THotel findHotelById(Integer hotelId){
        Wrapper<THotel> wrapper = new EntityWrapper<>();
        wrapper.eq("id", hotelId).eq("mark_modified", 0);
        try {
            THotel hotel = this.hotelService.selectOne(wrapper);
            if (hotel == null) {
                LOGGER.error("酒店信息不存在，参数信息：hotelId:{} ",  hotelId);
                throw new ResponseEntityException(ResponseEnums.DATA_DOES_NOT_EXIST);
            }
            return hotel;
        } catch (ResponseEntityException ex){
            throw ex;
        }catch (RuntimeException ex) {
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
//        String otherRedisKey = getCode();
//        redisCacheUtil.set(otherRedisKey, redirectUrl, 5 * 60L);
        Map<String, Object> queryMap = new HashMap<>();
//        queryMap.put("otherRedisKey", "hotel:" + otherRedisKey);
        Map<String, Object> redisMap = new HashMap<>();
//        redisMap.put( "redisKey", otherRedisKey );
//        redisMap.put( "redisValue", requestUrl );
        redisMap.put("setime", 300);
        // wxmp 存取
        SignHttpUtils.WxmppostByHttp("/8A5DA52E/redis/SetExApi.do", redisMap, "");
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
        // 微信-1
        if (ua.indexOf("micromessenger") > 0) {
            result = 1;
        } else {//其他 -99
            result = 99;
        }
        return result;
    }


}