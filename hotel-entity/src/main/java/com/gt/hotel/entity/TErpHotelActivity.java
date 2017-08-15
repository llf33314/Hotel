package com.gt.hotel.entity;

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

/**
 * <p>
 * ERP酒店-活动
 * </p>
 *
 * @author 
 * @since 2017-08-14
 */
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_activity")
public class TErpHotelActivity extends Model<TErpHotelActivity> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 商家id
     */
	@TableField("bus_id")
	private Integer busId;
    /**
     * 所属酒店id
     */
	@TableField("hotel_id")
	private Integer hotelId;
    /**
     * 活动名称
     */
	@TableField("activity_name")
	private String activityName;
    /**
     * 生效时间 始
     */
	@TableField("activity_stime")
	private Date activityStime;
    /**
     * 生效时间 末
     */
	@TableField("activity_etime")
	private Date activityEtime;
    /**
     * 类型（0=秒杀, 1=团购, 2=特价房, 3=时租房）
     */
	private Integer type;
    /**
     * 是否开启房间限购
     */
	@TableField("is_room_limit")
	private Integer isRoomLimit;
    /**
     * 房间限购数（间/人）
     */
	@TableField("room_limit")
	private Integer roomLimit;
    /**
     * 是否关联会员卡
     */
	@TableField("is_vipcard")
	private Integer isVipcard;
    /**
     * 是否关联卡券
     */
	@TableField("is_cardvolume")
	private Integer isCardvolume;
    /**
     * 规则
     */
	private String rule;
    /**
     * 房间剩余数
     */
	@TableField("is_room_count")
	private Integer isRoomCount;
    /**
     * 当房剩余 room_count  间时显
     */
	@TableField("room_count")
	private Integer roomCount;
    /**
     * 活动状态(0=未开始, 1=进行中, 2=已结束)
     */
	@TableField("activity_status")
	private Integer activityStatus;
    /**
     * 创建时间
     */
	private Date createtime;
    /**
     * 居住开始时间
     */
	@TableField("live_stime")
	private Date liveStime;
    /**
     * 居住结束时间
     */
	@TableField("live_etime")
	private Date liveEtime;
    /**
     * 团购最低间数
     */
	private Integer grouplowest;
    /**
     * 当达不到最低间数提示
     */
	private String grouplowesttip;
    /**
     * 时租房使用时间
     */
	@TableField("limit_hour")
	private Integer limitHour;
    /**
     * 时租房可预订起始时间
     */
	private Date whenrentstime;
    /**
     * 时租房可预订结束时间
     */
	private Date whenrentetime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelActivity{" +
			"id=" + id +
			", busId=" + busId +
			", hotelId=" + hotelId +
			", activityName=" + activityName +
			", activityStime=" + activityStime +
			", activityEtime=" + activityEtime +
			", type=" + type +
			", isRoomLimit=" + isRoomLimit +
			", roomLimit=" + roomLimit +
			", isVipcard=" + isVipcard +
			", isCardvolume=" + isCardvolume +
			", rule=" + rule +
			", isRoomCount=" + isRoomCount +
			", roomCount=" + roomCount +
			", activityStatus=" + activityStatus +
			", createtime=" + createtime +
			", liveStime=" + liveStime +
			", liveEtime=" + liveEtime +
			", grouplowest=" + grouplowest +
			", grouplowesttip=" + grouplowesttip +
			", limitHour=" + limitHour +
			", whenrentstime=" + whenrentstime +
			", whenrentetime=" + whenrentetime +
			", updateTime=" + updateTime +
			"}";
	}
}
