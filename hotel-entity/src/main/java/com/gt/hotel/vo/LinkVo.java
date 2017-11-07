package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author Reverien9@gmail.com
 * 2017年11月7日 上午10:31:08
 */
@Api("酒店后台 链接数据")
@Data
public class LinkVo {
	
	@ApiModelProperty("长路径")
	private String longUrl;
	
	@ApiModelProperty("短路径")
	private String shortUrl;
	
}
