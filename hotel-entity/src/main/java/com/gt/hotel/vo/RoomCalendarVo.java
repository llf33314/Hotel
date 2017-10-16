package com.gt.hotel.vo;

import java.util.Date;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(description = "房型日历价格对象")
public class RoomCalendarVo {

	@ApiModelProperty(value = "日历价格对象ID")
	private Integer id;
	
	@ApiModelProperty(value = "酒店ID")
	private Integer hotelId;
    
	@ApiModelProperty(value = "房型ID")
	private Integer roomCategoryId;
	
	@ApiModelProperty(value = "开始日期")
	private Date beginTime;
    
	@ApiModelProperty(value = "结束日期")
	private Date endTime;
    
	@ApiModelProperty(value = "日历价")
	private Integer price;
    
	@ApiModelProperty(value = "创建者ID")
	private Integer createdBy;
    
	@ApiModelProperty(value = "创建时间")
	private Date createdAt;
    
	@ApiModelProperty(value = "最后修改人 ID")
	private Integer updatedBy;
    
	@ApiModelProperty(value = "最后修改时间")
	private Date updatedAt;

}
