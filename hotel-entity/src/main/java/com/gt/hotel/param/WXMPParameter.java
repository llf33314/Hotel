package com.gt.hotel.param;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 房间 请求对象
 *
 * @author ReverieNight@Foxmail.com
 */

@Api( tags = "WXMP API 参数" )
public class WXMPParameter {
	
	@Data
	@Api( "员工 参数" )
	public static class queryEmployee extends HotelPage{
		@ApiModelProperty( value = "员工姓名" )
		private String name;
		
		@ApiModelProperty( value = "手机" )
		private String phone;

	}
	
}

