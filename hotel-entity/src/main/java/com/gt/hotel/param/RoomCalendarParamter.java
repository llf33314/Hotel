package com.gt.hotel.param;

import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api(description = "房型日历价格参数")
public class RoomCalendarParamter {

	@Data
	@Api("房型日历价格 查询 参数")
	public static class Query extends HotelPage{
		
		@ApiModelProperty(value = "日历ID")
		private Integer id;
		
		@ApiModelProperty(value = "开始日期")
		private Date beginTime;
		
		@ApiModelProperty(value = "结束日期")
		private Date endTime;
	}
	
	@Data
	@Api("房型日历价格 编辑 参数")
	public static class SaveOrUpdate{
		@ApiModelProperty(value = "酒店ID")
		@NotNull(message = "酒店ID不能为空")
		private Integer hotelId;
		
		@ApiModelProperty(value = "房型ID")
		@NotNull(message = "房型ID不能为空")
		private Integer roomCategoryId;
		
		@ApiModelProperty(value = "日历ID(更新需要)")
		private Integer id;
		
		@ApiModelProperty(value = "开始日期")
		@NotNull(message = "开始日期不能为空")
		private Date beginTime;
		
		@ApiModelProperty(value = "结束日期")
		@NotNull(message = "结束日期不能为空")
		private Date endTime;
		
		@ApiModelProperty(value = "日历价")
		@NotNull(message = "日历价不能为空")
		private Integer price;
	}
	
}
