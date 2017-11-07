package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @author Reverien9@gmail.com
 * 2017年11月6日 下午2:56:42
 */
@Data
@Api("订单与优惠券 1 : N ")
@Accessors(chain = true)
public class OrderCouponsVo {

	@ApiModelProperty(value = "ID")
	private Integer id;
	
	@ApiModelProperty(value = "订单ID")
	private Integer orderId;
	
	@ApiModelProperty(value = "订单号")
	private String orderNum;

	@ApiModelProperty(value = "优惠券ID")
	private Integer couponsId;

	@ApiModelProperty(value = "优惠券数量")
	private Integer couponsNum;

}
