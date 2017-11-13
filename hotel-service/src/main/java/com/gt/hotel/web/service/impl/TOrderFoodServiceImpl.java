package com.gt.hotel.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.gt.hotel.dao.TOrderFoodDAO;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.entity.TOrderFood;
import com.gt.hotel.entity.TOrderFoodDetail;
import com.gt.hotel.entity.TRoom;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.param.FoodMobileParameter.FoodMobileBookOrder;
import com.gt.hotel.param.FoodMobileParameter.FoodMobileOrder;
import com.gt.hotel.vo.FoodSettleVo;
import com.gt.hotel.vo.OrderFoodDetailVo;
import com.gt.hotel.web.service.TOrderFoodDetailService;
import com.gt.hotel.web.service.TOrderFoodService;
import com.gt.hotel.web.service.TOrderService;
import com.gt.hotel.web.service.TRoomCategoryService;
import com.gt.hotel.web.service.TRoomService;

/**
 * <p>
 * 订餐订单 独立订单 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
@Service
public class TOrderFoodServiceImpl extends BaseServiceImpl<TOrderFoodDAO, TOrderFood> implements TOrderFoodService {

	@Autowired
	TOrderFoodDetailService tOrderFoodDetailService;
	
	@Autowired
	TOrderService tOrderService;
	
	@Autowired
	TOrderFoodService tOrderFoodService;
	
	@Autowired
	TRoomService tRoomService;
	
	@Autowired
	TRoomCategoryService tRoomCategoryService;
	
	@Transactional
	@Override
	public FoodSettleVo mobileFoodOrderBook(Member member, FoodMobileOrder order) {
		Date date = new Date();
		TOrder tOrder = new TOrder();
		TOrderFood tOrderFood = new TOrderFood();
		
		BeanUtils.copyProperties(order, tOrder);
		BeanUtils.copyProperties(order, tOrderFood);
		tOrder.setOrderNum("DD"+System.currentTimeMillis());
		tOrder.setBusId(member.getBusid());
		tOrder.setMemberId(member.getId());
		tOrder.setPayType(CommonConst.PAY_TYPE_WX);
		tOrder.setCreateTime(date);
		tOrder.setCreatedAt(date);
		tOrder.setCreatedBy(member.getBusid());
		tOrder.setUpdatedBy(member.getBusid());
		if(!tOrder.insert()) {
			throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
		}
		tOrderFood.setOrderId(tOrder.getId());
		tOrderFood.setOrderNum(tOrder.getOrderNum());
		tOrderFood.setRoomNum("0");
		tOrderFood.setCategoryId(0);
		tOrderFood.setCustomerName(" ");
		tOrderFood.setCustomerPhone(" ");
		tOrderFood.setCreateTime(date);
		tOrderFood.setCreatedAt(date);
		tOrderFood.setCreatedBy(member.getBusid());
		tOrderFood.setUpdatedBy(member.getBusid());
		if(!tOrderFood.insert()) {
			throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
		}
		List<OrderFoodDetailVo> detailVos = order.getFoods();
		List<TOrderFoodDetail> details = new ArrayList<>();
		for(OrderFoodDetailVo detailVo : detailVos) {
			TOrderFoodDetail tOrderFoodDetail = new TOrderFoodDetail();
			BeanUtils.copyProperties(detailVo, tOrderFoodDetail);
			tOrderFoodDetail.setOrderFoodId(tOrderFood.getId());
			details.add(tOrderFoodDetail);
		}
		if(!tOrderFoodDetailService.insertBatch(details)) {
			throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
		}
		FoodSettleVo foodSettleVo = new FoodSettleVo();
		foodSettleVo.setOrderId(tOrder.getId());
		foodSettleVo.setCreateTime(tOrder.getCreateTime());
		foodSettleVo.setCustomerName(member.getNickname());
		foodSettleVo.setCustomerPhone(member.getPhone());
		return foodSettleVo;
	}

	@Transactional
	@Override
	public void mobileFoodOrderBookPay(Member member, FoodMobileBookOrder order) {
		TOrder tOrder = tOrderService.selectById(order.getOrderId());
		tOrder.setPayType(order.getPayType());
		if(!tOrder.updateById()) {
			throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
		}
		
		Wrapper<TOrderFood> wrapper = new EntityWrapper<>();
		wrapper.eq("order_id", order.getOrderId());
		TOrderFood tOrderFood = tOrderFoodService.selectOne(wrapper);
		tOrderFood.setCustomerName(order.getCustomerName());
		tOrderFood.setCustomerPhone(order.getCustomerPhone());
		tOrderFood.setInvoiceHead(order.getInvoiceHead());
		tOrderFood.setRoomNum(order.getRoomNum());
		Wrapper<TRoom> rwrapper = new EntityWrapper<>();
		rwrapper.eq("number", order.getRoomNum());
		TRoom tRoom = tRoomService.selectOne(rwrapper);
		if(tRoom != null) {
			tOrderFood.setCategoryId(tRoom.getCategoryId());
			tOrderFood.setCategoryName(tRoomCategoryService.selectById(tRoom.getCategoryId()).getName());
		}
		if(!tOrderFood.updateById()) {
			throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
		}
	}

	@Transactional
	@Override
	public JSONObject moblieHotelFoodPayNotifyUrl(Map<String, Object> param, Integer orderId) {
		JSONObject json = new JSONObject();
    	if(param.get("out_trade_no") == null) {
    		throw new ResponseEntityException(ResponseEnums.OPERATING_ERROR);
    	}
    	int payType = Integer.valueOf(param.get("payType").toString());
    	Date date = new Date();
    	Wrapper<TOrder> wrapper = new EntityWrapper<>();
    	wrapper.eq("id", orderId);
    	wrapper.eq("order_num", param.get("out_trade_no"));
		TOrder tOrder = tOrderService.selectOne(wrapper);
    	tOrder.setPayStatus(CommonConst.PAY_STATUS_PAID);
    	tOrder.setPayTime(date);
		tOrder.setPayType(payType == 0 ? CommonConst.PAY_TYPE_WX : CommonConst.PAY_TYPE_ALI);
		if(!tOrder.updateById()) {
			throw new ResponseEntityException(ResponseEnums.OPERATING_ERROR);
		}
		Wrapper<TOrderFood> fwrapper = new EntityWrapper<>();
		fwrapper.eq("order_id", orderId);
		fwrapper.eq("order_num", param.get("out_trade_no"));
		TOrderFood tOrderFood = tOrderFoodService.selectOne(fwrapper);
		tOrderFood.setPayStatus(CommonConst.PAY_STATUS_PAID);
		tOrderFood.setPayTime(date);
		if(!tOrderFood.updateById()) {
			throw new ResponseEntityException(ResponseEnums.OPERATING_ERROR);
		}
		json.put("code", 0);
		json.put("msg", "支付成功");
		return json;
	}
	
}
