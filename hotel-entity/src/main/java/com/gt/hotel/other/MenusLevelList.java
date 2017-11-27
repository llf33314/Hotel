/**
  * Copyright 2017 bejson.com 
  */
package com.gt.hotel.other;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 所有的菜单 根据t_man_common_menus
 * @author Reverien9@gmail.com
 * 2017年11月23日 下午3:28:27
 */
@Data
@Api("所有的菜单 根据t_man_common_menus")
public class MenusLevelList {

	@ApiModelProperty("erp属性")
    private int erpmodel;
	
	@ApiModelProperty("erp名字")
    private String erpname;
    
	@ApiModelProperty("首页链接")
    private String erpurl;

}