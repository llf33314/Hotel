package com.gt.hotel.vo.erp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 套餐内的房型与价格
 * @author Reverien9@gmail.com
 * 2017年11月24日 下午5:46:58
 */
@Data
@Api("套餐内的房型与价格")
public class PackageRoomVo {

	private Integer id;

	@ApiModelProperty("协议套餐 ID")
	private Integer packageId;

	@ApiModelProperty("房型ID")
	private Integer categoryId;

	@ApiModelProperty("房型名称")
	private String categoryName;

	@ApiModelProperty("门市价")
	private Integer rackRate;

	@ApiModelProperty("折扣 百分比 整数")
	private Integer discount;

	@ApiModelProperty("协议价=门市价 * 折扣")
	private Integer agreementPrice;

}
