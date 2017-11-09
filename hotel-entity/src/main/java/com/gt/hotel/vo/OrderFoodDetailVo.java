package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订餐订单详情
 * @author Reverien9@gmail.com
 * 2017年11月9日 下午6:46:38
 */
@Data
@Api("订餐订单详情")
public class OrderFoodDetailVo {

	@ApiModelProperty("菜品订单ID")
	private Integer orderFoodId;
	
	@ApiModelProperty("菜品ID")
	private Integer foodId;

	@ApiModelProperty("菜名")
	private String foodName;

	@ApiModelProperty("单价")
	private Integer foodPrice;

	@ApiModelProperty("菜品提供方")
	private String foodProvidesName;

	@ApiModelProperty("菜品提供方电话")
	private String foodProvidesPhone;

	@ApiModelProperty("数量")
	private Integer foodNumber;

}
