package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 移动端 房卡对象
 * @author Reverien9@gmail.com
 * 2017年12月11日 上午9:53:57
 */
@Data
@Api(description = "移动端 房卡对象")
public class RoomCardVo {

	@ApiModelProperty(value = "订单ID")
	private Integer orderId;
	
	@ApiModelProperty(value = "房卡号")
	private Integer roomNum;
	
	@ApiModelProperty(value = "早餐券")
	private Integer breakfastQuantity;
	
	@ApiModelProperty(value = "是否免押金  0 是 1 否")
	private Integer freeDepositEnable;
	
	@ApiModelProperty(value = "会员 最晚退房时间(需要默认退房时间均为离店当天14点)")
	private Integer vipCheckOut;
	
	@ApiModelProperty(value = "客房数量 默认1")
	private Integer number;
	
	@ApiModelProperty(value = "门市价")
	private Integer rackRate;
	
	@ApiModelProperty(value = "押金")
	private Integer deposit;
	
	@ApiModelProperty(value = "粉币折扣金额")
	private Integer fbDiscount;
	
	@ApiModelProperty(value = "积分折扣金额")
	private Integer integralDiscount;
	
	@ApiModelProperty(value = "优惠券折扣金额")
	private Integer couponsDiscount;
	
	@ApiModelProperty(value = "会员等级 目前只有4个等级 1,2,3,4")
	private Integer vipLevel;
	
	@ApiModelProperty(value = "房价")
	private Integer roomPrice;

	@ApiModelProperty(value = "酒店名字")
	private String hotelName;
	
	@ApiModelProperty(value = "入住人名称")
	private String customerName;
	
	@ApiModelProperty(value = "入住人手机")
	private String customerPhone;
	
}
