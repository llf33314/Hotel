package com.gt.hotel.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.api.bean.session.Member;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dao.TOrderRoomDAO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.entity.TOrderCoupons;
import com.gt.hotel.entity.TOrderRoom;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.param.RoomCategoryParameter.MobileQueryRoomCategory;
import com.gt.hotel.param.RoomMobileParameter.BookParam;
import com.gt.hotel.util.DateUtil;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.MobileRoomCategoryVo;
import com.gt.hotel.vo.MobileRoomOrderVo;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.TOrderCouponsService;
import com.gt.hotel.web.service.TOrderRoomService;
import com.gt.hotel.web.service.TOrderService;
import com.gt.hotel.web.service.TRoomCategoryService;

/**
 * <p>
 * 酒店订单 客房订单 (客单) 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
@Service
public class TOrderRoomServiceImpl extends BaseServiceImpl<TOrderRoomDAO, TOrderRoom> implements TOrderRoomService {

	@Autowired
	THotelService hotelService;
	
	@Autowired
	TOrderService orderService;
	
	@Autowired
	TOrderRoomService orderRoomService;
	
	@Autowired
	TOrderCouponsService couponsService;
	
	@Autowired
	TOrderRoomDAO tOrderRoomDAO;

	@Autowired
    WXMPApiUtil wXMPApiUtil;

	@Autowired
	TRoomCategoryService tRoomCategoryService;

	@Autowired
	THotelService tHotelService;
	
	@Transactional
	@Override
	public Integer MobileBookOrder(THotel hotel, Member member, BookParam bookParam) {
		try {
			if(!bookParam.getPayPrice().equals(MobilePriceCalculation(hotel.getId(), member, bookParam))) {
				throw new ResponseEntityException(ResponseEnums.PRICE_FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseEntityException(ResponseEnums.BOOK_FAILED);
		}
		Date date = new Date();
		TOrder order = new TOrder();
		TOrderRoom orderRoom = new TOrderRoom();
		TOrderCoupons coupons = new TOrderCoupons();
		BeanUtils.copyProperties(bookParam, order);
		BeanUtils.copyProperties(bookParam, orderRoom);
		BeanUtils.copyProperties(bookParam, coupons);
		
		order.setOrderNum("DD"+System.currentTimeMillis());
		order.setHotelId(hotel.getId());
		order.setBusId(member.getBusid());
		order.setMemberId(member.getId());
		order.setCreateTime(date);
		order.setRealPrice(bookParam.getPayPrice());
		order.setBillPrice(bookParam.getPayPrice());
		order.setReceivablePrice(bookParam.getPayPrice());
		order.setCreatedAt(date);
		order.setCreatedBy(member.getId());
		order.setUpdatedBy(member.getId());
		if(!orderService.insert(order)) {
			throw new ResponseEntityException(ResponseEnums.BOOK_FAILED);
		}
		
		orderRoom.setOrderId(order.getId());
		orderRoom.setOrderNum(order.getOrderNum());
		orderRoom.setHotelId(hotel.getId());
		orderRoom.setHotelName(hotel.getName());
		orderRoom.setReceivablePrice(bookParam.getPayPrice());
		orderRoom.setRoomPrice(bookParam.getDisplayPrice());
		orderRoom.setFrom(CommonConst.SOURCE_MOBILE);
		orderRoom.setGuestType(0);
		orderRoom.setCreatedAt(date);
		orderRoom.setCreatedBy(member.getId());
		orderRoom.setUpdatedBy(member.getId());
		if(!orderRoomService.insert(orderRoom)) {
			throw new ResponseEntityException(ResponseEnums.BOOK_FAILED);
		}
		
		coupons.setOrderId(order.getId());
		coupons.setOrderNum(order.getOrderNum());
		if(!couponsService.insert(coupons)) {
			throw new ResponseEntityException(ResponseEnums.BOOK_FAILED);
		}
		Wrapper<TOrder> wrapper = new EntityWrapper<>();
		wrapper.eq("id", order.getId());
		TOrder orderII = new TOrder();
		orderII.setOrderCouponsId(coupons.getId());
		if(!orderService.update(orderII, wrapper)) {
			throw new ResponseEntityException(ResponseEnums.BOOK_FAILED);
		}
		return order.getId();
	}

	@Override
	public MobileRoomOrderVo queryMobileRoomOrderOne(Integer hotelId, Integer orderId, Member member) {
		return tOrderRoomDAO.queryMobileRoomOrderOne(hotelId, orderId, member.getId());
	}

	@Transactional
	@Override
	public JSONObject moblieHotelRoomPayNotifyUrl(Map<String, Object> param, Integer orderId) {
		JSONObject json = new JSONObject();
    	if(param.get("out_trade_no") == null) {
    		throw new ResponseEntityException(ResponseEnums.OPERATING_ERROR);
    	}
    	int payType = Integer.valueOf(param.get("payType").toString());
    	Date date = new Date();
    	Wrapper<TOrder> wrapper = new EntityWrapper<>();
    	wrapper.eq("id", orderId);
    	wrapper.eq("order_num", param.get("out_trade_no"));
		TOrder tOrder = orderService.selectOne(wrapper);
    	tOrder.setPayStatus(CommonConst.PAY_STATUS_PAID);
    	tOrder.setPayTime(date);
		tOrder.setPayType(payType == 0 ? CommonConst.PAY_TYPE_WX : CommonConst.PAY_TYPE_ALI);
		if(!tOrder.updateById()) {
			throw new ResponseEntityException(ResponseEnums.OPERATING_ERROR);
		}
		Wrapper<TOrderRoom> fwrapper = new EntityWrapper<>();
		fwrapper.eq("order_id", orderId);
		fwrapper.eq("order_num", param.get("out_trade_no"));
		TOrderRoom orderRoom = orderRoomService.selectOne(fwrapper);
		orderRoom.setPayStatus(CommonConst.PAY_STATUS_PAID);
		orderRoom.setPayTime(date);
		if(!orderRoom.updateById()) {
			throw new ResponseEntityException(ResponseEnums.OPERATING_ERROR);
		}
		json.put("code", 0);
		json.put("msg", "支付成功");
		return json;
	}

	@Override
	public Integer MobilePriceCalculation(Integer hotelId, Member member, BookParam bookParam) throws Exception {
		Integer price = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Integer days = DateUtil.differentDays(bookParam.getRoomInTime(), bookParam.getRoomOutTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(bookParam.getRoomInTime());
		cal.add(Calendar.DAY_OF_YEAR, -1);
		int ordinaryDays = 0;
		int weekendDays = 0;
		for(int i=0;i<days;i++) {
			cal.add(Calendar.DAY_OF_YEAR, 1);
			int week = cal.get(Calendar.DAY_OF_WEEK);
			if(week == Calendar.FRIDAY || week == Calendar.SATURDAY) {
				weekendDays++;				
			}else {
				ordinaryDays++;
			}
		}
		/* 会员 */
		THotel hotel = tHotelService.selectById(hotelId);
		MobileQueryRoomCategory req = new MobileQueryRoomCategory();
		req.setCategoryId(bookParam.getCategoryId());
		req.setRoomInTime(sdf.format(bookParam.getRoomInTime()));
		req.setRoomOutTime(sdf.format(bookParam.getRoomOutTime()));
		Page<MobileRoomCategoryVo> page = tRoomCategoryService.queryMobileRoomCategory(hotelId, req);
		JSONObject json = wXMPApiUtil.findMemberCard(member.getPhone(), member.getBusid(), hotel.getShopId());
		JSONObject card = json.getJSONObject("data");
		for(MobileRoomCategoryVo m : page.getRecords()) {
			if(m.getId().equals(bookParam.getCategoryId())) {
				price = m.getRackRate() * ordinaryDays + m.getWeekendFare() * weekendDays;
				if(card != null && card.getInteger("ctId").equals(CommonConst.CARD_TYPE_DISCOUNT_CARD)) {
					price = Double.valueOf(m.getRackRate() * card.getDouble("discount")).intValue() * days;
				}
			}
		}
		
		price += bookParam.getDeposit();
		price *= bookParam.getNumber();
		return price;
	}
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Integer days = DateUtil.differentDays(sdf.parse("2017-11-11 00:00:00"), sdf.parse("2017-11-18 00:00:00"));
		System.err.println(days);
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse("2017-11-11 00:00:00"));
		cal.add(Calendar.DAY_OF_YEAR, -1);
		for(int i=0;i<days;i++) {
			cal.add(Calendar.DAY_OF_YEAR, 1);
			System.out.println(sdf.format(cal.getTime()));
			System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		}
	}
	
}
