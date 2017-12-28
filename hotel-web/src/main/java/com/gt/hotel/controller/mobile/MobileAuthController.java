package com.gt.hotel.controller.mobile;

import static com.gt.hotel.constant.CommonConst.CURRENT_SESSION_BUS_ID;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gt.api.bean.session.Member;
import com.gt.api.util.SessionUtils;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.entity.TAuthorization;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.web.service.TAuthorizationService;
import com.gt.hotel.web.service.THotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 手机端授权操作
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/11/28
 * @since 授权
 */
@Slf4j
@Api(tags = "授权操作")
@RequestMapping("/mobile/auth/")
@RestController
public class MobileAuthController extends BaseController {

    @Autowired
    private TAuthorizationService tAuthorizationService;

    @Autowired
    private WXMPApiUtil wxmpApiUtil;

    @Autowired
    WebServerConfigurationProperties property;

    @Autowired
    THotelService tHotelService;

    @ApiOperation(value = "首页", notes = "首页")
    @GetMapping(value = "{hotelId}/{authorId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView auth(HttpServletRequest request, @Param("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		@Param("授权ID") @PathVariable("authorId") Integer authorId, ModelAndView model) {
    	THotel hotel = tHotelService.selectById(hotelId);
    	Integer busId = (Integer) request.getSession().getAttribute(CURRENT_SESSION_BUS_ID);
    	Member member = SessionUtils.getLoginMember(request, busId);
    	try {
    		if (member == null) {
    			Map<String, Object> queryMap = new HashMap<>();
                queryMap.put("browser", judgeBrowser(request));
                queryMap.put("busId", hotel.getBusId());
                queryMap.put("uclogin", null);
                queryMap.put("returnUrl", getHost(request) + "/mobile/auth/" + hotelId + "/" + authorId);
                model.setViewName("redirect:" + property.getWxmpService().getApiMap().get("authorizeMemberNew") + URLEncoder.encode(JSON.toJSONString(queryMap), "utf-8"));
    		} else {
    			// 严谨点，应充分使用所有条件。避免数据串改
    			TAuthorization authorization = tAuthorizationService.selectById(authorId);
    			Wrapper<TAuthorization> wrapper = new EntityWrapper<>();
    			wrapper.eq("id", authorId).eq("hotel_id", hotelId).eq("mark_modified", 0);
    			TAuthorization entity = new TAuthorization();
    			entity.setScanCodeAuthorization(CommonConst.AUTHORIZED_ENABLED);
    			entity.setUpdatedAt(new Date());
    			entity.setUpdatedBy(busId);
    			entity.setMemberId(member.getId());
    			if (authorization.getMemberId() != null && tAuthorizationService.update(entity, wrapper)) {
    				wxmpApiUtil.getSocketApi("hotel:backsocket", null, "success");
    				model.setViewName("/author/success.html");
    			} else {
    				model.setViewName("/author/fail.html");
    			}
    		}
    	} catch (Exception e) {
    		log.error("签名失败: {}",e);
    	}
        return model;
    }

}

