package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api("客房预订 价格计算 返回 对象")
@Data
public class RoomOrderPriceVO {

	@ApiModelProperty("房费")
	private Integer roomPrice = 0;
	
	@ApiModelProperty("押金")
	private Integer deposit = 0;
	
	@ApiModelProperty("积分扣除")
	private Integer integralPrice = 0;
	
	@ApiModelProperty("粉币扣除")
	private Integer fenbiPrice = 0;
	
	@ApiModelProperty("优惠券扣除")
	private Integer couponPrice = 0;
	
	@ApiModelProperty("支付价")
	private Integer payPrice = 0;
	
}
