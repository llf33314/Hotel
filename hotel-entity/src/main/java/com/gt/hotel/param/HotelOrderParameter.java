package com.gt.hotel.param;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单请求对象
 *
 * @author ReverieNight@Foxmail.com
 */
@Api(description = "订单请求对象")
public class HotelOrderParameter {
	
	@Api("查询参数")
	@Data
	public static class OrderQuery extends HotelPage {
		@ApiModelProperty(value = "酒店ID")
		private Integer hotelId;

		@ApiModelProperty(value = "支付状态 0 已支付 1 未支付")
		private Integer payStatus;
		
		@ApiModelProperty(value = "订单流程状态 =0 处理中 =1 已确认 =2 已取消 =3 已完成 默认0")
		private Integer orderStatus;
		
		@ApiModelProperty(value = "入住时间")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date roomInTime;

		@ApiModelProperty(value = "离店时间")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date roomOutTime;
	}

}
