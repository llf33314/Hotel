package com.gt.hotel.web.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.gt.api.bean.session.Member;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TOrderFood;
import com.gt.hotel.param.FoodMobileParameter.FoodMobileBookOrder;
import com.gt.hotel.param.FoodMobileParameter.FoodMobileOrder;
import com.gt.hotel.vo.FoodSettleVo;

/**
 * <p>
 * 订餐订单 独立订单 服务类
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
public interface TOrderFoodService extends BaseService<TOrderFood> {

	/**
	 * 移动端 餐饮 下单
	 * @param member
	 * @param order
	 * @return 订单ID
	 */
	FoodSettleVo mobileFoodOrderBook(Member member, FoodMobileOrder order);

	/**
	 * 移动端 餐饮 订单 支付
	 * @param member
	 * @param order
	 */
	void mobileFoodOrderBookPay(Member member, FoodMobileBookOrder order);

	/**
	 * 支付异步回调
	 * @param param 
	 * @param orderId
	 * @return
	 */
	JSONObject moblieHotelFoodPayNotifyUrl(Map<String, Object> param, Integer orderId);

	/**
	 * 支付订单详情
	 * @param hotelId
	 * @param orderId
	 * @param member
	 * @return
	 */
	FoodSettleVo queryFoodOrderOne(Integer hotelId, Integer orderId, Member member);

	/**
	 * 支付同步回调
	 * @param param 
	 * @param orderId
	 * @return
	 */
	void moblieHotelFoodPayReturnUrl(Integer orderId);
	
}
