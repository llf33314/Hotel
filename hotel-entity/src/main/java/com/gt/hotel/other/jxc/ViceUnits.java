/**
  * Copyright 2017 bejson.com 
  */
package com.gt.hotel.other.jxc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 单位信息 (多单位)
 * @author Reverien9@gmail.com
 * 2017年11月29日 上午9:52:56
 */
@Api("单位信息 (多单位)")
@Data
public class ViceUnits {

	@ApiModelProperty(value = "销售单价")
    private int unitPrice;
    
	@ApiModelProperty(value = "副单位名称")
    private String unitName;
    
	@ApiModelProperty(value = "副单位id")
    private int unitId;
    
	@ApiModelProperty(value = "主副单位换算率(副单位数量 * 换算率 = 销售数量)")
    private int convertRatio;
    
	@ApiModelProperty(value = "条码")
    private String barCode;
    

}