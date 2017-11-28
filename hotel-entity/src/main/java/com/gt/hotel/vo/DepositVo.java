package com.gt.hotel.vo;

import java.util.Date;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api("移动端 押金对象")
public class DepositVo {

	@ApiModelProperty("订单ID")
	private Integer orderId;
	
	@ApiModelProperty("退房说明")
	private String checkOutInstructions;
	
	@ApiModelProperty("押金")
	private Integer deposit;
	
	@ApiModelProperty("时间")
	private Date payTime;
	
	@ApiModelProperty("是否已退押金 0 true 1 false")
	private Integer isRefund;
}
