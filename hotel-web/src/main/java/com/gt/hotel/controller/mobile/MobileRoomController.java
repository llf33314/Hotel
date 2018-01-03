package com.gt.hotel.controller.mobile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gt.api.bean.session.Member;
import com.gt.api.exception.SignException;
import com.gt.api.util.KeysUtil;
import com.gt.entityBo.NewErpPaySuccessBo;
import com.gt.entityBo.PayTypeBo;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.TActivity;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.entity.THotelSetting;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.entity.TOrderCoupons;
import com.gt.hotel.entity.TOrderRoom;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.MemberCard;
import com.gt.hotel.param.RoomCategoryParameter;
import com.gt.hotel.param.RoomCategoryParameter.MobileQueryRoomCategory;
import com.gt.hotel.param.RoomMobileParameter;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.MobileRoomCategoryVo;
import com.gt.hotel.vo.MobileRoomOrderVo;
import com.gt.hotel.vo.RoomOrderPriceVO;
import com.gt.hotel.web.service.TActivityService;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.THotelSettingService;
import com.gt.hotel.web.service.TOrderCouponsService;
import com.gt.hotel.web.service.TOrderRoomService;
import com.gt.hotel.web.service.TOrderService;
import com.gt.hotel.web.service.TRoomCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 酒店移动端 订房
 *
 * @author Reverien9@gmail.com
 * 2017年11月14日 上午10:27:26
 */
