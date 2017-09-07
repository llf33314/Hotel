package com.gt.hotel.web.service;

import com.gt.hotel.entity.TErpHotelImage;
import com.gt.hotel.entity.TErpHotelRoom;
import com.gt.hotel.entity.TErpHotelRoomAndCount;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;

/**
 * <p>
 * ERP酒店房型 服务类
 * </p>
 *
 * @author 
 * @since 2017-08-11
 */
public interface TErpHotelRoomService extends BaseService<TErpHotelRoom> {

	Integer roomInsertOrUpdate(TErpHotelRoom room, List<TErpHotelImage> imageList);

	boolean delRoom(List<Integer> idList);

	List<Integer> selectRoomIdsByHotelIds(List<Integer> idList);

	Page<TErpHotelRoomAndCount> selectHotelRoom(Page<TErpHotelRoomAndCount> page, Map<String, Object> param);
	
}
