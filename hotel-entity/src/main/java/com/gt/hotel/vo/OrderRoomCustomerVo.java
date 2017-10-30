package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api("订单房间")
@Data
public class OrderRoomCustomerVo {
	@ApiModelProperty(value = "房型ID")
	private Integer categoryId;

	@ApiModelProperty(value = "房型名")
	private String categoryName;
	
	@ApiModelProperty(value = "房间ID")
	private Integer roomId;
	
	@ApiModelProperty(value = "房间号")
	private String roomNum;
}
