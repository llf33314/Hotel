package com.gt.hotel.base;

import static com.gt.hotel.constant.CommonConst.CURRENT_SESSION_BUS_ID;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Optional;
import com.gt.api.bean.session.BusUser;
import com.gt.api.bean.session.Member;
import com.gt.api.util.SessionUtils;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.util.RedisCacheUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * BaseController
 *
 * @author zhangmz
 * @create 2017/7/10
 */
@Slf4j
public abstract class BaseController {

    @Autowired
    protected RedisCacheUtil redisCacheUtil;

    /**
     * 获取Sessionid
     *
     * @param session HttpSession
     * @return
     */
    protected String getSessionId(HttpSession session) {
        return session.getId();
    }

    /**
     * 暂时写死一个 id
     * TODO: 待完善 登录流程
     *
     * @param request HttpServletRequest
     * @return int
     */
    protected BusUser getLoginUser(HttpServletRequest request) {
//        BusUser b = new BusUser();
//        b.setId(33);
//        return b;
    	return SessionUtils.getLoginUser(request);
    }

    /**
     * 获取酒店信息
     * @return THotel
     */
    protected THotel getHotelInfo(HttpServletRequest request){
        Optional<String> json = Optional.of((String)request.getSession().getAttribute(CommonConst.CURRENT_SESSION_HOTEL_INFO));
        return Optional.of(JSONObject.parseObject(json.get(),THotel.class)).get();
    }

    /**
     * 获取会员
     *
     * @param request
     * @return
     */
    protected Member getMember(HttpServletRequest request) {
        Integer busId = (Integer) request.getSession().getAttribute(CURRENT_SESSION_BUS_ID);
        return SessionUtils.getLoginMember(request, busId);
    }



    /**
     * 参数校验
     *
     * @param result BindingResult
     */
    protected void invalidParameter(BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errorList = result.getAllErrors();
            for (ObjectError error : errorList) {
                log.warn(error.getDefaultMessage());
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
    protected ResponseDTO invalidParameterII(BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errorList = result.getAllErrors();
            for (ObjectError error : errorList) {
                log.warn(error.getDefaultMessage());
                return ResponseDTO.createByErrorMessage(error.getDefaultMessage());
            }
        }
        return null;
    }

    /**
     * 判断浏览器
     *
     * @return 1:微信浏览器,99:其他浏览器
     */
    protected static Integer judgeBrowser(HttpServletRequest request) {
        Integer result;
        String ua = request.getHeader("user-agent")
                .toLowerCase();
        // 微信-1
        String wxHaeader = "micromessenger";
        if (ua.indexOf(wxHaeader) > 0) {
            result = 1;
        } else {
            //其他 -99
            result = 99;
        }
        return result;
    }

    protected static String getCode() {
        Long date = System.currentTimeMillis();
        return date.toString().substring(1);
    }

    protected static String getHost(HttpServletRequest request) {
        String path = request.getContextPath();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    }
}
