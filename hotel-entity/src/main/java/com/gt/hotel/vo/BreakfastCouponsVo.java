package com.gt.hotel.vo;

import java.util.Date;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 早餐券
 * @author Reverien9@gmail.com
 * 2017年12月12日 下午2:25:53
 */
@Data
@Api("早餐券")
public class BreakfastCouponsVo {

	@ApiModelProperty(value = "早餐券ID")
	private Integer id;
	
	@ApiModelProperty(value = "订单ID")
	private Integer orderId;

	@ApiModelProperty(value = "房型ID")
	private Integer categoryId;

	@ApiModelProperty(value = "房号")
	private String roomNum;

	@ApiModelProperty(value = "早餐券编号")
	private String code;

	@ApiModelProperty(value = "核销状态 0 未核销 1 已核销")
	private Integer writeOffStatus;

	@ApiModelProperty(value = "核销人ID(memberId)")
	private Integer writer;

	@ApiModelProperty(value = "创建时间")
	private Date createdAt;

}
