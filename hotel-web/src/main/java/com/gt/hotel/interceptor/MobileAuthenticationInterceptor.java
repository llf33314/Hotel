package com.gt.hotel.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gt.api.bean.session.Member;
import com.gt.api.util.SessionUtils;
import com.gt.hotel.annotation.MobileLoginRequired;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.NeedLoginException;
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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import static com.gt.hotel.constant.CommonConst.*;

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
     * 路径上获取酒店ID
     */
    public static final String HOTEL_ID = "hotelId";

    /**
     * 进入方法前，判断接口是否需要登录
     * 如果需要登录，告诉前端，前端执行并判断
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
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
            LOGGER.debug("sessionId : {} ", request.getSession().getId());
            // 酒店信息
            String hotelInfoJson = (String) request.getSession().getAttribute(CURRENT_HOTEL_INFO);
            // 从session中获取 当前商家ID
            Integer busId = (Integer) request.getSession().getAttribute(CURRENT_BUS_ID);
            Integer sessionHotelId = (Integer) request.getSession().getAttribute(CURRENT_HOTEL_ID);
            Map attribute = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            // hotelId
            Integer hotelId = MapUtils.getInteger(attribute, HOTEL_ID);
            System.err.println("check:{"+busId+"}");
            if (busId != null && busId > 0 && "null".equals(busId)) {
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
                // 获取会员信息
                Member member = SessionUtils.getLoginMember(request, busId);
                if (member == null) {
                    String url = authorizeMember(request, busId);
                    throw new NeedLoginException(ResponseEnums.NEED_LOGIN, busId, url);
                }
            } else {
                if (hotelId != null) {
                    THotel hotel = findHotelById(hotelId);
                    // 并重置更新缓存信息
                    request.getSession().setAttribute(CURRENT_HOTEL_INFO, hotelInfoJson);
                    request.getSession().setAttribute(CURRENT_HOTEL_INFO, JSONObject.toJSONString(hotel));
                    request.getSession().setAttribute(CURRENT_BUS_ID, hotel.getBusId());
                    request.getSession().setAttribute(CURRENT_HOTEL_ID, hotelId);
                    // 获取会员信息
                    Member member = SessionUtils.getLoginMember(request, hotel.getBusId());
                    if (member == null) {
                        String url = authorizeMember(request, hotel.getBusId());
                        throw new NeedLoginException(ResponseEnums.NEED_LOGIN, hotel.getBusId(), url);
                    }
                } else {
                    throw new ResponseEntityException(ResponseEnums.BAD_REQUEST);
                }
            }
        }
        return true;
    }

    /**
     * 获取酒店信息
     *
     * @param hotelId 酒店ID
     * @return THotel
     */
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
     * @param request HttpServletRequest
     * @param busId   商家ID
     * @return URL地址
     * @throws Exception
     */
    private String authorizeMember(HttpServletRequest request, Integer busId) throws Exception {
        Integer browser = judgeBrowser(request);
        //参数uclogin 如果uclogin不为空值  是指微信端是要通过授权  其他浏览器需要登录
        JSONObject wxPublic = wxmpApiUtil.getWxPulbicMsg(busId);
        Integer code = wxPublic.getInteger("code");
        //判断商家信息 1是否过期 2公众号是否变更过
        if (code.equals(-1)) {
            //请求错误
            return "";
        } else if (code.equals(0)) {
            String guoqi = wxPublic.getString("guoqi");
            //商家已过期
            if (!StringUtils.isBlank(guoqi)) {
                Object overdue = wxPublic.get("guoqiUrl");
                return "redirect:" + overdue;
            }
            String remoteUcLogin = wxPublic.getString("remoteUcLogin");
            if (!StringUtils.isBlank(remoteUcLogin)) {
                return "";
            }
        }
        return webServerConfigurationProperties.getWxmpService().getApiMap().get("authorizeMemberNew");
        /*Map<String, Object> queryMap = new HashMap<>(3);
        String redirectUrl = URLEncoder.encode(request.getRequestURL().toString(), "utf-8");
        queryMap.put("browser", browser);
        queryMap.put("busId", busId);
        queryMap.put("uclogin", null);
        queryMap.put("returnUrl", redirectUrl);
        String params = URLEncoder.encode(JSON.toJSONString(queryMap), "utf-8");
        LOGGER.info("授权地址：{}{}", webServerConfigurationProperties.getWxmpService().getApiMap().get("authorizeMember") + params, JSON.toJSONString(queryMap));*/
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

    public static void main(String[] args) throws UnsupportedEncodingException {
        String url = "https://deeptel.com.cn//phoneLoginController/33/79B4DE7C/phonelogin.do?returnKey=hotel1510621956750#1231saldaslkdjaldj#31142222";
        String urlencode = URLEncoder.encode(url, "utf-8");
        String urldecode = URLDecoder.decode(urlencode, "utf-8");
        System.out.println("原始地址：" + url);
        System.out.println("加密后的地址：" + urlencode);
        System.out.println("解密后的地址：" + urldecode);
    }


}