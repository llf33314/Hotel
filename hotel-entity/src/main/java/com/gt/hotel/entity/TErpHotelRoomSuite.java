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
 * ERP酒店房型楼层房间
 * </p>
 *
 * @author 
 * @since 2017-08-11
 */
@ApiModel("TErpHotelRoomSuite")
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_room_suite")
public class TErpHotelRoomSuite extends Model<TErpHotelRoomSuite> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 房型ID
     */
	@ApiModelProperty("房型ID")
	@TableField("room_id")
	private Integer roomId;
    /**
     * 楼层
     */
	@ApiModelProperty("楼层")
	private String floor;
    /**
     * 房间号
     */
	@ApiModelProperty("房间号")
	private String number;
    /**
     * 房间状态(第一行)
     */
	@ApiModelProperty("房间状态(第一行)")
	private String status;
    /**
     * 房态
     */
	@ApiModelProperty("房态")
	@TableField("room_status")
	private String roomStatus;
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
		return "TErpHotelRoomSuite{" +
			"id=" + id +
			", roomId=" + roomId +
			", floor=" + floor +
			", number=" + number +
			", status=" + status +
			", roomStatus=" + roomStatus +
			", remark=" + remark +
			"}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
