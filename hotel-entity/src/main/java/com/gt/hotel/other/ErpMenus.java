/**
  * Copyright 2017 bejson.com 
  */
package com.gt.hotel.other;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ERP列表菜单
 * @author Reverien9@gmail.com
 * 2017年11月23日 下午3:36:13
 */
@Data
@Api("ERP列表菜单")
public class ErpMenus {

	@ApiModelProperty("拥有的菜单版本")
    private List<MenusLevel> menusLevel;
    
	@ApiModelProperty("所有的封装好的一级菜单 根据t_man_common_menus  里面的url会匹配到第一个跳转的url")
    private List<MenusOne> menusOne;
    
	@ApiModelProperty("所有的菜单 根据t_man_common_menus")
    private List<Menuslist> menuslist;

}