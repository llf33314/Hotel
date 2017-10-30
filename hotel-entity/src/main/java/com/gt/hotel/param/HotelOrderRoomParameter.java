package com.gt.hotel.param;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单 房间 请求对象
 *
 * @author ReverieNight@Foxmail.com
 */
@Api(description = "订单 房间 请求对象")
public class HotelOrderRoomParameter {
	
	@Api("订单 房间 ")
	@Data
	public static class OrderRoom {
		@ApiModelProperty(value = "房型ID")
		private Integer categoryId;

		@ApiModelProperty(value = "房型名")
		private String categoryName;
		
		@ApiModelProperty(value = "房间ID")
		private Integer roomId;
		
		@ApiModelProperty(value = "房间号")
		private String roomNum;

	}

}
