package com.gt.hotel.controller.mobile;

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
import com.gt.api.bean.session.Member;
import com.gt.api.exception.SignException;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.MemberCard;
import com.gt.hotel.param.RoomMobileParameter;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.MobileRoomOrderVo;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.TOrderRoomService;
import com.gt.hotel.web.service.TRoomCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 酒店移动端 订房
 * @author Reverien9@gmail.com
 * 2017年11月14日 上午10:27:26
 */
@Api(tags = "酒店移动端 订房")
@RestController
@RequestMapping("/mobile/78CDF1/room")
public class MobileRoomController extends BaseController {
	
	@Autowired
	THotelService tHotelService;
	
	@Autowired
	TRoomCategoryService tRoomCategoryService;
	
	@Autowired
	TOrderRoomService tOrderRoomService;
	
	@Autowired
    WXMPApiUtil wXMPApiUtil;

	@ApiOperation(value = "预定", notes = "预定")
    @GetMapping(value = "{hotelId}/book/{categoryId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<MemberCard> moblieRoomBook(
    		@PathVariable("hotelId") Integer hotelId, 
    		@PathVariable("categoryId") Integer categoryId, 
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
    	MemberCard memberCard = null;
    	
    	try {
    		JSONObject json = wXMPApiUtil.findMemberCard(member.getPhone(), member.getBusid(), hotel.getShopId());
    		memberCard = JSONObject.toJavaObject(json.getJSONObject("data"), MemberCard.class);
		} catch (SignException e) {
			throw new ResponseEntityException(ResponseEnums.FAILED_TO_OBTAIN_MEMBER_INFORMATION);
		}
        return ResponseDTO.createBySuccess(memberCard);
    }
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "提交订单", notes = "提交订单")
	@PostMapping(value = "{hotelId}/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO moblieRoomSubmit(
			@PathVariable("hotelId") Integer hotelId, 
			@Validated @RequestBody RoomMobileParameter.BookParam bookParam, 
			BindingResult bindingResult, HttpServletRequest request) {
    	InvalidParameter(bindingResult);
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
    	tOrderRoomService.MobileBookOrder(hotel, member, bookParam);
		return ResponseDTO.createBySuccess();
	}
	
	@ApiOperation(value = "支付订单详情", notes = "支付订单详情")
    @GetMapping(value = "{hotelId}/order/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<MobileRoomOrderVo> moblieHotelFoodOrderR(
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
        return ResponseDTO.createBySuccess(tOrderRoomService.queryMobileRoomOrderOne(hotelId, orderId, member));
    }
	
}
