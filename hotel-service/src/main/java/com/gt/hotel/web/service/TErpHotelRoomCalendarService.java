package com.gt.hotel.web.service;

import java.util.Map;

import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TErpHotelRoomCalendar;
import com.gt.hotel.entity.TErpHotelRoomCalendarVO;

/**
 * <p>
 * 酒店房型日历价格 可根据日期调整价格 服务类
 * </p>
 *
 * @author 
 * @since 2017-08-14
 */
public interface TErpHotelRoomCalendarService extends BaseService<TErpHotelRoomCalendar> {

	TErpHotelRoomCalendarVO selectRoomCalendarAll(Map<String, Object> param);
	
}
