package com.gt.hotel.param;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.gt.hotel.vo.HotelMemberSettingVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 房间 请求对象
 *
 * @author ReverieNight@Foxmail.com
 */

@Api( tags = "ERP设置 实体参数" )
public class ERPParameter {
	
	@Data
	@Api( "保存 参数" )
	public static class Save {
		@ApiModelProperty( value = "酒店ID", required = true )
		@NotNull(message = "酒店ID不能为空")
		private Integer hotelId;
		
		@ApiModelProperty( value = "LOGO URL", required = true )
		@NotNull(message = "LOGO URL 不能为空")
		private String logo;

		@ApiModelProperty( "ERP 会员设置 集合" )
	    private List<HotelMemberSettingVo> memberSetting;
	}
	
}

