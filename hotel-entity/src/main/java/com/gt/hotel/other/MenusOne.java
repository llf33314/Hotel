/**
  * Copyright 2017 bejson.com 
  */
package com.gt.hotel.other;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 所有的封装好的一级菜单 根据t_man_common_menus  里面的url会匹配到第一个跳转的url
 * @author Reverien9@gmail.com
 * 2017年11月23日 下午3:38:38
 */
@Data
@Api("所有的封装好的一级菜单 根据t_man_common_menus  里面的url会匹配到第一个跳转的url")
public class MenusOne {

	@ApiModelProperty("创建人")
    private int create_person;

	@ApiModelProperty("创建时间")
	private String create_time;
    
	@ApiModelProperty("菜单图标")
	private String icon;
    
	@ApiModelProperty("菜单切换图标")
	private String icon_lan;
    
	@ApiModelProperty("菜单ID")
	private int id;
    
	@ApiModelProperty("行业id（对应字典表里面的1018）")
	private int industryid;
    
	@ApiModelProperty("菜单版本，取自字典1193（[{\"item_value\":\"通用版\",\"item_key\":\"0\"},{\"item_value\":\"速食版\",\"item_key\":\"1\"}]）")
	private int levelstyle;
    
	@ApiModelProperty("登陆属性，0 是电脑端，1是手机端")
	private int loginstyle;
    
	@ApiModelProperty("菜单名字")
	private String menus_name;
    
	@ApiModelProperty("菜单排序")
	private int menus_order;
    
	@ApiModelProperty("")
	private int menus_pid;
    
	@ApiModelProperty("模块属性  ，取自字典1180（酒店为9）")
	private int modelstyle;
    
	@ApiModelProperty("备注")
	private String remarks;
    
	@ApiModelProperty("菜单URL链接")
	private String url;
	
	@ApiModelProperty("请求方式")
	private String requestmode;

}
