package com.gt.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TBreakfastCoupons;
import com.gt.hotel.vo.BreakfastCouponsVo;

/**
 * <p>
 * 早餐券 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-12-12
 */
public interface TBreakfastCouponsDAO extends BaseMapper<TBreakfastCoupons> {

	List<BreakfastCouponsVo> findBreakfastCoupons(@Param("orderId") Integer orderId, @Param("roomNum") String RoomNum);
	
}
