package com.gt.hotel.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.base.Optional;
import com.gt.api.bean.session.Member;
import com.gt.api.util.SessionUtils;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.NeedLoginException;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.web.service.THotelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.gt.hotel.constant.CommonConst.*;

/**
 * 移动端登录、微信授权拦截器
 * <p>
 * <STRIKE>在需要验证登录的Controller方法上添加@MobileLoginRequired 注解 拦截器将会生效</STRIKE>，开启全局拦截
 * </p>
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/11/08
 * @since 1.0
 */
@Slf4j
public class MobileAuthenticationInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    private WXMPApiUtil wxmpApiUtil;

    @Autowired
    private THotelService hotelService;

    @Autowired
    WebServerConfigurationProperties webServerConfigurationProperties;

    /**
     * 路径上获取酒店ID
     */
    private static final String HOTEL_ID = "hotelId";

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
        // 从session中获取 当前商家ID
        Optional<Integer> busId = Optional.fromNullable((Integer) request.getSession().getAttribute(CURRENT_SESSION_BUS_ID));
        Optional<Integer> sessionHotelId = Optional.fromNullable((Integer) request.getSession().getAttribute(CURRENT_SESSION_HOTEL_ID));
        Map attribute = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (passUri(request)) {
            return true;
        }
        // hotelId
        Optional<Integer> hotelId = Optional.of(MapUtils.getInteger(attribute, HOTEL_ID));
        if (busId.isPresent() && busId.get() > 0) {
            // 访问地址的酒店ID 与 redis 内存储的hotelid 不匹配，则重新获取
            if (sessionHotelId.isPresent() && !hotelId.get().equals(sessionHotelId.get())) {
                // 数据库查询
                Wrapper<THotel> wrapper = new EntityWrapper<>();
                wrapper.eq("id", hotelId.get()).eq("bus_id", busId.get());
                Optional<THotel> hotel = Optional.fromNullable(this.hotelService.selectOne(wrapper));
                if (!hotel.isPresent()) {
                    log.warn("获取酒店信息失败 参数列表：hotelId : {} , busId : {} ", hotelId.get(), busId.get());
                    throw new ResponseEntityException(ResponseEnums.DATA_DOES_NOT_EXIST);
                }
                // 并重置更新缓存信息
                resetSession(request, hotelId.get(), hotel.get());
            }
            // 获取会员信息
            Optional<Member> member = Optional.fromNullable(SessionUtils.getLoginMember(request, busId.get()));
            if (!member.isPresent()) {
                log.warn("member is null");
                String url = wxmpApiUtil.authorizeMember(busId.get());
                throw new NeedLoginException(ResponseEnums.NEED_LOGIN, busId.get(), hotelId.get(), url);
            }
        } else {
            // 酒店信息
            Optional<Member> member;
            Optional<THotel> hotel;
            // 会话中的酒店ID 不存在
            if (!sessionHotelId.isPresent()) {
                hotel = Optional.of(findHotelById(hotelId.get()));
                // 并重置更新缓存信息
                resetSession(request, hotelId.get(), hotel.get());
                // 获取会员信息
                member = Optional.fromNullable(SessionUtils.getLoginMember(request, hotel.get().getBusId()));
            } else {
                Optional<String> hotelInfoJson = Optional.of((String) request.getSession().getAttribute(CURRENT_SESSION_HOTEL_INFO));
                hotel = Optional.of(JSONObject.parseObject(hotelInfoJson.get(), THotel.class));
                member = Optional.fromNullable(SessionUtils.getLoginMember(request, hotel.get().getBusId()));
            }
            if (!member.isPresent()) {
                log.warn("member is null");
                String url = wxmpApiUtil.authorizeMember(hotel.get().getBusId());
                throw new NeedLoginException(ResponseEnums.NEED_LOGIN, hotel.get().getBusId(), hotel.get().getId(), url);
            }
        }
        return true;
    }

    /**
     * 放行 uri 地址
     *
     * @param request HttpServletRequest
     * @return boolean
     */
    private boolean passUri(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.contains("/mobile/78CDF1/home/") || uri.contains("/mobile/78CDF1/common/") || uri.contains("/mobile/auth/");
    }

    /**
     * 重置Session
     *
     * @param request HttpServletRequest
     * @param hotelId 酒店ID
     * @param hotel   酒店信息
     */
    private void resetSession(HttpServletRequest request, Integer hotelId, THotel hotel) {
        request.getSession().setAttribute(CURRENT_SESSION_HOTEL_INFO, JSONObject.toJSONString(hotel));
        request.getSession().setAttribute(CURRENT_SESSION_BUS_ID, hotel.getBusId());
        request.getSession().setAttribute(CURRENT_SESSION_HOTEL_ID, hotelId);
    }

    /**
     * 获取酒店信息
     *
     * @param hotelId 酒店ID
     * @return THotel
     */
    private THotel findHotelById(Integer hotelId) {
        Wrapper<THotel> wrapper = new EntityWrapper<>();
        wrapper.eq("id", hotelId).eq("mark_modified", 0);
        try {
            log.debug("参数信息：hotelId:{} ", hotelId);
            Optional<THotel> hotel = Optional.fromNullable(this.hotelService.selectOne(wrapper));
            return hotel.orNull();
        } catch (ResponseEntityException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            log.error("异常信息 : {} ", ex);
            throw new ResponseEntityException(ResponseEnums.UNKNOWN_ERROR);
        }
    }

}