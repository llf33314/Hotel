package com.gt.hotel.param;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 酒店移动端 餐饮 请求对象
 * @author Reverien9@gmail.com
 * 2017年11月3日 下午3:54:04
 */
@Api(description = "酒店移动端 餐饮 请求对象")
public class FoodMobileParameter {
	
	@Data
	@Api( "餐饮 移动端 查询 参数" )
	public static class FoodMobileQuery extends HotelPage{
		@ApiModelProperty(value = "餐饮ID")
		private Integer id;
		
		@ApiModelProperty(value = "提供方名称")
		private String foodProvidesName;
	}
	
}
