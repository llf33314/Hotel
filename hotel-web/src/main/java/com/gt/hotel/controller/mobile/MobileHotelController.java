package com.gt.hotel.controller.mobile;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.base.Optional;
import com.gt.api.bean.session.Member;
import com.gt.api.exception.SignException;
import com.gt.api.util.SessionUtils;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.entity.THotelSetting;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.entity.TOrderRoom;
import com.gt.hotel.param.HotelMobileParameter;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.param.RoomCategoryParameter;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.MobileActivityRoomCategoryVo;
import com.gt.hotel.vo.MobileActivityVo;
import com.gt.hotel.vo.MobileHotelVo;
import com.gt.hotel.vo.MobileRoomCategoryVo;
import com.gt.hotel.vo.SysDictionaryVo;
import com.gt.hotel.web.service.SysDictionaryService;
import com.gt.hotel.web.service.TActivityService;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.THotelSettingService;
import com.gt.hotel.web.service.TOrderRoomService;
import com.gt.hotel.web.service.TOrderService;
import com.gt.hotel.web.service.TRoomCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 酒店移动端
 *
 * @author Reverien9@gmail.com
 * @author zhangmz
 * 2017年10月25日 下午12:04:18
 */
@Api(tags = "酒店移动端 首页")
@Slf4j
@RestController
@RequestMapping("/mobile/78CDF1")
public class MobileHotelController extends BaseController {

    @Autowired
    THotelService tHotelService;

    @Autowired
    THotelSettingService tHotelSettingService;

    @Autowired
    TRoomCategoryService tRoomCategoryService;

    @Autowired
    TActivityService tActivityService;

    @Autowired
    WXMPApiUtil wXMPApiUtil;

    @Autowired
    TOrderService tOrderService;

    @Autowired
    TOrderRoomService tOrderRoomService;

    @Autowired
    SysDictionaryService sysDictionaryService;

    @Autowired
    WebServerConfigurationProperties property;


