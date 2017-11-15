package com.gt.hotel.web.service;

import com.gt.hotel.entity.THotel;
import com.gt.hotel.entity.TOrderRoom;
import com.gt.hotel.param.RoomMobileParameter.BookParam;
import com.gt.hotel.vo.MobileRoomOrderVo;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.gt.api.bean.session.Member;
import com.gt.hotel.base.BaseService;

/**
 * <p>
 * 酒店订单 客房订单 (客单) 服务类
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
public interface TOrderRoomService extends BaseService<TOrderRoom> {

	/**
	 * 移动端 提交订单
	 * @param hotel
	 * @param member
	 * @param bookParam
	 * @return 订单ID
	 */
	Integer MobileBookOrder(THotel hotel, Member member, BookParam bookParam);

	/**
	 * 移动端 房间 支付订单详情
	 * @param hotelId
	 * @param orderId
	 * @param member
	 * @return
	 */
	MobileRoomOrderVo queryMobileRoomOrderOne(Integer hotelId, Integer orderId, Member member);

	/**
	 * 支付异步回调
	 * @param param
	 * @param orderId
	 * @return
	 */
	JSONObject moblieHotelRoomPayNotifyUrl(Map<String, Object> param, Integer orderId);;

}
