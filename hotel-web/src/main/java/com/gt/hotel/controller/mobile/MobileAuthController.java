package com.gt.hotel.controller.mobile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gt.api.bean.session.Member;
import com.gt.api.exception.SignException;
import com.gt.api.util.SessionUtils;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.entity.TAuthorization;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.web.service.TAuthorizationService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.gt.hotel.constant.CommonConst.CURRENT_BUS_ID;

/**
 * 手机端授权操作
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/11/28
 * @since 授权
 */
@Slf4j
@RequestMapping("/mobile/auth/")
@RestController
public class MobileAuthController extends BaseController {

    @Autowired
    private TAuthorizationService tAuthorizationService;

    @Autowired
    private WXMPApiUtil wxmpApiUtil;


    @ApiOperation(value = "首页", notes = "首页")
    @GetMapping(value = "{hotelId}/{authorId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView auth(HttpServletRequest request, @Param("酒店ID") @PathVariable("hotelId") Integer hotelId,
                                   @Param("授权ID") @PathVariable("authorId") Integer authorId, ModelAndView model) {
        Integer busId = (Integer) request.getSession().getAttribute(CURRENT_BUS_ID);
        Member member = SessionUtils.getLoginMember(request, busId);
        Wrapper<TAuthorization> wrapper = new EntityWrapper<>();
        // 严谨点，应充分使用所有条件。避免数据串改
        wrapper.eq("id", authorId).eq("hotel_id", hotelId).eq("mark_modified", 0);
        TAuthorization entity = new TAuthorization();
        entity.setScanCodeAuthorization(CommonConst.AUTHORIZED_ENABLED);
        entity.setUpdatedAt(new Date());
        entity.setUpdatedBy(busId);
        entity.setMemberId(member.getId());
        if (tAuthorizationService.update(entity, wrapper)) {
            try {
                wxmpApiUtil.getSocketApi("hotel:backsocket", null, "success");
            } catch (SignException e) {
                log.error("签名失败: {}",e);
            }
            model.setViewName("/author/success.html");
        } else {
            model.setViewName("/author/fail.html");
        }
        return model;
    }

}

