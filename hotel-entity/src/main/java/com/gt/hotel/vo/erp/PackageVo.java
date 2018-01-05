package com.gt.hotel.vo.erp;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 套餐(包含中介/单位)
 * @author Reverien9@gmail.com
 * 2017年11月24日 下午5:57:02
 */
@Data
@Api("套餐(包含中介/单位)")
public class PackageVo {

	private Integer id;

	@ApiModelProperty("套餐名称")
	private String name;

	@ApiModelProperty("酒店ID")
	private Integer hotelId;

	@ApiModelProperty("门店ID")
	private Integer shopId;

	@ApiModelProperty("备注")
	private String remark;

	@ApiModelProperty("套餐针对的单位类型：0 协议单位  1 中介")
	private Integer module;

	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createdAt;
}
