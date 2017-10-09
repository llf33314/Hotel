package com.gt.hotel.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "房型")
public class TErpHotelRoomAndCount extends TErpHotelRoom{

	private static final long serialVersionUID = 6422629676853934707L;
	
	@ApiModelProperty("房间数")
	Integer roomCount;

	public Integer getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}

	@Override
	public String toString() {
		return "TErpHotelRoomAndCount [roomCount=" + roomCount + "]";
	}

	
}
