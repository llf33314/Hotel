package com.gt.hotel.controller.erp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.api.exception.SignException;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.param.HotelOrderParameter;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.HotelBackRoomOrderVo;
import com.gt.hotel.web.service.TOrderRoomService;
import com.gt.hotel.web.service.TOrderService;
import com.gt.hotel.web.service.TRoomCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 酒店ERP - 预订
 * @author Reverien9@gmail.com
 * 2017年11月21日 上午10:51:58
 */
@Api(tags = "酒店ERP - 预订")
@RestController
@RequestMapping("/erp/booking")
public class ErpHotelBookingController extends BaseController {
	
	@Autowired
    WXMPApiUtil wXMPApiUtil;
	
	@Autowired
    WebServerConfigurationProperties properties;
	
	@Autowired
	TOrderService orderService;
	
	@Autowired
	TOrderRoomService orderRoomService;
	
	@Autowired
	TRoomCategoryService roomCategoryService;

    @ApiOperation(value = "房间订单 列表", notes = "房间订单 列表")
    @GetMapping(value = "roomOrder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<HotelBackRoomOrderVo>> roomOrder(
    		HotelOrderParameter.RoomOrderQuery param,
			HttpServletRequest request) {
		Integer busid = getLoginUser(request).getId();
		Page<HotelBackRoomOrderVo> page = orderService.queryRoomOrder(busid, param);
		return ResponseDTO.createBySuccess(page);
	}
    
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "新增预订", notes = "新增预订")
	@PostMapping(value = "roomOrder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO newOrder(
			@Validated @RequestBody HotelOrderParameter.OffLineOrder order, 
			BindingResult bindingResult, 
			HttpServletRequest request) {
		ResponseDTO msg = InvalidParameterII(bindingResult);
        if(msg != null) {
        	return msg;
        }
		Integer busid = getLoginUser(request).getId();
		orderService.AddOffLineOrder(busid, order);
		return ResponseDTO.createBySuccess();
	}
	
	@ApiOperation(value = "搜索会员", notes = "搜索会员")
	@GetMapping(value = "searchMember", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<Page<HotelBackRoomOrderVo>> searchMember(
			String phone, 
			Integer shopId, 
			HttpServletRequest request) {
		Integer busid = getLoginUser(request).getId();
		try {
			JSONObject json = wXMPApiUtil.findMemberCard(phone, busid, shopId);
			if(json.getIntValue("code") == 0) {
				JSONObject card = json.getJSONObject("data");
				JSONObject result = new JSONObject();
				result.put("consumption", orderRoomService.queryMobileRoomOrderSUM(card.getInteger("memberId")));
				result.put("memberInfo", card);
				return ResponseDTO.createBySuccess();
			}else {
				return ResponseDTO.createByError();
			}
		} catch (SignException e) {
			e.printStackTrace();
			return ResponseDTO.createByError();
		}
	}
    
	@ApiOperation(value = "订单详情", notes = "订单详情")
	@GetMapping(value = "roomOrder/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<HotelBackRoomOrderVo> roomOrderOneR(
			@ApiParam("订单ID") @PathVariable("orderId") Integer orderId) {
		HotelBackRoomOrderVo order = orderService.queryRoomOrderOne(orderId);
		return ResponseDTO.createBySuccess(order);
	}
	
	
}
