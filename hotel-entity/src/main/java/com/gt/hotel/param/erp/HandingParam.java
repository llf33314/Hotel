package com.gt.hotel.param.erp;

import com.gt.hotel.param.HotelPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 挂账 请求 对象
 * @author Reverien9@gmail.com
 * 2018年1月8日 上午11:39:01
 */
@Api(description = "挂账 请求 对象")
@Data
public class HandingParam {
    
	@Api("查询 参数")
	@Data
	public static class HandingQuery extends HotelPage{

		@ApiModelProperty("时间段 开始")
		private String inTime;
		
		@ApiModelProperty("时间段 结束")
		private String outTime;
		
	}
    
}