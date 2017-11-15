package com.gt.hotel.web.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
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
import com.gt.hotel.param.RoomMobileParameter.BookParam;
import com.gt.hotel.vo.MobileRoomOrderVo;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.TOrderCouponsService;
import com.gt.hotel.web.service.TOrderRoomService;
import com.gt.hotel.web.service.TOrderService;

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

	@Transactional
	@Override
	public Integer MobileBookOrder(THotel hotel, Member member, BookParam bookParam) {
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
	
}
