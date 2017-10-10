package com.gt.hotel.responseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * 酒店后台 房间管理 对象
 * @author ReverieNight@Foxmail.com
 * @date 2017年10月9日 下午5:46:04
 */
@Api(description = "酒店后台 房间管理 对象")
public class ResRoomCategory {
	
	@ApiModelProperty("房型ID")
	private Integer roomCategoryId;
	
	@ApiModelProperty("房型类型")
	private String name;
	
	@ApiModelProperty("房间总数")
	private Integer count;
	
	@ApiModelProperty("门市价")
	private Integer rackRate;
	
	@ApiModelProperty("押金")
	private Integer deposit;
	
	@ApiModelProperty("折扣率范围 1 - 100 默认 100(计算方式：85/100=0.85 即85折)，即不打折")
	private Integer discount;
	
	@ApiModelProperty("周末价格")
	private Integer weekendFare;

	public Integer getRoomCategoryId() {
		return roomCategoryId;
	}

	public void setRoomCategoryId(Integer roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getRackRate() {
		return rackRate;
	}

	public void setRackRate(Integer rackRate) {
		this.rackRate = rackRate;
	}

	public Integer getDeposit() {
		return deposit;
	}

	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getWeekendFare() {
		return weekendFare;
	}

	public void setWeekendFare(Integer weekendFare) {
		this.weekendFare = weekendFare;
	}

	@Override
	public String toString() {
		return "ResRoomCategory [roomCategoryId=" + roomCategoryId + ", name=" + name + ", count=" + count
				+ ", rackRate=" + rackRate + ", deposit=" + deposit + ", discount=" + discount + ", weekendFare="
				+ weekendFare + "]";
	}
	
	
}
