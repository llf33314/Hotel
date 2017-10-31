package com.gt.hotel.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class ActivityDetailVo {
    /**
     * 活动ID
     */
    @ApiModelProperty(value = "活动ID")
    private Integer activityId;
    /**
     * 活动类型 1 特价房 2 钟点房 3 秒杀房 4 团购房
     */
    @ApiModelProperty(value = "活动类型 1 特价房 2 钟点房 3 秒杀房 4 团购房")
    private Integer activityType;
    /**
     * 当activity=2 , 此项不能为空，入住时长。 单位分钟
     */
    @ApiModelProperty(value = "当activity=2 , 此项不能为空，入住时长。 单位分钟")
    private Integer checkInTime;
    
    @ApiModelProperty(value = "如果 activity_type=2 钟点房 客人可入住时间段 始")
    @JsonFormat(pattern = "HH:mm:ss")
    private Date checkInPeriod;
    
    @ApiModelProperty(value = "如果 activity_type=2 钟点房 客人可入住时间段 末")
    @JsonFormat(pattern = "HH:mm:ss")
	private Date checkOutPeriod;
    
    /**
     * 如果 activity=4 团购房 规则 最少预订房间数量
     */
    @ApiModelProperty(value = "如果 activity=4 团购房 规则 最少预订房间数量")
    private Integer minPurchaseNumber;
    /**
     * 创建者ID
     */
    @ApiModelProperty(value = "创建者ID")
    private Integer createdBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date    createdAt;
    /**
     * 最后修改人 ID
     */
    @ApiModelProperty(value = "最后修改人 ID")
    private Integer updatedBy;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value = "最后修改时间")
    private Date    updatedAt;

}
