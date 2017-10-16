package com.gt.hotel.vo;

import java.util.Date;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(description = "活动与房间关系")
public class ActivityRoomVo {

	@ApiModelProperty(value = "id")
    private Integer id;
	
	@ApiModelProperty(value = "活动ID")
	private Integer activityId;
    
	@ApiModelProperty(value = "酒店ID")
    private Integer hotelId;
    
	@ApiModelProperty(value = "房型ID")
    private Integer roomCategoryId;
    
	@ApiModelProperty(value = "房型名称 冗余参数")
    private String  roomCategoryName;
    
	@ApiModelProperty(value = "客房ID")
    private Integer roomId;
    
	@ApiModelProperty(value = "客房编号")
    private String  roomNumber;
    
	@ApiModelProperty(value = "门市价")
    private Integer rackRate;
    
	@ApiModelProperty(value = "活动价 单位分 记录")
    private Integer activityPrice;
    
	@ApiModelProperty(value = "状态标记")
    private Integer markModified;
    
	@ApiModelProperty(value = "创建者ID")
    private Integer createdBy;
    
	@ApiModelProperty(value = "创建时间")
    private Date createdAt;
    
	@ApiModelProperty(value = "最后修改人 ID")
    private Integer updatedBy;
    
	@ApiModelProperty(value = "最后修改时间")
    private Date    updatedAt;

}
