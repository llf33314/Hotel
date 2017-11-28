package com.gt.hotel.controller.mobile;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.api.bean.session.Member;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.entity.TOrderRoom;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.vo.DepositVo;
import com.gt.hotel.vo.HotelBackFoodOrderVo;
import com.gt.hotel.vo.HotelBackRoomOrderVo;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.TOrderRoomService;
import com.gt.hotel.web.service.TOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 酒店移动端 我的
 * @author Reverien9@gmail.com
 * 2017年11月10日 下午3:54:20
 */
@Api(tags = "酒店移动端 我的")
@RestController
@RequestMapping("/mobile/78CDF1/my")
public class MobilePersonalController extends BaseController {

	@Autowired
    THotelService tHotelService;
	
	@Autowired
	TOrderService tOrderService;
	
	@Autowired
	TOrderRoomService tOrderRoomService;
	
	@Autowired
    private WebServerConfigurationProperties property;
	
	@ApiOperation(value = "我的 首页", notes = "我的 首页")
    @GetMapping(value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Member> moblieMyHome(@PathVariable("hotelId") Integer hotelId, HttpServletRequest request) {
		Member member = getMember(request);
        return ResponseDTO.createBySuccess(member);
    }
    
	/********************************************************* 房间订单 *************************************************************************/
	
	@ApiOperation(value = "房间订单", notes = "房间订单")
    @GetMapping(value = "{hotelId}/roomOrder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<HotelBackRoomOrderVo>> moblieRoomOrder(
    		@PathVariable("hotelId") Integer hotelId, 
    		@ModelAttribute HotelPage hotelPage, 
    		HttpServletRequest request) {
		Member member = getMember(request);
        return ResponseDTO.createBySuccess(tOrderService.queryMobileRoomOrder(member, hotelPage));
    }
	
	/********************************************************* ↑房间订单↑ ↓共用↓ *************************************************************************/
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "移动端订单 取消", notes = "移动端订单 取消")
	@PostMapping(value = "{hotelId}/cancel/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO moblieRoomOrderCancel(
			@PathVariable("hotelId") Integer hotelId, 
			@PathVariable("orderId") Integer orderId, 
			HttpServletRequest request) {
		Member member = getMember(request);
		TOrder order = tOrderService.selectById(orderId);
		if(!order.getOrderStatus().equals(CommonConst.ORDER_PROCESSING) && !order.getOrderStatus().equals(CommonConst.ORDER_CONFIRMED)) {
			return ResponseDTO.createByErrorMessage(ResponseEnums.ORDER_STATUS_ERROR.getMsg());
		}
		Wrapper<TOrder> wrapper = new EntityWrapper<>();
		wrapper.eq("id", orderId);
		TOrder newOrder = new TOrder();
		newOrder.setOrderStatus(CommonConst.ORDER_CANCALLED);
		newOrder.setUpdatedBy(member.getId());
		if(!tOrderService.update(newOrder, wrapper)) {
			return ResponseDTO.createByErrorMessage(ResponseEnums.OPERATING_ERROR.getMsg());
		}
		return ResponseDTO.createBySuccess();
	}
	
	/********************************************************* ↓我的订餐↓ ↑共用↑ *************************************************************************/
	
	@ApiOperation(value = "我的订餐", notes = "我的订餐")
    @GetMapping(value = "{hotelId}/foodOrder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<HotelBackFoodOrderVo>> moblieFoodOrder(
    		@PathVariable("hotelId") Integer hotelId, 
    		@ModelAttribute HotelPage hotelPage, 
    		HttpServletRequest request) {
		Member member = getMember(request);
        return ResponseDTO.createBySuccess(tOrderService.queryMobileFoodOrder(member, hotelPage));
    }
	
	/********************************************************* 我的订餐 *************************************************************************/
	
	/********************************************************* 我的押金 *************************************************************************/
	
	@ApiOperation(value = "我的押金", notes = "我的押金")
    @GetMapping(value = "{hotelId}/deposit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<DepositVo>> moblieDeposit(
    		@PathVariable("hotelId") Integer hotelId, 
    		@ModelAttribute HotelPage hotelPage, 
    		HttpServletRequest request) {
		Member member = getMember(request);
        return ResponseDTO.createBySuccess(tOrderService.queryMobileDeposit(member, hotelPage));
    }
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "我的押金 垃圾箱按钮", notes = "我的押金 垃圾箱按钮")
    @PostMapping(value = "{hotelId}/deposit/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO moblieDepositD(
    		@PathVariable("hotelId") Integer hotelId, 
    		@PathVariable("orderId") Integer orderId, 
    		HttpServletRequest request) {
		Member member = getMember(request);
    	Wrapper<TOrderRoom> wrapper = new EntityWrapper<>();
    	wrapper.eq("order_id", orderId);
		TOrderRoom entity = new TOrderRoom();
		entity.setDeposit(CommonConst.CLOSE);
		entity.setUpdatedBy(member.getId());
		if(!tOrderRoomService.update(entity, wrapper)) {
			return ResponseDTO.createByErrorMessage(ResponseEnums.OPERATING_ERROR.getMsg());
    	}
        return ResponseDTO.createBySuccess();
    }
	
	/********************************************************* 我的押金 *************************************************************************/
	
	/********************************************************* 我的房卡 *************************************************************************/
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "我的房卡（暂无）", notes = "我的房卡（暂无）")
	@GetMapping(value = "{hotelId}/roomCard", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO moblieRoomCard(
			@PathVariable("hotelId") Integer hotelId, 
			@ModelAttribute HotelPage hotelPage, 
			HttpServletRequest request) {
		//TODO 我的房卡（暂无）
		return ResponseDTO.createBySuccess();
	}
	
	/********************************************************* 我的房卡 *************************************************************************/
	
	@ApiOperation(value = "我的商城、会员卡、个人中心 链接", notes = "我的商城、会员卡、个人中心 链接")
	@GetMapping(value = "{hotelId}/linkList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<JSONObject> moblieLink(
			@PathVariable("hotelId") Integer hotelId, 
			HttpServletRequest request) {
		THotel hotel = tHotelService.selectById(hotelId);
		Member member = getMember(request);
		JSONObject json = new JSONObject();
		json.put("shop", property.getWxmpService().getServiceUrl() + "/mallPage/"+hotel.getShopId()+"/79B4DE7C/getPageIds.do");
		json.put("memberCard", property.getWxmpService().getServiceUrl() + "/phoneMemberController/"+member.getPublicId()+"/79B4DE7C/findMember.do");
		json.put("center", property.getWxmpService().getServiceUrl() + "/phonePersonCenterController/"+member.getPublicId()+"/79B4DE7C/personCenterIndex.do");
		return ResponseDTO.createBySuccess(json);
	}
	
}
