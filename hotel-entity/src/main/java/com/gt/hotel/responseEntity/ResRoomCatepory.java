package com.gt.hotel.responseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 后台 - 房型响应对象对象
 * @author ReverieNight@Foxmail.com
 * @date 2017年10月10日 下午4:52:46
 */
@Api(description = "")
public class ResRoomCatepory {

	@Api(description = "房型列表对象")
	@Data
	public static class RoomCategoryList{
		@ApiModelProperty("酒店ID")
		private Integer hotelId;
		
		@ApiModelProperty("房型ID")
		private Integer roomCategoryId;
		
		private String name;
		
		private Integer rackRate;
	}
	
	
	
}
