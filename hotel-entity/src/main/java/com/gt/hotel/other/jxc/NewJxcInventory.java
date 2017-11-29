/**
  * Copyright 2017 bejson.com 
  */
package com.gt.hotel.other.jxc;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 进销存 库存(多单位)
 * @author Reverien9@gmail.com
 * 2017年11月29日 上午9:52:06
 */
@Api("进销存 库存(多单位)")
@Data
public class NewJxcInventory {

    private List<ViceUnits> viceUnits;
    
    @ApiModelProperty(value = "库存数量")
    private int amount;
    
    @ApiModelProperty(value = "编码")
    private String proCode;
    
    @ApiModelProperty(value = "主单位")
    private String unitName;
    
    @ApiModelProperty(value = "商品名称")
    private String name;
    
    @ApiModelProperty(value = "主单位ID")
    private int unitId;
    
    @ApiModelProperty(value = "商品ID")
    private int id;
    
    @ApiModelProperty(value = "商品分类")
    private String type;
    
    @ApiModelProperty(value = "门店")
    private String warehouse;
    
    @ApiModelProperty(value = "零售价(元)")
    private int retailPrice;
    
    @ApiModelProperty(value = "规格")
    private String spec;
    
    @ApiModelProperty(value = "条码")
    private String barCode;
    
    @ApiModelProperty(value = "成本价(元)")
    private String costPrice;
    

}