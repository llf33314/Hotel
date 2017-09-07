package com.gt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.enums.FieldFill;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * ERP酒店-活动-房型-房间
 * </p>
 *
 * @author 
 * @since 2017-08-14
 */
@ApiModel(value = "TErpHotelActivityRoomSuite")
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_activity_room_suite")
public class TErpHotelActivityRoomSuite extends Model<TErpHotelActivityRoomSuite> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 活动id
     */
	@ApiModelProperty(value = "活动id")
	@TableField("activity_id")
	private Integer activityId;
    /**
     * 房型ID
     */
	@ApiModelProperty(value = "房型ID")
	@TableField("room_id")
	private Integer roomId;
    /**
     * 房间ID
     */
	@ApiModelProperty(value = "房间ID")
	@TableField("suite_id")
	private Integer suiteId;
    /**
     * 活动价格
     */
	@ApiModelProperty(value = "活动价格")
	private Integer price;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelActivityRoomSuite{" +
			"id=" + id +
			", activityId=" + activityId +
			", roomId=" + roomId +
			", suiteId=" + suiteId +
			", price=" + price +
			"}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(Integer suiteId) {
		this.suiteId = suiteId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
