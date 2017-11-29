package com.gt.hotel.interceptor;

import static com.gt.hotel.constant.CommonConst.CURRENT_BUS_ID;
import static com.gt.hotel.constant.CommonConst.CURRENT_HOTEL_ID;
import static com.gt.hotel.constant.CommonConst.CURRENT_HOTEL_INFO;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gt.api.bean.session.Member;
import com.gt.api.exception.SignException;
import com.gt.api.util.SessionUtils;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.NeedLoginException;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.web.service.THotelService;

/**
 * 移动端登录、微信授权拦截器
 * <p>
 *   <STRIKE>在需要验证登录的Controller方法上添加@MobileLoginRequired 注解 拦截器将会生效</STRIKE>，开启全局拦截
 * </p>
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/11/08
 * @since 1.0
 */
public class MobileAuthenticationInterceptor extends HandlerInterceptorAdapter {


    private static final Logger LOGGER = LoggerFactory.getLogger(MobileAuthenticationInterceptor.class);

    @Autowired
    private WXMPApiUtil wxmpApiUtil;

    @Autowired
    private THotelService hotelService;

    @Autowired
    WebServerConfigurationProperties webServerConfigurationProperties;
    
    /**
     * 路径上获取酒店ID
     */
    public static final String HOTEL_ID = "hotelId";

    /**
     * 进入方法前，判断接口是否需要登录
     * 如果需要登录，告诉前端，前端执行并判断
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param handler  Object
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	// 如果不是映射到方法直接通过
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        // 判断接口是否需要登录
//        MobileLoginRequired annotation = method.getAnnotation(MobileLoginRequired.class);
//        // 移动端有 @MobileLoginRequired 注解，需要验证
//        if (annotation != null) {
    	// 酒店信息
    	
    	SessionUtils.getLoginMember(request, 33);
    	
    	//test
//    	Member member1 = new Member();
//    	member1.setId(1071);
//    	member1.setBusid(33);
//    	member1.setPhone("13433550667");
//    	member1.setPublicId(492);
//    	member1.setCardid("15338");
//    	SessionUtils.setLoginMember(request, member1);
    	//test
    	
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
            // 获取会员信息
            Member member = SessionUtils.getLoginMember(request, busId);
            if (member == null) {
                LOGGER.warn("member is null");
                String url = wxmpApiUtil.authorizeMember(busId);
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
                    LOGGER.warn("member is null");
                    String url = wxmpApiUtil.authorizeMember(hotel.getBusId());
                    throw new NeedLoginException(ResponseEnums.NEED_LOGIN, hotel.getBusId(), url);
                }
            } else {
                throw new ResponseEntityException(ResponseEnums.BAD_REQUEST);
            }
        }
        return true;
    }

    private String authorizeMember(HttpServletRequest request, Integer busId) throws SignException {
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

    public static void main(String[] args) throws UnsupportedEncodingException {
        String url = "https://deeptel.com.cn//phoneLoginController/33/79B4DE7C/phonelogin.do?returnKey=hotel1510621956750#1231saldaslkdjaldj#31142222";
        String urlencode = URLEncoder.encode(url, "utf-8");
        String urldecode = URLDecoder.decode(urlencode, "utf-8");
        System.out.println("原始地址：" + url);
        System.out.println("加密后的地址：" + urlencode);
        System.out.println("解密后的地址：" + urldecode);
    }


}