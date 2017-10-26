package com.gt.hotel.param;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api(description = "酒店 活动 参数实体类")
public class ActivityParamter {

	@Api(description = "酒店 活动 请求参数")
	@Data
	public static class ActivityQuery extends HotelPage{
		@ApiModelProperty(value = "活动ID")
		private Integer id;
		
		@ApiModelProperty(value = "酒店ID", required = true)
		@NotNull(message = "酒店ID不能为空")
		private Integer hotelId;
		
		@ApiModelProperty(value = "活动类型 1 特价房 2 钟点房 3 秒杀房 4 团购房", required = true)
		@NotNull(message = "活动类型不能为空")
	    private Integer activityType;
		
		@ApiModelProperty(value = "活动发布状态 0 开启 1 未开启")
		private Integer publishStatus;
	}
	
	@Data
	@Api("酒店 活动  编辑 参数")
	public static class ActivitySaveOrUpdate{
		@ApiModelProperty(value = "活动ID(更新需要)")
		private Integer id;
		
		@ApiModelProperty(value = "活动 开始时间")
		@NotNull(message = "开始时间不能为空")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date beginTime;
		
		@ApiModelProperty(value = "结束时间")
		@NotNull(message = "结束时间不能为空")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date endTime;
		
		@ApiModelProperty(value = "可入住最大时间 活动开始后，预订入住时间 最大有效期。")
		@NotNull(message = "可入住最大时间不能为空")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	    private Date availableTime;
	    
	    @ApiModelProperty(value = "是否开启限购 0 — 开启  1 不限制  默认1")
	    private Integer restrictionEnable;
	    
	    @ApiModelProperty(value = "限购数量 当限购开启 - 限购最少限购1间")
	    private Integer restrictionNumber;
	    
	    @ApiModelProperty(value = "是否开启显示房间数 0 是  1 不是 默认 1")
	    private Integer showRoomEnable;
	    
	    @ApiModelProperty(value = "显示剩余房间数的临界值 当show_room_enable=0 此值不能为空。最大999")
	    private Integer numberThreshold;
	    
	    @ApiModelProperty(value = "活动规则")
	    @NotEmpty(message = "活动规则不能为空")
	    private String  activityRules;
	    
	    @ApiModelProperty(value = "活动类型 1 特价房 2 钟点房 3 秒杀房 4 团购房")
	    @NotNull(message = "活动类型不能为空")
	    private Integer activityType;
	    
	    @ApiModelProperty(value = "当activity=2 , 此项不能为空，入住时长。 单位分钟")
	    private Integer checkInTime;
	    
	    @ApiModelProperty(value = "如果 activity_type=2 钟点房 客人可入住时间段")
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	    private Date checkInPeriod;
	    
	    @ApiModelProperty(value = "如果 activity=4 团购房 规则 最少预订房间数量")
	    private Integer minPurchaseNumber;
	    
	    @ApiModelProperty(value = "房间")
	    private List<ActivityRoomParam> rooms;
	}
	
	@Data
	@Api("酒店 活动房间 对象")
	public static class ActivityRoomParam{
		@ApiModelProperty(value = "酒店ID")
		@NotNull(message = "酒店ID不能为空")
	    private Integer hotelId;
	    
		@ApiModelProperty(value = "房型ID")
		@NotNull(message = "房型ID不能为空")
	    private Integer categoryId;
	    
		@ApiModelProperty(value = "房型名称 冗余参数")
		@NotEmpty(message = "房型名称不能为空")
	    private String  categoryName;
	    
		@ApiModelProperty(value = "客房ID")
		@NotNull(message = "客房ID不能为空")
	    private Integer roomId;
	    
		@ApiModelProperty(value = "客房编号")
		@NotEmpty(message = "客房编号不能为空")
	    private String  roomNumber;
	    
		@ApiModelProperty(value = "门市价")
		@NotNull(message = "门市价不能为空")
	    private Integer rackRate;
	    
		@ApiModelProperty(value = "活动价 单位分 记录")
		@NotNull(message = "活动价不能为空")
	    private Integer activityPrice;
	    
	}
}
