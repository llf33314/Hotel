package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 收入明细
 * @author Reverien9@gmail.com
 * 2017年11月22日 下午4:50:08
 */
@Api("收入明细")
@Data
public class IncomeDetailsVo {
	
	@ApiModelProperty("其他")
    private Integer otherPrice = 0;

	@ApiModelProperty("商品消费")
    private Integer commodityPrice = 0;
	
	@ApiModelProperty("日期")
	private String date;
	
	@ApiModelProperty("房间费用")
	private Integer roomPrice = 0;
}
