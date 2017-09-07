package com.gt.hotel.entity;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * ERP酒店-活动-房间
 * </p>
 *
 * @author 
 * @since 2017-08-14
 */
@ApiModel(value = "TErpHotelActivityAndSuite")
public class TErpHotelActivityAndSuite extends TErpHotelActivity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("room array")
	private List<TErpHotelActivityRoomSuite> suites;

	public List<TErpHotelActivityRoomSuite> getSuites() {
		return suites;
	}

	public void setSuites(List<TErpHotelActivityRoomSuite> suites) {
		this.suites = suites;
	}
	
}
