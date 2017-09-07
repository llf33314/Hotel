package com.gt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
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
 * 长包房
 * </p>
 *
 * @author 
 * @since 2017-08-11
 */
@ApiModel("TErpHotelLongTimeRoom")
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_long_time_room")
public class TErpHotelLongTimeRoom extends Model<TErpHotelLongTimeRoom> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 酒店ID
     */
	@ApiModelProperty("酒店ID")
	@TableField("hotel_id")
	private Integer hotelId;
    /**
     * 房型ID
     */
	@ApiModelProperty("房型ID")
	@TableField("room_id")
	private Integer roomId;
    /**
     * 房型类型
     */
	@ApiModelProperty("房型类型")
	private String type;
    /**
     * 规则名称
     */
	@ApiModelProperty("规则名称")
	@TableField("rule_name")
	private String ruleName;
    /**
     * 最少入住天数
     */
	@ApiModelProperty("最少入住天数")
	@TableField("minimum_day")
	private Integer minimumDay;
    /**
     * 价格
     */
	@ApiModelProperty("价格")
	private Integer price;
    /**
     * 押金
     */
	@ApiModelProperty("押金")
	private Integer deposit;
    /**
     * 创建者
     */
	@ApiModelProperty("创建者")
	private String creator;
    /**
     * 创建时间
     */
	@ApiModelProperty("创建时间")
	@TableField("create_time")
	private Date createTime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelLongTimeRoom{" +
			"id=" + id +
			", hotelId=" + hotelId +
			", roomId=" + roomId +
			", type=" + type +
			", ruleName=" + ruleName +
			", minimumDay=" + minimumDay +
			", price=" + price +
			", deposit=" + deposit +
			", creator=" + creator +
			", createTime=" + createTime +
			"}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Integer getMinimumDay() {
		return minimumDay;
	}

	public void setMinimumDay(Integer minimumDay) {
		this.minimumDay = minimumDay;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getDeposit() {
		return deposit;
	}

	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
