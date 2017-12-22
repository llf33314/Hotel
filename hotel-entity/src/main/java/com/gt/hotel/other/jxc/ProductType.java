/**
  * Copyright 2017 bejson.com 
  */
package com.gt.hotel.other.jxc;

import java.util.List;

import io.swagger.annotations.Api;
import lombok.Data;

/**
 * 商品类别信息
 * @author Reverien9@gmail.com
 * 2017年11月24日 上午10:08:58
 */
@Api("商品类别信息")
@Data
public class ProductType {

    private long createDate;

    private String createDateStr;
    
    private int createUid;
    
    private int id;
    
    private String name;
    
    private int rootUid;
    
    private boolean status;
    
    private boolean sold;
    
    private List<ProductType> chlid;

}