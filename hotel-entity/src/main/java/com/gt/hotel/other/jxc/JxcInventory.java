/**
  * Copyright 2017 bejson.com 
  */
package com.gt.hotel.other.jxc;
import java.util.List;

import io.swagger.annotations.Api;
import lombok.Data;

/**
 * 进销存 集合类
 * @author Reverien9@gmail.com
 * 2017年11月24日 上午10:04:03
 */
@Api("进销存 集合类")
@Data
public class JxcInventory {

    private List<Content> content;

    private boolean first;
    
    private boolean last;
    
    private int number;
    
    private int numberOfElements;
    
    private int size;
    
    private int totalElements;
    
    private int totalPages;

}