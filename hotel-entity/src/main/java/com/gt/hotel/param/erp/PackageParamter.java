package com.gt.hotel.param.erp;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.gt.hotel.param.HotelPage;
import com.gt.hotel.vo.erp.PackageRoomVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 套餐(包含中介/单位)
 * @author Reverien9@gmail.com
 * 2017年11月24日 下午5:57:02
 */
@Data
@Api("套餐 参数")
public class PackageParamter {

	@Api("套餐 查询 参数")
	@Data
	public static class PackageQuery extends HotelPage{
		private Integer id;

		@ApiModelProperty(value = "门店ID", required = true)
		@NotNull(message = "门店ID不能为空")
		private Integer shopId;
		
		@ApiModelProperty(value = "套餐针对的单位类型：0 协议单位  1 中介", required = true)
		@NotNull(message = "单位类型不能为空")
		private Integer module;
	}

	@Api("套餐 更新 参数")
	@Data
	public static class PackageCU{
		private Integer id;
		
		@ApiModelProperty(value = "套餐名", required = true)
		@NotEmpty(message = "套餐名不能为空")
		private String name;

		@ApiModelProperty(value = "酒店ID", required = true)
		@NotNull(message = "酒店ID不能为空")
		private Integer hotelId;
		
		@ApiModelProperty(value = "门店ID", required = true)
		@NotNull(message = "门店ID不能为空")
		private Integer shopId;
		
		@ApiModelProperty(value = "备注")
		private String remark;
		
		@ApiModelProperty(value = "套餐针对的单位类型：0 协议单位  1 中介", required = true)
		@NotNull(message = "单位类型不能为空")
		private Integer module;

		@ApiModelProperty(value = "房型列表")
		private List<PackageRoomVo> packRooms;
	}
	
	
}
