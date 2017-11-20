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
import com.gt.hotel.entity.TActivityDetail;
import com.gt.hotel.entity.TActivityRoom;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.entity.TOrderCoupons;
import com.gt.hotel.entity.TOrderRoom;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.DuofenCards;
import com.gt.hotel.other.MemberCard;
import com.gt.hotel.param.RoomCategoryParameter.MobileQueryRoomCategory;
import com.gt.hotel.param.RoomMobileParameter.BookParam;
import com.gt.hotel.util.DateUtil;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.ActivityDetailVo;
import com.gt.hotel.vo.MobileRoomCategoryVo;
import com.gt.hotel.vo.MobileRoomOrderVo;
import com.gt.hotel.vo.RoomOrderPriceVO;
import com.gt.hotel.web.service.TActivityDetailService;
import com.gt.hotel.web.service.TActivityRoomService;
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
	TActivityDetailService activityDetailService;
	
	@Autowired
	TActivityRoomService activityRoomService;
	
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
			RoomOrderPriceVO orderPriceVO = MobilePriceCalculation(hotel.getId(), member, bookParam);
			if(!bookParam.getPayPrice().equals(orderPriceVO.getPayPrice())) {
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
		try {
			orderRoom.setHourRoomCheckInTime(new SimpleDateFormat("HH:mm:ss").parse(bookParam.getHourRoomCheckInTime()));
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ResponseEntityException(ResponseEnums.BOOK_FAILED);
		}
		if(!orderRoomService.insert(orderRoom)) {
			throw new ResponseEntityException(ResponseEnums.BOOK_FAILED);
		}
		
		coupons.setOrderId(order.getId());
		coupons.setOrderNum(order.getOrderNum());
		coupons.setCouponsCode(bookParam.getCouponsCode());
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
		MobileRoomOrderVo mobileRoomOrderVo = tOrderRoomDAO.queryMobileRoomOrderOne(hotelId, orderId, member.getId());
		ActivityDetailVo activityDetailVo = new ActivityDetailVo();
		Wrapper<TActivityDetail> wrapper = new EntityWrapper<>();
		wrapper.eq("activity_id", mobileRoomOrderVo.getActivityId());
		TActivityDetail activityDetail = activityDetailService.selectOne(wrapper);
		BeanUtils.copyProperties(activityDetail, activityDetailVo);
		mobileRoomOrderVo.setActivityDetailVo(activityDetailVo);
		return mobileRoomOrderVo;
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
	public RoomOrderPriceVO MobilePriceCalculation(Integer hotelId, Member member, BookParam bookParam) throws Exception {
		RoomOrderPriceVO orderPriceVO = new RoomOrderPriceVO();
		Integer price = 0;
		/* 日期 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Integer days = DateUtil.differentDays(bookParam.getRoomInTime(), bookParam.getRoomOutTime());
		days = days == 0 ? 1 : days;
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
		/* 日期 */
		MobileQueryRoomCategory req = new MobileQueryRoomCategory();
		req.setCategoryId(bookParam.getCategoryId());
		req.setRoomInTime(sdf.format(bookParam.getRoomInTime()));
		req.setRoomOutTime(sdf.format(bookParam.getRoomOutTime()));
		THotel hotel = tHotelService.selectById(hotelId);
		Page<MobileRoomCategoryVo> page = tRoomCategoryService.queryMobileRoomCategory(hotelId, req);
		JSONObject json = wXMPApiUtil.findMemberCard(member.getPhone(), member.getBusid(), hotel.getShopId());
		JSONObject card = json.getJSONObject("data");
		
		for(MobileRoomCategoryVo m : page.getRecords()) {
			if(m.getId().equals(bookParam.getCategoryId())) {
				price = m.getRackRate() * ordinaryDays + m.getWeekendFare() * weekendDays;
				orderPriceVO.setRoomPrice(price);
				price = activityCalculate(bookParam, price, orderPriceVO, days);
				if(card != null && card.getInteger("ctId").equals(CommonConst.CARD_TYPE_DISCOUNT_CARD)) {
					if(bookParam.getActivityId() == null) {
						price = Double.valueOf(m.getRackRate() * card.getDouble("discount")).intValue() * days;
						orderPriceVO.setRoomPrice(price);
					}
					MemberCard memberCard = JSONObject.toJavaObject(card, MemberCard.class);
					price = DuofenCardsCalculate(bookParam, memberCard, price, orderPriceVO);
					price = IntegralCalculate(bookParam, memberCard, price, orderPriceVO);
					price = FenBiCalculate(bookParam, memberCard, price, orderPriceVO);
				}
			}
		}
		price += bookParam.getDeposit();
		price *= bookParam.getNumber();
		orderPriceVO.setDeposit(bookParam.getDeposit() * bookParam.getNumber());
		orderPriceVO.setPayPrice(price);
		return orderPriceVO;
	}

	/**
	 * 活动 计算
	 * @param bookParam
	 * @param price
	 * @param orderPriceVO
	 * @param days 
	 */

	private Integer activityCalculate(BookParam bookParam, Integer price, RoomOrderPriceVO orderPriceVO, Integer days) {
		if(bookParam.getActivityId() != null) {
			Wrapper<TActivityRoom> arw = new EntityWrapper<>();
			arw.eq("activity_id", bookParam.getActivityId());
			arw.eq("category_id", bookParam.getCategoryId());
			TActivityRoom ar = activityRoomService.selectOne(arw);
			price = ar.getActivityPrice() * days;
			orderPriceVO.setRoomPrice(price);
		}
		return price;
	}
	

	/**
	 * 积分 计算
	 * @param bookParam
	 * @param memberCard
	 * @param price
	 * @param orderPriceVO
	 * @param days
	 */
	private Integer IntegralCalculate(BookParam bookParam, MemberCard memberCard, Integer price, RoomOrderPriceVO orderPriceVO) {
		if(bookParam.getIntegral() != null) {
			if(price >= memberCard.getJifenStartMoney() * 100) {
				price -= memberCard.getJifenMoeny() * 100;
				orderPriceVO.setIntegralPrice(memberCard.getJifenMoeny() * 100);
			}
		}
		return price;
	}
	
	/**
	 * 粉币 计算
	 * @param bookParam
	 * @param memberCard
	 * @param price
	 * @param orderPriceVO
	 * @param days
	 */
	private Integer FenBiCalculate(BookParam bookParam, MemberCard memberCard, Integer price, RoomOrderPriceVO orderPriceVO) {
		if(bookParam.getFb() != null) {
			if(price >= memberCard.getFenbiStartMoney() * 100) {
				price -= memberCard.getFenbiMoeny() * 100;
				orderPriceVO.setFenbiPrice(memberCard.getFenbiMoeny() * 100);
			}
		}
		return price;
	}

	/**
	 * 卡券 计算
	 * @param memberCard
	 * @param price
	 * @param CouponsCode
	 * @param orderPriceVO
	 * @return
	 */
	private Integer DuofenCardsCalculate(BookParam bookParam, MemberCard memberCard, Integer price, RoomOrderPriceVO orderPriceVO) {
		if(bookParam.getCouponsCode() != null) {
			for(DuofenCards dc : memberCard.getDuofenCards()) {
				if(dc.getCode().equals(bookParam.getCouponsCode())) {
					if(dc.getCard_type() == 0) {
						int temp = price;
						price = Double.valueOf(price * dc.getDiscount() / 10).intValue();
						orderPriceVO.setCouponPrice(temp - price);
						orderPriceVO.setCouponCount(1);
					}else {
						if(price > (dc.getCash_least_cost() * 100)) {
							int dcCount = DuofenCardsCount(dc, price);
							price -= dc.getReduce_cost() * 100 * dcCount;
							orderPriceVO.setCouponPrice(dc.getReduce_cost() * 100 * dcCount);
							orderPriceVO.setCouponCount(dcCount);
						}
					}
				}
			}
		}
		return price;
	}
	
	private int DuofenCardsCount(DuofenCards dc, Integer price) {
		int dcCount = 0;
		int temp = price;
		for(int i=0;i<dc.getCountId();i++) {
			if(temp > dc.getCash_least_cost()) {
				temp -= dc.getReduce_cost();
				dcCount++;
			}else {
				break;
			}
		}
		return dcCount;
	}
	
}
