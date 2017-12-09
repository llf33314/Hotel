package com.gt.hotel.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Api(description = "移动端首页活动对象")
@Data
public class MobileActivityVo {
	@ApiModelProperty(value = "活动ID")
    private Integer id;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

	@ApiModelProperty(value = "酒店ID")
    private Integer hotelId;

	@ApiModelProperty(value = "外部活动编号 提供对外显示 ID 格式 前缀 HD+时间戳年月日时分秒毫秒 14位")
    private String  activityNum;

	@ApiModelProperty(value = "活动 开始时间")
    private Date    beginTime;

	@ApiModelProperty(value = "结束时间")
    private Date    endTime;

	@ApiModelProperty(value = "可入住最大时间 活动开始后，预订入住时间 最大有效期。")
    private Date    availableTime;

	@ApiModelProperty(value = "是否开启限购 0 — 开启  1 不限制  默认1")
    private Integer restrictionEnable;

	@ApiModelProperty(value = "限购数量 当限购开启 - 限购最少限购1间")
    private Integer restrictionNumber;

	@ApiModelProperty(value = "是否开启显示房间数 0 是  1 不是 默认 1")
    private Integer showRoomEnable;

	@ApiModelProperty(value = "显示剩余房间数的临界值 当show_room_enable=0 此值不能为空。最大999")
    private Integer numberThreshold;

	@ApiModelProperty(value = "活动规则")
    private String  activityRules;

	@ApiModelProperty(value = "活动发布状态 0 进行中 1 未开始 2 已结束 3 停止")
    private Integer publishStatus;
	
	@ApiModelProperty(value = "活动ID")
    private Integer activityId;

	@ApiModelProperty(value = "活动类型 1 特价房 2 钟点房 3 秒杀房 4 团购房")
    private Integer activityType;

    @ApiModelProperty(value = "当activity=2 , 此项不能为空，入住时长。 单位分钟")
    private Integer checkInTime;
    
    @ApiModelProperty(value = "如果 activity_type=2 钟点房 客人可入住时间段 始")
    @JsonFormat(pattern = "HH:mm:ss")
    private Date checkInPeriod;
    
    @ApiModelProperty(value = "如果 activity_type=2 钟点房 客人可入住时间段 末")
    @JsonFormat(pattern = "HH:mm:ss")
	private Date checkOutPeriod;
    
    @ApiModelProperty(value = "如果 activity=4 团购房 规则 最少预订房间数量")
    private Integer minPurchaseNumber;
    
    @ApiModelProperty(value = "房型ID")
    private Integer categoryId;

    @ApiModelProperty(value = "房型名称 冗余参数")
    private String categoryName;

    @ApiModelProperty(value = "客房ID")
    private Integer roomId;

    @ApiModelProperty(value = "客房编号")
    private String roomNum;

    @ApiModelProperty(value = "门市价")
    private Integer rackRate;

    @ApiModelProperty(value = "活动价 单位分 记录")
    private Integer activityPrice;
    
    @ApiModelProperty(value = "图片")
    private String path;
}