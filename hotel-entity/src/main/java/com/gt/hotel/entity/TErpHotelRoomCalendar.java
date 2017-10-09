package com.gt.hotel.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 酒店房型日历价格 可根据日期调整价格
 * </p>
 *
 * @author 
 * @since 2017-08-14
 */
@ApiModel("TErpHotelRoomCalendar")
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_room_calendar")
public class TErpHotelRoomCalendar extends Model<TErpHotelRoomCalendar> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
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
     * 价格
     */
	@ApiModelProperty("价格")
	private Integer price;
    /**
     * 时间日期
     */
	@ApiModelProperty("时间日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
	private Date time;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelRoomCalendar{" +
			"id=" + id +
			", roomId=" + roomId +
			", price=" + price +
			", time=" + time +
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
