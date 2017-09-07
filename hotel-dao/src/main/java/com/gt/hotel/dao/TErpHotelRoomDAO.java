package com.gt.hotel.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TErpHotelRoom;
import com.gt.hotel.entity.TErpHotelRoomAndCount;

/**
 * <p>
  * ERP酒店房型 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-08-11
 */
public interface TErpHotelRoomDAO extends BaseMapper<TErpHotelRoom> {

	List<Integer> selectRoomIdsByHotelIds(List<Integer> idList);

	List<TErpHotelRoomAndCount> selectHotelRoom(Map<String, Object> param);

	Integer selectHotelRoomCount(Map<String, Object> param);

}