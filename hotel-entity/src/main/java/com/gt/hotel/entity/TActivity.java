package com.gt.hotel.entity;

<<<<<<< HEAD
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.enums.FieldFill;

import lombok.Data;
import lombok.experimental.Accessors;

=======
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6
/**
 * <p>
 * 活动设置 - 抽出活动公共设置
 * </p>
 *
<<<<<<< HEAD
 * @author 
 * @since 2017-10-09
 */
@Data
@Accessors(chain = true)
@TableName("t_activity")
public class TActivity extends Model<TActivity> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 酒店ID
     */
	@TableField("hotel_id")
	private Integer hotelId;
    /**
     * 外部活动编号 提供对外显示 ID 格式 前缀 HD+时间戳年月日时分秒毫秒 14位
     */
	@TableField("external_number")
	private String externalNumber;
    /**
     * 活动 开始时间
     */
	@TableField("begin_time")
	private Date beginTime;
    /**
     * 结束时间
     */
	@TableField("end_time")
	private Date endTime;
    /**
     * 可入住最大时间 活动开始后，预订入住时间 最大有效期。
     */
	@TableField("available_time")
	private Date availableTime;
    /**
     * 是否开启限购 0 — 开启  1 不限制  默认1
     */
	@TableField("restriction_enable")
	private Integer restrictionEnable;
    /**
     * 限购数量 当限购开启 - 限购最少限购1间
     */
	@TableField("restriction_number")
	private Integer restrictionNumber;
    /**
     * 是否开启显示房间数 0 是  1 不是 默认 1
     */
	@TableField("show_room_enable")
	private Integer showRoomEnable;
    /**
     * 显示剩余房间数的临界值 当show_room_enable=0 此值不能为空。最大999
     */
	@TableField("number_threshold")
	private Integer numberThreshold;
    /**
     * 活动规则
     */
	@TableField("activity_rules")
	private String activityRules;
    /**
     * 活动发布状态 0 开启 1 未开启 
     */
	@TableField("publish_status")
	private Integer publishStatus;
    /**
     * 0 正常 1 禁用 2 删除
     */
	@TableField("mark_modified")
	private Integer markModified;
    /**
     * 创建者ID
     */
	@TableField("created_by")
	private Integer createdBy;
    /**
     * 创建时间
     */
	@TableField("created_at")
	private Date createdAt;
    /**
     * 最后修改人 ID
     */
	@TableField("updated_by")
	private Integer updatedBy;
    /**
     * 最后修改时间
     */
	@TableField("updated_at")
	private Date updatedAt;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TActivity{" +
			"id=" + id +
			", hotelId=" + hotelId +
			", externalNumber=" + externalNumber +
			", beginTime=" + beginTime +
			", endTime=" + endTime +
			", availableTime=" + availableTime +
			", restrictionEnable=" + restrictionEnable +
			", restrictionNumber=" + restrictionNumber +
			", showRoomEnable=" + showRoomEnable +
			", numberThreshold=" + numberThreshold +
			", activityRules=" + activityRules +
			", publishStatus=" + publishStatus +
			", markModified=" + markModified +
			", createdBy=" + createdBy +
			", createdAt=" + createdAt +
			", updatedBy=" + updatedBy +
			", updatedAt=" + updatedAt +
			"}";
	}
=======
 * @author
 * @since 2017-10-09
 */
@Data
@Accessors( chain = true )
@TableName( "t_activity" )
public class TActivity extends Model< TActivity > {

    private static final long serialVersionUID = 1L;

    @TableId( value = "id", type = IdType.AUTO )
    private Integer id;
    /**
     * 酒店ID
     */
    @TableField( "hotel_id" )
    private Integer hotelId;
    /**
     * 外部活动编号 提供对外显示 ID 格式 前缀 HD+时间戳年月日时分秒毫秒 14位
     */
    @TableField( "external_number" )
    private String  externalNumber;
    /**
     * 活动 开始时间
     */
    @TableField( "begin_time" )
    private Date    beginTime;
    /**
     * 结束时间
     */
    @TableField( "end_time" )
    private Date    endTime;
    /**
     * 可入住最大时间 活动开始后，预订入住时间 最大有效期。
     */
    @TableField( "available_time" )
    private Date    availableTime;
    /**
     * 是否开启限购 0 — 开启  1 不限制  默认1
     */
    @TableField( "restriction_enable" )
    private Integer restrictionEnable;
    /**
     * 限购数量 当限购开启 - 限购最少限购1间
     */
    @TableField( "restriction_number" )
    private Integer restrictionNumber;
    /**
     * 是否开启显示房间数 0 是  1 不是 默认 1
     */
    @TableField( "show_room_enable" )
    private Integer showRoomEnable;
    /**
     * 显示剩余房间数的临界值 当show_room_enable=0 此值不能为空。最大999
     */
    @TableField( "number_threshold" )
    private Integer numberThreshold;
    /**
     * 活动规则
     */
    @TableField( "activity_rules" )
    private String  activityRules;
    /**
     * 活动发布状态 0 开启 1 未开启
     */
    @TableField( "publish_status" )
    private Integer publishStatus;
    /**
     * 0 正常 1 禁用 2 删除
     */
    @TableField( "mark_modified" )
    private Integer markModified;
    /**
     * 创建者ID
     */
    @TableField( "created_by" )
    private Integer createdBy;
    /**
     * 创建时间
     */
    @TableField( "created_at" )
    private Date    createdAt;
    /**
     * 最后修改人 ID
     */
    @TableField( "updated_by" )
    private Integer updatedBy;
    /**
     * 最后修改时间
     */
    @TableField( "updated_at" )
    private Date    updatedAt;

    @Override
    protected Serializable pkVal() {
	return this.id;
    }

>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6
}
