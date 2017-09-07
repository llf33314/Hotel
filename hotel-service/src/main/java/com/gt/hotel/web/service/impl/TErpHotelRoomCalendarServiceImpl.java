package com.gt.hotel.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TErpHotelRoomCalendarDAO;
import com.gt.hotel.entity.TErpHotelRoom;
import com.gt.hotel.entity.TErpHotelRoomCalendar;
import com.gt.hotel.entity.TErpHotelRoomCalendarVO;
import com.gt.hotel.web.service.TErpHotelRoomCalendarService;
import com.gt.hotel.web.service.TErpHotelRoomService;

/**
 * <p>
 * 酒店房型日历价格 可根据日期调整价格 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-08-14
 */
@Service
public class TErpHotelRoomCalendarServiceImpl extends BaseServiceImpl<TErpHotelRoomCalendarDAO, TErpHotelRoomCalendar> implements TErpHotelRoomCalendarService {

	@Autowired
	TErpHotelRoomService TErpHotelRoomService;
	
	@Override
	public TErpHotelRoomCalendarVO selectRoomCalendarAll(Map<String, Object> param) {
		TErpHotelRoom r = TErpHotelRoomService.selectById(param.get("room_id").toString());
		TErpHotelRoomCalendarVO rcv = new TErpHotelRoomCalendarVO(r);
		List<TErpHotelRoomCalendar> rcs = this.selectByMap(param);
		rcv.setTErpHotelRoomCalendars(rcs);
		return rcv;
	}
	
}
