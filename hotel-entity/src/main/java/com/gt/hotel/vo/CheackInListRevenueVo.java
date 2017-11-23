package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 客房入住数
 * @author Reverien9@gmail.com
 * 2017年11月22日 上午10:19:46
 */
@Api("近一周入住率 & 营收总额 对象")
@Data
public class CheackInListRevenueVo {
	
	@ApiModelProperty("入住率(%)")
    private Integer occupancyRate = 0;

	@ApiModelProperty("入住数")
    private Integer checkInRoomCount = 0;
	
	@ApiModelProperty("日期")
	private String date;
	
	@ApiModelProperty("营收总额")
	private Integer totalRevenue = 0;
}
