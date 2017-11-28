/**
  * Copyright 2017 bejson.com 
  */
package com.gt.hotel.other.jxc;

import io.swagger.annotations.Api;
import lombok.Data;

/**
 * 商品基本信息
 * @author Reverien9@gmail.com
 * 2017年11月24日 上午10:07:47
 */
@Api("商品基本信息")
@Data
public class Product {

    private int buyPrice;

    private int createDate;
    
    private String createDateStr;
    
    private int createUid;
    
    private boolean discount;
    
    private int id;
    
    private String name;
    
    private boolean netSales;
    
    private String origin;
    
    private String proAttrs;
    
    private ProductType productType;
    
    private int retailPrice;
    
    private boolean returns;
    
    private int rootUid;
    
    private int sellPrice;
    
    private boolean status;
    
    private int tradePrice;
    
    private int uidType;
    
    private Unit unit;
    
}
