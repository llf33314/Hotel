package com.gt.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TOrderRoom;
import com.gt.hotel.vo.MobileRoomOrderVo;
import com.gt.hotel.vo.RoomCheackInCountVo;

/**
 * <p>
  * 酒店订单 客房订单 (客单) Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
public interface TOrderRoomDAO extends BaseMapper<TOrderRoom> {

	/**
	 * 移动端 房间 支付订单详情
	 * @param hotelId
	 * @param orderId
	 * @param memberId
	 * @return
	 */
	MobileRoomOrderVo queryMobileRoomOrderOne(@Param("hotelId") Integer hotelId, @Param("orderId") Integer orderId, @Param("memberId") Integer memberId);

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
	RoomCheackInCountVo roomCheckInCount(@Param("busId") Integer busId, @Param("shopId") Integer shopId);

	/**
	 * 入住房间数
	 * @param busId
	 * @param shopId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<TOrderRoom> getCheckInRoom(@Param("busId") Integer busId, @Param("shopId") Integer shopId, @Param("startDate") String startDate, @Param("endDate") String endDate);

}