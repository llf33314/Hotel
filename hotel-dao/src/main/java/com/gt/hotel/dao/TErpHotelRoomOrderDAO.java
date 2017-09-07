package com.gt.hotel.dao;

import com.gt.hotel.entity.TErpHotelRoomOrder;
import com.gt.hotel.entity.TErpHotelRoomOrderEx;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * ERP酒店-房间-订单 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-08-17
 */
public interface TErpHotelRoomOrderDAO extends BaseMapper<TErpHotelRoomOrder> {

	List<TErpHotelRoomOrderEx> selectRoomOrderExport(Map<String, Object> p);

	Integer selectRoomOrderExportCount(Map<String, Object> p);
	
	void updateOrderDel(List<Integer> idList);

	Integer updateStatus(TErpHotelRoomOrder po);

}