/**
  * Copyright 2017 bejson.com 
  */
package com.gt.hotel.other.jxc;

import io.swagger.annotations.Api;
import lombok.Data;

/**
 * 单位信息
 * @author Reverien9@gmail.com
 * 2017年11月24日 上午10:08:31
 */
@Api("单位信息")
@Data
public class Unit {

    private int createDate;

    private String createDateStr;
    
    private int createUid;
    
    private int id;
    
    private String name;
    
    private int rootUid;
    
}