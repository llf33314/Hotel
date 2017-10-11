package com.gt.hotel.responseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api(description = "房型列表对象")
@Data
public class RoomCategoryList{
	@ApiModelProperty("酒店ID")
	private Integer hotelId;
	
	@ApiModelProperty("房型ID")
	private Integer roomCategoryId;
	
	@ApiModelProperty("房间类型")
	private String name;
	
	@ApiModelProperty("门市价")
	private Integer rackRate;
	
	@ApiModelProperty("折扣率")
	private Integer discount;
	
	@ApiModelProperty("周末价")
	private Integer weekendFare;
	
	@ApiModelProperty("押金")
	private Integer deposit;
	
	@ApiModelProperty("房间总数")
	private Integer roomCount;
}