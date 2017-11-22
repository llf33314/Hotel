package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 营业状况
 * @author Reverien9@gmail.com
 * 2017年11月21日 下午6:01:32
 */
@Api("营业状况")
@Data
public class BusinessConditionsVo {
	
	@ApiModelProperty("本日预抵店")
    private Integer PreAarrived;

	@ApiModelProperty("本日新增预订")
    private Integer newBookign;
	
	@ApiModelProperty("本日收入")
    private Integer income;
	
	@ApiModelProperty("本日支出")
    private Integer expenditure;

}
