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
 * ERP酒店-房间-订单-房间
 * </p>
 *
 * @author 
 * @since 2017-08-17
 */
@ApiModel("TErpHotelRoomOrderSuite")
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_room_order_suite")
public class TErpHotelRoomOrderSuite extends Model<TErpHotelRoomOrderSuite> {

    private static final long serialVersionUID = 1L;

    /**
     * 入住方式(0：散客)
     */
    public static final Integer FIT = 0;
    /**
     * 入住方式(1：协议单位)
     */
    public static final Integer UNIT = 1;
    /**
     * 入住方式(2：团队)
     */
    public static final Integer TEAM = 2;
    
    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 订单ID
     */
	@ApiModelProperty("订单ID")
	@TableField("order_id")
	private Integer orderId;
    /**
     * 房间ID
     */
	@ApiModelProperty("房间ID")
	@TableField("room_suite_id")
	private Integer roomSuiteId;
    /**
     * 入住方式(0：散客，1：协议单位， 2：团队)
     */
	@ApiModelProperty("入住方式(0：散客，1：协议单位， 2：团队)")
	@TableField("check_in_mode")
	private Integer checkInMode;
    /**
     * 房价
     */
	@ApiModelProperty("房价")
	private Integer price;
    /**
     * 入住天数
     */
	@ApiModelProperty("入住天数")
	@TableField("check_in_days")
	private Integer checkInDays;
    /**
     * 备注
     */
	@ApiModelProperty("备注")
	private String remark;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelRoomOrderSuite{" +
			"id=" + id +
			", orderId=" + orderId +
			", roomSuiteId=" + roomSuiteId +
			", checkInMode=" + checkInMode +
			", price=" + price +
			", checkInDays=" + checkInDays +
			", remark=" + remark +
			"}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getRoomSuiteId() {
		return roomSuiteId;
	}

	public void setRoomSuiteId(Integer roomSuiteId) {
		this.roomSuiteId = roomSuiteId;
	}

	public Integer getCheckInMode() {
		return checkInMode;
	}

	public void setCheckInMode(Integer checkInMode) {
		this.checkInMode = checkInMode;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getCheckInDays() {
		return checkInDays;
	}

	public void setCheckInDays(Integer checkInDays) {
		this.checkInDays = checkInDays;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
