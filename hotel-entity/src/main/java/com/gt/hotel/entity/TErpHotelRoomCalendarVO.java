package com.gt.hotel.entity;

import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel("TErpHotelRoomCalendarVO")
public class TErpHotelRoomCalendarVO extends TErpHotelRoom {

	private static final long serialVersionUID = 7444666589236563531L;

	List<TErpHotelRoomCalendar> TErpHotelRoomCalendars;

	public TErpHotelRoomCalendarVO() {
		super();
	}

	public TErpHotelRoomCalendarVO(TErpHotelRoom room) {
		super();
		this.setId(room.getId());
		this.setHotelId(room.getHotelId());
		this.setPrice(room.getPrice());
		this.setIfDiscountPrice(room.getIfDiscountPrice());
		this.setDiscountPrice(room.getDiscountPrice());
		this.setIfWeekendPrice(room.getIfWeekendPrice());
		this.setWeekendPrice(room.getWeekendPrice());
		this.setIfMondayPrice(room.getIfMondayPrice());
		this.setMondayPrice(room.getMondayPrice());
		this.setIfTuesdayPrice(room.getIfTuesdayPrice());
		this.setTuesdayPrice(room.getTuesdayPrice());
		this.setIfWednesdayPrice(room.getIfWednesdayPrice());
		this.setWednesdayPrice(room.getWednesdayPrice());
		this.setIfThursdayPrice(room.getIfThursdayPrice());
		this.setThursdayPrice(room.getThursdayPrice());
		this.setIfSundayPrice(room.getIfSundayPrice());
		this.setSundayPrice(room.getSundayPrice());
	}
	
	public List<TErpHotelRoomCalendar> getTErpHotelRoomCalendars() {
		return TErpHotelRoomCalendars;
	}

	public void setTErpHotelRoomCalendars(List<TErpHotelRoomCalendar> tErpHotelRoomCalendars) {
		TErpHotelRoomCalendars = tErpHotelRoomCalendars;
	}
    
}
