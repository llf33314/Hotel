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
import com.gt.hotel.entity.TOrderRoom;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
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
//		THotel hotel = tHotelService.selectById(hotelId);
//    	Member member = SessionUtils.getLoginMember(request, hotel.getBusId());
    	//test
    	Member member = new Member();
    	member.setId(1071);
    	member.setBusid(33);
    	member.setPhone("13433550667");
    	member.setPublicId(492);
    	member.setCardid("15338");
    	//test
        return ResponseDTO.createBySuccess(member);
    }
    
	/********************************************************* 房间订单 *************************************************************************/
	
	@ApiOperation(value = "房间订单", notes = "房间订单")
    @GetMapping(value = "{hotelId}/roomOrder/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<HotelBackRoomOrderVo>> moblieRoomOrder(
    		@PathVariable("hotelId") Integer hotelId, 
    		@PathVariable("orderId") Integer orderId, 
    		@ModelAttribute HotelPage hotelPage, 
    		HttpServletRequest request) {
//		THotel hotel = tHotelService.selectById(hotelId);
//    	Member member = SessionUtils.getLoginMember(request, hotel.getBusId());
    	//test
    	Member member = new Member();
    	member.setId(1071);
    	member.setBusid(33);
    	member.setPhone("13433550667");
    	member.setPublicId(492);
    	member.setCardid("15338");
    	//test
        return ResponseDTO.createBySuccess(tOrderService.queryMobileRoomOrder(member, hotelPage));
    }
	
	/********************************************************* 房间订单 *************************************************************************/
	
	/********************************************************* 我的订餐 *************************************************************************/
	
	@ApiOperation(value = "我的订餐", notes = "我的订餐")
    @GetMapping(value = "{hotelId}/foodOrder/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<HotelBackFoodOrderVo>> moblieFoodOrder(
    		@PathVariable("hotelId") Integer hotelId, 
    		@PathVariable("orderId") Integer orderId, 
    		@ModelAttribute HotelPage hotelPage, 
    		HttpServletRequest request) {
//		THotel hotel = tHotelService.selectById(hotelId);
//    	Member member = SessionUtils.getLoginMember(request, hotel.getBusId());
    	//test
    	Member member = new Member();
    	member.setId(1071);
    	member.setBusid(33);
    	member.setPhone("13433550667");
    	member.setPublicId(492);
    	member.setCardid("15338");
    	//test
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
//		THotel hotel = tHotelService.selectById(hotelId);
//    	Member member = SessionUtils.getLoginMember(request, hotel.getBusId());
    	//test
    	Member member = new Member();
    	member.setId(1071);
    	member.setBusid(33);
    	member.setPhone("13433550667");
    	member.setPublicId(492);
    	member.setCardid("15338");
    	//test
        return ResponseDTO.createBySuccess(tOrderService.queryMobileDeposit(member, hotelPage));
    }
	
	@ApiOperation(value = "我的押金 垃圾箱按钮", notes = "我的押金 垃圾箱按钮")
    @PostMapping(value = "{hotelId}/deposit/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<DepositVo>> moblieDepositD(
    		@PathVariable("hotelId") Integer hotelId, 
    		@PathVariable("orderId") Integer orderId, 
    		HttpServletRequest request) {
//		THotel hotel = tHotelService.selectById(hotelId);
//    	Member member = SessionUtils.getLoginMember(request, hotel.getBusId());
    	//test
    	Member member = new Member();
    	member.setId(1071);
    	member.setBusid(33);
    	member.setPhone("13433550667");
    	member.setPublicId(492);
    	member.setCardid("15338");
		//test
    	Wrapper<TOrderRoom> wrapper = new EntityWrapper<>();
    	wrapper.eq("order_id", orderId);
		TOrderRoom entity = new TOrderRoom();
		entity.setDeposit(CommonConst.CLOSE);
		entity.setUpdatedBy(member.getId());
		if(!tOrderRoomService.update(entity, wrapper)) {
    		throw new ResponseEntityException(ResponseEnums.OPERATING_ERROR);
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
//		THotel hotel = tHotelService.selectById(hotelId);
//    	Member member = SessionUtils.getLoginMember(request, hotel.getBusId());
		//test
		Member member = new Member();
		member.setId(1071);
		member.setBusid(33);
		member.setPhone("13433550667");
		member.setPublicId(492);
		member.setCardid("15338");
		//test
		return ResponseDTO.createBySuccess();
	}
	
	/********************************************************* 我的房卡 *************************************************************************/
	
	@ApiOperation(value = "我的商城、会员卡、个人中心 链接", notes = "我的商城、会员卡、个人中心 链接")
	@GetMapping(value = "{hotelId}/linkList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<JSONObject> moblieLink(
			@PathVariable("hotelId") Integer hotelId, 
			HttpServletRequest request) {
		THotel hotel = tHotelService.selectById(hotelId);
//    	Member member = SessionUtils.getLoginMember(request, hotel.getBusId());
		//test
		Member member = new Member();
		member.setId(1071);
		member.setBusid(33);
		member.setPhone("13433550667");
		member.setPublicId(492);
		member.setCardid("15338");
		//test
		JSONObject json = new JSONObject();
		json.put("shop", property.getWxmpService().getServiceUrl() + "/mallPage/"+hotel.getShopId()+"/79B4DE7C/getPageIds.do");
		json.put("memberCard", property.getWxmpService().getServiceUrl() + "/phoneMemberController/"+member.getPublicId()+"/79B4DE7C/findMember.do");
		json.put("center", property.getWxmpService().getServiceUrl() + "/phonePersonCenterController/"+member.getPublicId()+"/79B4DE7C/personCenterIndex.do");
		return ResponseDTO.createBySuccess(json);
	}
	
}
