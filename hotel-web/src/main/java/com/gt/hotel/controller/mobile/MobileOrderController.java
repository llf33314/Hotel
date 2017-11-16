package com.gt.hotel.controller.mobile;

import com.alibaba.fastjson.JSONObject;
import com.gt.api.bean.session.Member;
import com.gt.api.util.SessionUtils;
import com.gt.hotel.annotation.MobileLoginRequired;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 移动端订单管理
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/11/06
 * @since v1.0
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping("/mobile")
public class MobileOrderController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MobileOrderController.class);

    @Autowired
    private WebServerConfigurationProperties webServerConfigurationProperties;

    /**
     * 预订
     * 获取支付方式
     * 获取粉币 积分 优惠券列表
     * 获取房型信息 押金 房费
     *
     * @param request    HttpServletRequest
     * @param hotelId    酒店ID
     * @param categoryId 房型ID
     * @return ResponseDTO
     */
    @ApiOperation("预订")
    @GetMapping(value = "/{hotelId}/{categoryId}/booking", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO booking(HttpServletRequest request, @PathVariable Integer hotelId, @PathVariable Integer categoryId) {
        // 1. 获取当前会员ID


        return ResponseDTO.createBySuccess();
    }


    /**
     * 创建预订订单(普通)
     *
     * @param session      HttpSession
     * @param hid          订单id
     * @param cid          房型id
     * @param checkInTime  入住时间
     * @param checkOutTime 离店时间
     * @return ResponseDTO 数据包
     */
    @GetMapping(value = "/{hid}/{cid}/order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO createNewOrder(HttpSession session, @PathVariable Integer hid, @PathVariable Integer cid, @RequestParam String checkInTime, @RequestParam String checkOutTime) {
        Integer userId = this.getLoginUserId(session);
//        入住时间
        DateTime inTime = DateTime.parse(checkInTime);
//        离店时间
        DateTime outTime = DateTime.parse(checkOutTime);
        if (!inTime.isBefore(outTime)) {
            LOGGER.error("开始日期不能大于结束时间 checkIn:{} ,checkOut:{} ", checkInTime, checkOutTime);
            throw new ResponseEntityException("入住日期必须小于离店日期");
        }


        return ResponseDTO.createBySuccess();
    }

    @MobileLoginRequired
    @GetMapping(value = "/{hotelId}/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO test(HttpServletRequest request, @PathVariable Integer hotelId) {
        System.out.println("hotelId : " + hotelId);
        Integer busId = (Integer) request.getSession().getAttribute("hotel:current_bus_id");

        Member member = SessionUtils.getLoginMember(request, busId);
        LOGGER.info("member: {}", JSONObject.toJSON(member));
        System.out.println(webServerConfigurationProperties.getWxmpService());
        System.out.println(webServerConfigurationProperties.getWxmpService().getApiMap().get("dictKey"));
        System.out.println(webServerConfigurationProperties.getShortService());
        System.out.println(webServerConfigurationProperties.getMemberService());
        return ResponseDTO.createBySuccess();
    }

}
