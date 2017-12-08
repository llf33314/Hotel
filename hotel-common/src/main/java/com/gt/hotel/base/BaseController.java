package com.gt.hotel.base;

import com.gt.api.bean.session.BusUser;
import com.gt.api.bean.session.Member;
import com.gt.api.util.SessionUtils;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.util.RedisCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.gt.hotel.constant.CommonConst.CURRENT_BUS_ID;

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
    public String getSessionId(HttpSession session) {
        return session.getId();
    }

    /**
     * 暂时写死一个 id
     * TODO: 待完善 登录流程
     *
     * @param request HttpServletRequest
     * @return int
     */
    public BusUser getLoginUser(HttpServletRequest request) {
        BusUser b = new BusUser();
        b.setId(33);
        return b;
//    	return SessionUtils.getLoginUser(request);
    }

    /**
     * 获取会员
     *
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
    public static Integer judgeBrowser(HttpServletRequest request) {
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

    public static String getCode() {
        Long date = System.currentTimeMillis();
        return date.toString().substring(1);
    }

    public static String getHost(HttpServletRequest request) {
        String path = request.getContextPath();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    }
}
