package com.gt.hotel.web.service;

import com.gt.hotel.entity.TErpHotelRoomSuite;
import com.gt.hotel.entity.TErpHotelRoomSuiteFloorVer;

import java.util.List;

import com.gt.hotel.base.BaseService;

/**
 * <p>
 * ERP酒店房型楼层房间 服务类
 * </p>
 *
 * @author 
 * @since 2017-08-11
 */
public interface TErpHotelRoomSuiteService extends BaseService<TErpHotelRoomSuite> {

	List<TErpHotelRoomSuiteFloorVer> selectFloorVerList(Integer roomId);
	
}
