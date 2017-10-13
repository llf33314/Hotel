package com.gt.hotel.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 房间 请求对象
 *
 * @author ReverieNight@Foxmail.com
 */

@Api( description = "房间 实体参数" )
public class RoomParameter {
	
	@Data
	@Api( "保存 或 更新 参数" )
	public static class SaveOrUpdate {
		@ApiModelProperty( value = "房间ID(更新时需要)" )
		private Integer id;
		
		@ApiModelProperty( value = "房型ID", required = true )
		@NotNull(message = "房型ID不能为空")
		private Integer roomCategoryId;
		
		@ApiModelProperty( value = "房间编号", required = true )
		@NotEmpty(message = "房间编号不能为空")
		private String number;
		
		@ApiModelProperty( value = "楼层", required = true )
		@NotEmpty(message = "楼层不能为空")
		private String floor;
		
		@ApiModelProperty( value = "房间状态" )
		private String status = "在住";
	}
	
}

