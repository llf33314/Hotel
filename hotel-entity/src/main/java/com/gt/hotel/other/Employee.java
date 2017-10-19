/**
  * Copyright 2017 bejson.com 
  */
package com.gt.hotel.other;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Auto-generated: 2017-10-19 10:50:30
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
@Api(description = "员工")
public class Employee {

	@ApiModelProperty("部门ID")
	private int			branid;
	
	@ApiModelProperty("创建人，bus_user")
	private int			createPerson;
	
	@ApiModelProperty("创建人，bus_user")
	private String	createTime;
	
	@ApiModelProperty("邮箱")
	private String	email;
	
	@ApiModelProperty("性别(0表示男性, 1表示女性)")
	private int			gender;
	
	@ApiModelProperty("员工ID")
	private int			id;
	
	private int			isdelect;
	
	@ApiModelProperty("工号")
	private String	jobNumber;
	
	@ApiModelProperty("登录名")
	private String	loginName;
	
	@ApiModelProperty("员工姓名")
	private String	name;
	
	@ApiModelProperty("手机电话")
	private String	phone;
	
	@ApiModelProperty("职位id  取自t_common_erp_position")
	private int			positionid;
	
	@ApiModelProperty("备注")
	private String	remark;
	
	@ApiModelProperty("wx_shop 门店店铺id")
	private int			shopId;
	
	@ApiModelProperty("状态(0 正常用户, 1 冻结用户)")
	private int			status;

}