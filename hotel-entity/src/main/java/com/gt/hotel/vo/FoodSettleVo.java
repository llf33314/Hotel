package com.gt.hotel.vo;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api("餐饮订单结算返回数据")
public class FoodSettleVo {
	
	@ApiModelProperty(value = "订单ID")
	private Integer orderId;
	
	@ApiModelProperty(value = "客户姓名")
	private String customerName;
	
	@ApiModelProperty(value = "客户联系电话")
	private String customerPhone;
	
	@ApiModelProperty(value = "下单时间")
	private Date createTime;
	
	@ApiModelProperty(value = "配送时间 单位分钟")
	private Integer deliveryTime;
	
	@ApiModelProperty(value = "菜品列表")
	private List<OrderFoodDetailVo> OrderFoodDetails;
}
