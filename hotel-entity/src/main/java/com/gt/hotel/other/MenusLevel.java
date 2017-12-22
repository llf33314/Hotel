/**
  * Copyright 2017 bejson.com 
  */
package com.gt.hotel.other;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 拥有的菜单版本
 * @author Reverien9@gmail.com
 * 2017年11月23日 下午3:37:50
 */
@Api("拥有的菜单版本")
@Data
public class MenusLevel {

	@ApiModelProperty("菜单版本属性")
    private String levelid;

	@ApiModelProperty("菜单版本名字")
    private String levelname;

}