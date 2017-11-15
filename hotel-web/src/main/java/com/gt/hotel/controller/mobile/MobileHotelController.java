package com.gt.hotel.controller.mobile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.api.bean.session.Member;
import com.gt.api.exception.SignException;
import com.gt.api.util.SessionUtils;
import com.gt.hotel.annotation.MobileLoginRequired;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.entity.TOrderRoom;
import com.gt.hotel.param.HotelMobileParameter;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.param.RoomCategoryParameter;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.MobileActivityRoomCategoryVo;
import com.gt.hotel.vo.MobileActivityVo;
import com.gt.hotel.vo.MobileHotelVo;
import com.gt.hotel.vo.MobileRoomCategoryVo;
import com.gt.hotel.web.service.TActivityService;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.THotelSettingService;
import com.gt.hotel.web.service.TOrderRoomService;
import com.gt.hotel.web.service.TOrderService;
import com.gt.hotel.web.service.TRoomCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 酒店移动端
 *
 * @author Reverien9@gmail.com 
 * 2017年10月25日 下午12:04:18
 */
@Api(tags = "酒店移动端 首页")
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

//    @MobileLoginRequired
    @ApiOperation(value = "首页", notes = "首页")
    @GetMapping(value = "{hotelId}/home", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView moblieHome(HttpServletRequest request, @PathVariable("hotelId") Integer hotelId, ModelAndView model) {
//    	THotel hotel = tHotelService.selectById(hotelId);
//    	Member member = SessionUtils.getLoginMember(request, hotel.getBusId());
//    	if(StringUtils.isEmpty(member) || StringUtils.isEmpty(member.getId())) {
//    		Map<String, Object> param = new HashMap<>();
//    		param.put("busId", hotel.getBusId());
//    		param.put("requestUrl", getHost(request) + "/mobile/78CDF1" + hotelId + "/home/");
//    		String url = null;
//			try {
//				url = authorizeMember(request, param);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//    		if(!StringUtils.isEmpty(url)) {
//    			model.setViewName(url);
//    	        return model;
//    		}
//    	}
    	model.setViewName("/index.html");
        return model;
    }

    @MobileLoginRequired
    @ApiOperation(value = "首页酒店信息", notes = "首页酒店信息")
    @GetMapping(value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<MobileHotelVo> moblieHotelR(@PathVariable("hotelId") Integer hotelId, HttpServletRequest request) {
    	//test
    	Member member = new Member();
    	member.setId(1071);
    	member.setBusid(33);
    	member.setPhone("13433550667");
    	member.setPublicId(492);
//		SessionUtils.setLoginMember(request, member);
    	//test
    	MobileHotelVo setting = tHotelSettingService.queryHotelSettingOne(hotelId);
        return ResponseDTO.createBySuccess(setting);
    }

    @ApiOperation(value = "首页房型列表", notes = "首页房型列表")
    @GetMapping(value = "{hotelId}/roomCategory", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<MobileRoomCategoryVo>> mobileRoomCategoryR(@PathVariable("hotelId") Integer hotelId, 
    		@ModelAttribute RoomCategoryParameter.MobileQueryRoomCategory req, HttpServletRequest request) {
    	THotel hotel = tHotelService.selectById(hotelId);
    	Member member1 = SessionUtils.getLoginMember(request, hotel.getBusId());
    	System.err.println(JSONObject.toJSONString(member1));
    	//test
    	Member member = new Member();
    	member.setId(1071);
    	member.setBusid(33);
    	member.setPhone("13433550667");
    	member.setPublicId(492);
    	member.setCardid("15338");
    	//test
    	Page<MobileRoomCategoryVo> page = tRoomCategoryService.queryMobileRoomCategory(hotelId, req);
    	try {
    	if(member != null && member.getId() != null) {
    		JSONObject json = wXMPApiUtil.findMemberCard(member.getPhone(), member.getBusid(), hotel.getShopId());
    		JSONObject card = json.getJSONObject("data");
    		Integer cardType = card.getInteger("ctId");
    		List<MobileRoomCategoryVo> l = page.getRecords();
    		for(MobileRoomCategoryVo m : l) {	//会员
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
    		return ResponseDTO.createByErrorMessage("会员信息获取失败");
    	}
        return ResponseDTO.createBySuccess(page);
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
    public ResponseDTO<Page<MobileActivityRoomCategoryVo>> checkInOrder(@PathVariable("hotelId") Integer hotelId, 
    		@PathVariable("activityId") Integer activityId, @ModelAttribute HotelPage hotelPage) {
    	Page<MobileActivityRoomCategoryVo> page = null;
    	return ResponseDTO.createBySuccess(page);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "预约退房", notes = "预约退房")
    @PostMapping(value = "{hotelId}/checkOut", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO mobilecheckOut(@PathVariable("hotelId") Integer hotelId, 
    		@RequestBody HotelMobileParameter.CheckOutParam param, BindingResult bindingResult) {
    	ResponseDTO msg = InvalidParameterII(bindingResult);
    	if(msg != null) {
    		return msg;
    	}
    	Wrapper wrapper = new EntityWrapper<>();
    	wrapper.eq("order_num", param.getOrderNum());
    	TOrder o = tOrderService.selectOne(wrapper);
    	TOrderRoom or = tOrderRoomService.selectOne(wrapper);
    	String content = "收到客人预约退房通知，请查看。订单号："+param.getOrderNum()+"，退房房号："
    			+param.getRoomNum()+"，姓名："+or.getCustomerName()+"，手机："+or.getCustomerPhone()+"，发票抬头："+param.getInvoiceHead();
		try {
			JSONObject result = wXMPApiUtil.sendMsg(o.getBusId(), or.getCustomerPhone(), content);
			if(!result.getInteger("code").equals(0)) {
				return ResponseDTO.createByError();
			}
		} catch (SignException e) {
			e.printStackTrace();
			return ResponseDTO.createByError();
		}
    	return ResponseDTO.createBySuccess();
    }
    
}
