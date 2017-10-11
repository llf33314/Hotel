package com.gt.hotel.requestEntity;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新增or更新酒店请求对象
 *
 * @author ReverieNight@Foxmail.com
 */

@Api( description = "房型实体参数" )
public class RoomCategoryParameter {
	
	/**
	 * 房型列表查询参数
	 */
	@Data
	@Api( "房型列表查询参数" )
	public static class queryRoomCategory extends HotelPage{
		@ApiModelProperty( value = "酒店ID" )
		@NotNull(message = "酒店ID不能为空")
		private Integer hotelId;
		
		@ApiModelProperty( value = "房型ID" )
		private Integer roomCategoryId;
	}
	
	/**
	 * 新增 或 更新 参数
	 */
	@Data
	@Api( "保存 或 更新 参数" )
	public static class SaveOrUpdate {
		@ApiModelProperty( value = "酒店ID" )
		private Integer hotelId;
		
		@ApiModelProperty( "门店ID" )
		@NotNull( message = "门店ID不能为空" )
		private Integer shopId;
		
		
		
	}
	
}
