package com.gt.hotel.web.service;

import com.gt.hotel.entity.TErpHotelImage;
import com.gt.hotel.entity.TErpHotelRoom;

import java.util.List;

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

	boolean roomInsertOrUpdate(TErpHotelRoom room, List<TErpHotelImage> imageList);

	boolean delRoom(List<Integer> idList);
	
}
