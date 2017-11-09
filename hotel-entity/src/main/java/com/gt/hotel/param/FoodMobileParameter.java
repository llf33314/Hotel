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
		
		@ApiModelProperty("早餐时段是否提供 0 提供 1 不提供")
	    private Integer breakfastEnable;

	    @ApiModelProperty("午餐时段是否提供 0 提供 1 不提供")
	    private Integer lunchEnable;

	    @ApiModelProperty("晚餐是否提供 0 是 1 否")
	    private Integer dinnerEnable;

	    @ApiModelProperty("宵夜是否提供 0 是 1 否")
	    private Integer supperEnable;
	}
	
}
