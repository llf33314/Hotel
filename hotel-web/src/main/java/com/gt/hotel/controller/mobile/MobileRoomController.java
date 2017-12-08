package com.gt.hotel.controller.mobile;

import com.alibaba.fastjson.JSONObject;
import com.gt.api.bean.session.Member;
import com.gt.api.exception.SignException;
import com.gt.api.util.KeysUtil;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.MemberCard;
import com.gt.hotel.param.RoomMobileParameter;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.MobileRoomOrderVo;
import com.gt.hotel.vo.RoomOrderPriceVO;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.TOrderRoomService;
import com.gt.hotel.web.service.TOrderService;
import com.gt.hotel.web.service.TRoomCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
	
	@Autowired
	TOrderService tOrderService;
	
	@Autowired
    WebServerConfigurationProperties properties;

	@ApiOperation(value = "预定", notes = "预定")
    @GetMapping(value = "{hotelId}/book/{categoryId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<MemberCard> moblieRoomBook(
    		@PathVariable("hotelId") Integer hotelId, 
    		@PathVariable("categoryId") Integer categoryId, 
    		HttpServletRequest request) {
    	THotel hotel = tHotelService.selectById(hotelId);
    	Member member = getMember(request);
    	MemberCard memberCard = null;
    	
    	try {
    		JSONObject json = wXMPApiUtil.findMemberCard(member.getPhone(), member.getBusid(), hotel.getShopId());
    		if(json != null && json.getInteger("code").equals(0)) {
    			memberCard = JSONObject.toJavaObject(json.getJSONObject("data"), MemberCard.class);
    		}
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
			BindingResult bindingResult, 
			HttpServletRequest request) {
    	invalidParameter(bindingResult);
    	THotel hotel = tHotelService.selectById(hotelId);
    	Member member = getMember(request);
    	JSONObject json = new JSONObject();
    	json.put("orderId", tOrderRoomService.mobileBookOrder(hotel, member, bookParam));
		return ResponseDTO.createBySuccess(json);
	}
	
	@ApiOperation(value = "支付订单详情", notes = "支付订单详情")
    @GetMapping(value = "{hotelId}/order/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<MobileRoomOrderVo> moblieHotelRoomOrderR(
    		@PathVariable("hotelId") Integer hotelId,
    		@PathVariable("orderId") Integer orderId, 
    		HttpServletRequest request) {
		Member member = getMember(request);
        return ResponseDTO.createBySuccess(tOrderRoomService.queryMobileRoomOrderOne(hotelId, orderId, member));
    }
	
	@ApiOperation(value = "立即支付", notes = "立即支付")
    @GetMapping(value = "{hotelId}/pay/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView moblieHotelRoomPay(
    		@PathVariable("hotelId") Integer hotelId,  
    		@PathVariable("orderId") Integer orderId,
    		HttpServletRequest request, 
    		ModelAndView modelAndView) {
    	TOrder tOrder = tOrderService.selectById(orderId);
    	JSONObject SubQrPayParams = new JSONObject();
    	SubQrPayParams.put("totalFee", tOrder.getRealPrice());
    	SubQrPayParams.put("model", CommonConst.PAY_MODEL_ROOM);
    	SubQrPayParams.put("busId", tOrder.getBusId());
    	SubQrPayParams.put("appidType", 0);
    	SubQrPayParams.put("orderNum", tOrder.getOrderNum());
    	SubQrPayParams.put("desc", "酒店订房");
    	SubQrPayParams.put("isreturn", 0);
    	SubQrPayParams.put("notifyUrl", getHost(request)+"/mobile/78CDF1/room/"+hotelId+"/notifyUrl/"+orderId);
    	SubQrPayParams.put("isSendMessage", 0);
//    	SubQrPayParams.put("sendUrl", "");
    	SubQrPayParams.put("payWay", 0);
    	SubQrPayParams.put("sourceType", 1);
		try {
			String obj;
			obj = KeysUtil.getEncString(SubQrPayParams.toJSONString());
			modelAndView.setViewName("redirect:" + properties.getWxmpService().getApiMap().get("payapi").toString()+obj);
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.setViewName("/error");
		}
    	return modelAndView;
    }
	
	@ApiOperation(value = "支付异步回调", notes = "支付异步回调", hidden = true)
    @PostMapping(value = "{hotelId}/notifyUrl/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject moblieHotelRoomPayNotifyUrl(@PathVariable("hotelId") Integer hotelId,
    		@PathVariable("orderId") Integer orderId, Map<String, Object> param,
    		HttpServletRequest request) {
    	JSONObject json = new JSONObject();
		json.put("code", -1);
		json.put("msg", "支付失败");
		json = tOrderRoomService.moblieHotelRoomPayNotifyUrl(param, orderId);
		return json;
    }
	
	@ApiOperation(value = "价格计算", notes = "价格计算")
    @PostMapping(value = "{hotelId}/getPrice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<RoomOrderPriceVO> moblieHotelRoomGetPrice(
    		@PathVariable("hotelId") Integer hotelId,
    		@RequestBody RoomMobileParameter.BookParam bookParam,
    		HttpServletRequest request) {
		Member member = getMember(request);
		RoomOrderPriceVO price = null;
		try {
			System.err.println(bookParam);
			price = tOrderRoomService.mobilePriceCalculation(hotelId, member, bookParam);
			System.err.println(price);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDTO.createByErrorMessage("价格计算出错");
		}
        return ResponseDTO.createBySuccess(price);
    }
	
}