@Slf4j
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
	TOrderCouponsService orderCouponsService;
	
	@Autowired
	TActivityService activityService;

	@Autowired
    WebServerConfigurationProperties properties;
	
	@Autowired
	THotelSettingService hotelSettingService;

    @ApiOperation(value = "预定", notes = "预定")
    @GetMapping(value = "{hotelId}/book/{categoryId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<MemberCard> moblieRoomBook(
            @PathVariable("hotelId") Integer hotelId,
            @PathVariable("categoryId") Integer categoryId,
            HttpServletRequest request) {
		THotel hotel = this.getHotelInfo(request);
        Member member = getMember(request);
        MemberCard memberCard = null;
        try {
            JSONObject json = wXMPApiUtil.findMemberCard(member.getPhone(), member.getBusid(), hotel.getShopId());
            if (json != null && json.getInteger("code").equals(0)) {
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
            HttpServletRequest request) throws ParseException {
        invalidParameter(bindingResult);
        /* 2017/12/20: 业务修订 总订单 1 ： N 客房订单 by:zhangmz */
        if (bookParam.getActivityId() != null) {
        	TActivity activity = activityService.selectById(bookParam.getActivityId());
        	if(activity.getEndTime().getTime() < System.currentTimeMillis()) {
        		return ResponseDTO.createByErrorMessage("活动已结束");
        	}
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            RoomCategoryParameter.MobileQueryRoomCategory req = new MobileQueryRoomCategory();
            req.setCategoryId(bookParam.getCategoryId());
            req.setRoomInTime(sdf.format(bookParam.getRoomInTime()));
            req.setRoomOutTime(sdf.format(bookParam.getRoomOutTime()));
            List<MobileRoomCategoryVo> mrcv = tRoomCategoryService.queryMobileRoomCategory(hotelId, req).getRecords();
            if (mrcv == null || mrcv.size() <= 0 || bookParam.getRoomOrderNum() > (mrcv.get(0).getRoomCount() - mrcv.get(0).getOrderCount())) {
                return ResponseDTO.createByErrorMessage("房间数量不足");
            }
        }
        THotel info = this.getHotelInfo(request);
        Member member = getMember(request);
        JSONObject json = new JSONObject();
        json.put("orderId", tOrderRoomService.mobileBookOrder(info, member, bookParam));
        Wrapper<THotelSetting> w = new EntityWrapper<>();
    	w.eq("hotel_id", hotelId);
		THotelSetting hotelSetting = hotelSettingService.selectOne(w);
		if(hotelSetting.getSmsEnable().equals(CommonConst.ENABLED)) {
			try {
				wXMPApiUtil.sendMsg(member.getBusid(), hotelSetting.getSmsPhone(), "您有新的订房订单，请到管理后查收(多粉平台)");
			} catch (SignException e) {
			}
		}
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
		try {
		    // 2017/12/21: 获取当前酒店信息，从session获取  by:zhangmz
			THotel hotel = this.getHotelInfo(request);
			Member member = getMember(request);
			TOrder tOrder = tOrderService.selectById(orderId);
			Wrapper<TOrderCoupons> w = new EntityWrapper<>();
			w.eq("order_id", orderId);
			TOrderCoupons orderCoupon = orderCouponsService.selectOne(w);
			MemberCard memberCard = null;
			JSONObject json = wXMPApiUtil.findMemberCard(member.getPhone(), member.getBusid(), hotel.getShopId());
			if(json != null && json.getInteger("code").equals(0)) {
				memberCard = JSONObject.toJavaObject(json.getJSONObject("data"), MemberCard.class);
			}
			if(memberCard != null && tOrder.getPayType().equals(CommonConst.PAY_TYPE_VALUE_CARD) && memberCard.getCtId() == CommonConst.CARD_TYPE_VALUE_CARD) {
				//会员卡支付
				NewErpPaySuccessBo bo = new NewErpPaySuccessBo();
				bo.setMemberId(member.getId());
				bo.setStoreId(hotel.getShopId());
				bo.setOrderCode(tOrder.getOrderNum());
				bo.setTotalMoney(tOrder.getReceivablePrice() / 100d);
				bo.setDiscountAfterMoney(tOrder.getRealPrice() / 100d);
				bo.setDiscountMoney((tOrder.getFbDiscount() + tOrder.getIntegralDiscount()) / 100d);
				bo.setUcType(1);
				if(orderCoupon != null) {
					bo.setDiscountMoney((tOrder.getFbDiscount() + tOrder.getIntegralDiscount() + orderCoupon.getCouponsDiscount()) / 100d);
					bo.setUseCoupon(1);
					bo.setCardId(orderCoupon.getCouponsId());
					bo.setCouponType(1);
				}else {
					bo.setUseCoupon(0);
				}
				bo.setUserFenbi(tOrder.getFb() == 0 ? 0 : 1);
				bo.setFenbiNum(tOrder.getFb() / 100d);
				bo.setUserJifen(tOrder.getIntegral() == 0 ? 0 : 1);
				bo.setJifenNum(tOrder.getIntegral() / 100);
				bo.setDataSource(judgeBrowser(request) == 1 ? 1 : 2);
				bo.setPayTypeBos(new ArrayList<PayTypeBo>());
				JSONObject jsonObject = wXMPApiUtil.newPaySuccessByErpBalance(bo);
				System.err.println(jsonObject);
				if(jsonObject.getInteger("code").equals(0)) {
			    	Date date = new Date();
			    	tOrder.setPayStatus(CommonConst.PAY_STATUS_PAID);
			    	tOrder.setPayTime(date);
//					tOrder.setPayType(judgeBrowser(request) == 1 ? CommonConst.PAY_TYPE_WX : CommonConst.PAY_TYPE_ALI);
					if(!tOrder.updateById()) {
						throw new ResponseEntityException(ResponseEnums.OPERATING_ERROR);
					}
					Wrapper<TOrderRoom> fwrapper = new EntityWrapper<>();
					fwrapper.eq("order_id", orderId);
					fwrapper.eq("order_num", tOrder.getOrderNum());
					TOrderRoom orderRoom = tOrderRoomService.selectOne(fwrapper);
					orderRoom.setPayStatus(CommonConst.PAY_STATUS_PAID);
					orderRoom.setPayTime(date);
					if(!orderRoom.updateById()) {
						throw new ResponseEntityException(ResponseEnums.OPERATING_ERROR);
					}
					modelAndView.setViewName("redirect:/mobile/index.html/#/book/roomSet/" + hotel.getId());
				}else {
					modelAndView.addObject("msg", "太贵了");
					modelAndView.setViewName("/error/defaultError.html");
				}
			}else {
				JSONObject SubQrPayParams = new JSONObject();
				SubQrPayParams.put("totalFee", tOrder.getRealPrice() / 100d);
				SubQrPayParams.put("model", CommonConst.PAY_MODEL_ROOM);
				SubQrPayParams.put("busId", tOrder.getBusId());
				SubQrPayParams.put("memberId", member.getId());
				SubQrPayParams.put("appidType", 0);
				SubQrPayParams.put("orderNum", tOrder.getOrderNum());
				SubQrPayParams.put("desc", "酒店订房");
				SubQrPayParams.put("isreturn", 1);
				SubQrPayParams.put("returnUrl", getHost(request)+"/mobile/78CDF1/room/"+hotelId+"/returnUrl/"+orderId);
//				SubQrPayParams.put("notifyUrl", getHost(request)+"/mobile/78CDF1/room/"+hotelId+"/notifyUrl/"+orderId);
				SubQrPayParams.put("isSendMessage", 0);
//    			SubQrPayParams.put("sendUrl", "");
				SubQrPayParams.put("payWay", 0);
				SubQrPayParams.put("sourceType", 1);
				System.err.println(SubQrPayParams.toJSONString());
				String obj = KeysUtil.getEncString(SubQrPayParams.toJSONString());
				modelAndView.setViewName("redirect:" + properties.getWxmpService().getApiMap().get("payapi").toString()+obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("msg", "支付失败");
			modelAndView.setViewName("/error/defaultError.html");
		}
    	return modelAndView;
    }
    
    @ApiOperation(value = "支付同步回调", notes = "支付同步回调", hidden = true)
    @GetMapping(value = "{hotelId}/returnUrl/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView moblieHotelRoomPayReturnUrl(@PathVariable("hotelId") Integer hotelId,
    		@PathVariable("orderId") Integer orderId, 
    		Integer busId, Integer memberId,
    		ModelAndView modelAndView,
    		HttpServletRequest request) {
        try{
        	tOrderRoomService.moblieHotelRoomPayReturnUrl(orderId);
        }catch (Exception e) {
        	modelAndView.addObject("msg", "支付回调失败");
        	modelAndView.setViewName("/error/defaultError.html");
		}
    	modelAndView.setViewName("redirect:/mobile/index.html/#/book/roomSet/" + hotelId);
    	return modelAndView;
    }

    @ApiOperation(value = "支付异步回调", notes = "支付异步回调", hidden = true)
    @PostMapping(value = "{hotelId}/notifyUrl/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject moblieHotelRoomPayNotifyUrl(@PathVariable("hotelId") Integer hotelId,
    		@PathVariable("orderId") Integer orderId, 
    		@RequestBody Map<String, Object> params,
    		HttpServletRequest request) {
        JSONObject json = new JSONObject();
        json.put("code", -1);
        json.put("msg", "支付失败");
        System.err.println(params);
        json = tOrderRoomService.moblieHotelRoomPayNotifyUrl(params, orderId);
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
            price = tOrderRoomService.mobilePriceCalculation(hotelId, member, bookParam);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.createByErrorMessage("价格计算出错");
        }
        return ResponseDTO.createBySuccess(price);
    }

}
