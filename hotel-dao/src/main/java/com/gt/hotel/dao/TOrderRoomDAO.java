package com.gt.hotel.dao;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TOrderRoom;
import com.gt.hotel.vo.MobileRoomOrderVo;

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

}