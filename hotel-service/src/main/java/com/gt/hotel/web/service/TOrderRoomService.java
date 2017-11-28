package com.gt.hotel.web.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.gt.api.bean.session.Member;
import com.gt.hotel.base.BaseService;
import com.alibaba.fastjson.JSONObject;
import com.gt.api.bean.session.Member;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.entity.TOrderRoom;
import com.gt.hotel.param.RoomMobileParameter.BookParam;
import com.gt.hotel.vo.CheackInListRevenueVo;
import com.gt.hotel.vo.MobileRoomOrderVo;
import com.gt.hotel.vo.RoomCheackInCountVo;
import com.gt.hotel.vo.RoomOrderPriceVO;

import java.util.Map;

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
	Integer mobileBookOrder(THotel hotel, Member member, BookParam bookParam);

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
	JSONObject moblieHotelRoomPayNotifyUrl(Map<String, Object> param, Integer orderId);

	/**
	 * 价格计算
	 * @param member
	 * @param bookParam
	 * @return
	 */
	RoomOrderPriceVO mobilePriceCalculation(Integer hotelId, Member member, BookParam bookParam) throws Exception;

	/**
	 * 会员消费总额
	 * @param memberId
	 * @return
	 */
	Integer queryMobileRoomOrderSUM(Integer memberId);

	/**
	 * 客房入住数
	 * @param busId
	 * @param shopId
	 * @return
	 */
	RoomCheackInCountVo roomCheckInCount(Integer busId, Integer shopId);

	/**
	 * 近一周入住率
	 * @param busId
	 * @param shopId
	 * @return
	 */
	List<CheackInListRevenueVo> erpGetOccupancyRevenue(String now, Integer busId, Integer shopId);

}
