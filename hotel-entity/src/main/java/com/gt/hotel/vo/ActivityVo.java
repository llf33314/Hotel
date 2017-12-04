package com.gt.hotel.vo;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ActivityVo {

    @ApiModelProperty(value = "活动ID")
    private Integer id;
    /**
     * 酒店ID
     */
    @ApiModelProperty(value = "酒店ID")
    private Integer hotelId;
    /**
     * 外部活动编号 提供对外显示 ID 格式 前缀 HD+时间戳年月日时分秒毫秒 14位
     */
    @ApiModelProperty(value = "外部活动编号 提供对外显示 ID 格式 前缀 HD+时间戳年月日时分秒毫秒 14位")
    private String  activityNum;
    /**
     * 活动 开始时间
     */
    @ApiModelProperty(value = "活动 开始时间")
    private Date    beginTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date    endTime;
    /**
     * 可入住最大时间 活动开始后，预订入住时间 最大有效期。
     */
    @ApiModelProperty(value = "可入住最大时间 活动开始后，预订入住时间 最大有效期。")
    private Date    availableTime;
    /**
     * 是否开启限购 0 — 开启  1 不限制  默认1
     */
    @ApiModelProperty(value = "是否开启限购 0 — 开启  1 不限制  默认1")
    private Integer restrictionEnable;
    /**
     * 限购数量 当限购开启 - 限购最少限购1间
     */
    @ApiModelProperty(value = "限购数量 当限购开启 - 限购最少限购1间")
    private Integer restrictionNumber;
    /**
     * 是否开启显示房间数 0 是  1 不是 默认 1
     */
    @ApiModelProperty(value = "是否开启显示房间数 0 是  1 不是 默认 1")
    private Integer showRoomEnable;
    /**
     * 显示剩余房间数的临界值 当show_room_enable=0 此值不能为空。最大999
     */
    @ApiModelProperty(value = "显示剩余房间数的临界值 当show_room_enable=0 此值不能为空。最大999")
    private Integer numberThreshold;
    /**
     * 活动规则
     */
    @ApiModelProperty(value = "活动规则")
    private String  activityRules;
    /**
     * 活动发布状态 0 开启 1 未开启
     */
    @ApiModelProperty(value = "活动发布状态 0 进行中 1 未开始 2 已结束 3 停止")
    private Integer publishStatus;
    /**
     * 0 正常 1 禁用 2 删除
     */
    @ApiModelProperty(value = "0 正常 1 禁用 2 删除")
    private Integer markModified;
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

    @ApiModelProperty(value = "活动详情表 根据活动类型不同整合各个活动所需字段")
    private ActivityDetailVo detail;
    
    @ApiModelProperty(value = "当前时间")
    private Date newTime = new Date(); 

    @ApiModelProperty(value = "活动房间 集合")
    private List<ActivityRoomVo> rooms;
    
    @ApiModelProperty(value = "活动名称")
	private String activityName;
}
