
package com.gt.hotel.requestEntity;

import com.gt.hotel.util.RequestBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 新增or更新酒店请求对象
 *
 * @author ReverieNight@Foxmail.com
 */

@Api( description = "酒店实体参数" )
public class HotelParameter {
	/**
	 * 新增或更新实体参数
	 */
	@Data
	@Api( "保存或更新实体" )
	public static class SaveOrUpdate {
		@ApiModelProperty( "门店ID" + RequestBeanUtil.OPTIONAL )
		private Integer hotelId;
		
		@ApiModelProperty( "门店ID" )
		@NotNull( message = "门店ID不能为空" )
		private Integer shopid;
		
		@ApiModelProperty( "酒店名称" )
		@NotNull( message = "酒店名称不能为空" )
		@Length( max = 60, message = "酒店名称长度过长" )
		private String name;
		
		@ApiModelProperty( "酒店电话" )
		@NotNull( message = "酒店电话不能为空" )
		@Length( max = 12, message = "酒店电话长度过长" )
		private String tel;
		
		@ApiModelProperty( "酒店地址" )
		@NotNull( message = "酒店地址不能为空" )
		@Length( max = 200, message = "酒店地址长度过长" )
		private String addr;
		
		@ApiModelProperty( "经度" )
		@NotNull( message = "经度不能为空" )
		private Double longitude;
		
		@ApiModelProperty( "纬度" )
		@NotNull( message = "纬度不能为空" )
		private Double latitude;
		
		@ApiModelProperty( "酒店介绍" )
		@NotNull( message = "酒店介绍不能为空" )
		@Length( max = 200, message = "酒店介绍长度过长" )
		private String desc;
		
	}
	
}