    @ApiOperation(value = "首页", notes = "首页")
    @GetMapping(value = "home/{hotelId}")
    public ModelAndView moblieHome(
            HttpServletRequest request,
            @PathVariable("hotelId") Integer hotelId,
            ModelAndView model) {
        try {
            THotel hotel = tHotelService.selectById(hotelId);
            Member member = SessionUtils.getLoginMember(request, hotel.getBusId());
            if (member == null) {
                Map<String, Object> queryMap = new HashMap<>();
                queryMap.put("browser", judgeBrowser(request));
                queryMap.put("busId", hotel.getBusId());
                queryMap.put("uclogin", null);
                queryMap.put("returnUrl", String.format("%s/mobile/78CDF1/home/%s", getHost(request), hotelId));
                model.setViewName("redirect:" + property.getWxmpService().getApiMap().get("authorizeMemberNew") + URLEncoder.encode(JSON.toJSONString(queryMap), "utf-8"));
            } else {
                model.setViewName("redirect:/mobile/index.html/#/book/roomSet/" + hotel.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }


    @ApiOperation(value = "首页房型列表", notes = "首页房型列表")
    @GetMapping(value = "{hotelId}/roomCategory", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<MobileRoomCategoryVo>> mobileRoomCategoryR(HttpServletRequest request, @PathVariable("hotelId") Integer hotelId,
                                                                       @ModelAttribute RoomCategoryParameter.MobileQueryRoomCategory queryParam) {
        THotel hotel = this.getHotelInfo(request);
        Member member = Optional.of(this.getMember(request)).get();
        Page<MobileRoomCategoryVo> page = tRoomCategoryService.queryMobileRoomCategory(hotelId, queryParam);
        System.err.println(queryParam);
        System.err.println(page.getRecords());
        try {
            JSONObject json = wXMPApiUtil.findMemberCard(member.getPhone(), member.getBusid(), hotel.getShopId());
            if (json != null && json.getInteger("code").equals(0)) {
                JSONObject card = json.getJSONObject("data");
                Integer cardType = card.getInteger("ctId");
                List<MobileRoomCategoryVo> l = page.getRecords();
                for (MobileRoomCategoryVo m : l) {    //会员
                    switch (cardType) {
                        case CommonConst.CARD_TYPE_POINT_CARD:
                            break;
                        case CommonConst.CARD_TYPE_DISCOUNT_CARD:
                            m.setDisplayPrice(Double.valueOf(m.getRackRate() * card.getDouble("discount")).intValue());
                            break;
                        case CommonConst.CARD_TYPE_VALUE_CARD:
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseDTO.createBySuccess(page);
    }

    /* 2017/12/20: 修复缺少酒店ID，修订代码  by:zhangmz */
    @ApiOperation(value = "首页酒店信息", notes = "首页酒店信息")
    @GetMapping(value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<MobileHotelVo> moblieHotelR(@PathVariable("hotelId") Integer hotelId, HttpServletRequest request) {
        MobileHotelVo setting = tHotelSettingService.queryHotelSettingOne(hotelId);
        return ResponseDTO.createBySuccess(setting);
    }

    @ApiOperation(value = "首页房型 活动 列表", notes = "首页房型 活动 列表")
    @GetMapping(value = "{hotelId}/activity", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<MobileActivityVo>> mobileActivityR(@PathVariable("hotelId") Integer hotelId) {
        Page<MobileActivityVo> page = tActivityService.queryMobileActivity(hotelId);
        return ResponseDTO.createBySuccess(page);
    }

    @ApiOperation(value = "首页房型 活动 房型 列表", notes = "首页房型 活动 房型 列表")
    @GetMapping(value = "{hotelId}/activity/{activityId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<MobileActivityRoomCategoryVo>> mobileActivityRoomR(@PathVariable("hotelId") Integer hotelId,
                                                                               @PathVariable("activityId") Integer activityId, @ModelAttribute HotelPage hotelPage) {
        Page<MobileActivityRoomCategoryVo> page = tActivityService.queryMobileActivityRoomCategoryList(hotelId, activityId, hotelPage);
        return ResponseDTO.createBySuccess(page);
    }

    @ApiOperation(value = "已入住订单", notes = "已入住订单")
    @GetMapping(value = "{hotelId}/checkInOrder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<com.gt.hotel.vo.HotelBackRoomOrderVo>> checkInOrder(
            @PathVariable("hotelId") Integer hotelId,
            HttpServletRequest request) {
        Member member = getMember(request);
        Page<com.gt.hotel.vo.HotelBackRoomOrderVo> page = tOrderService.checkInOrder(member);
        return ResponseDTO.createBySuccess(page);
    }

    @ApiOperation(value = "查询 发票列表", notes = "查询 发票列表")
    @GetMapping(value = "{hotelId}/invoice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<SysDictionaryVo>> mobileInvoiceR(
            @PathVariable("hotelId") Integer hotelId) {
        Page<SysDictionaryVo> page = sysDictionaryService.MobileQueryDictionary(CommonConst.DICT_INVOICE, hotelId);
        return ResponseDTO.createBySuccess(page);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "预约退房", notes = "预约退房")
    @PostMapping(value = "{hotelId}/checkOut", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO mobilecheckOut(
            @PathVariable("hotelId") Integer hotelId,
            @RequestBody HotelMobileParameter.CheckOutParam param ) {
        Wrapper<THotelSetting> hw = new EntityWrapper<>();
        hw.eq("hotel_id", hotelId);
        THotelSetting hotelset = tHotelSettingService.selectOne(hw);
        Wrapper wrapper = new EntityWrapper<>();
        wrapper.eq("order_num", param.getOrderNum());
        TOrder o = tOrderService.selectOne(wrapper);
        TOrderRoom or = tOrderRoomService.selectOne(wrapper);
//        String content = "收到客人预约退房通知，请查看。订单号：" + param.getOrderNum() + "，退房房号："
//                + param.getRoomNum() + "，姓名：" + or.getCustomerName() + "，手机：" + or.getCustomerPhone() + "，发票抬头："
//                + param.getInvoiceHead() + "，发票类目：" + param.getInvoiceCategory();
        String content = param.getOrderNum() + "," + param.getRoomNum() + "," + or.getCustomerName() + ","
        		+ or.getCustomerPhone() + "," + param.getInvoiceHead() + "," + param.getInvoiceCategory();
        try {
            JSONObject result = wXMPApiUtil.sendSmsNew(hotelset.getReservationCheckOutPhone(), content, o.getBusId());
//            JSONObject result = wXMPApiUtil.sendMsg(o.getBusId(), hotelset.getReservationCheckOutPhone(), content);
            if(result == null) {
            	return ResponseDTO.createByErrorMessage("400:短信发送失败");
            }
            if (!result.getInteger("code").equals(0)) {
            	return ResponseDTO.createByErrorMessage("0:短信发送失败");
            }
        } catch (SignException e) {
            e.printStackTrace();
            return ResponseDTO.createByError();
        }
        return ResponseDTO.createBySuccess();
    }


}
