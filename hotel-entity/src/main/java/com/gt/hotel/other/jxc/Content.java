/**
  * Copyright 2017 bejson.com 
  */
package com.gt.hotel.other.jxc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 进销存 库存
 * @author Reverien9@gmail.com
 * 2017年11月24日 上午10:04:52
 */
@Api("进销存 库存")
@Data
public class Content {

	@ApiModelProperty("库存数")
    private double amountSum;
   
    private String attrIds;
    
    private String attrNames;
    
    private String barCode;
    
    private long createDate;
    
    private String createDateStr;
    
    private String customCoding;
    
    private int earlyWarning;
    
    private int id;
    
    @ApiModelProperty("商品基本信息")
    private Product product;
    
    @ApiModelProperty("成本价")
    private int movePrice;

}