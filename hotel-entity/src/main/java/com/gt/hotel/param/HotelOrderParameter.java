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
	
	@Api("房间订单 查询参数")
	@Data
	public static class RoomOrderQuery extends HotelPage {
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

	@Api("餐饮订单 查询参数")
	@Data
	public static class FoodOrderQuery extends HotelPage {
		@ApiModelProperty(value = "酒店ID")
		private Integer hotelId;

		@ApiModelProperty(value = "支付状态 0 已支付 1 未支付")
		private Integer payStatus;
		
		@ApiModelProperty(value = "订单流程状态 =0 处理中 =1 已确认 =2 已取消 =3 已完成 默认0")
		private Integer orderStatus;
		
		@ApiModelProperty(value = "开始时间")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date beginTime;
		
		@ApiModelProperty(value = "结束时间")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date endTime;

		@ApiModelProperty(value = "菜品提供方")
		private String foodProvidesName;
	}
	
	@Api("添加线下订单 参数")
	@Data
	public static class OffLineOrder {
		@ApiModelProperty(value = "客户姓名")
		private String customerName;

		@ApiModelProperty(value = "手机号码")
		private String customerPhone;

		@ApiModelProperty(value = "证件类型")
		private Integer customerIdType;
		
		@ApiModelProperty(value = "证件号")
		private String customerIdCard;
		
		@ApiModelProperty(value = "酒店ID")
		private Integer hotelId;
		
		@ApiModelProperty(value = "酒店名称")
		private String hotelName;
		
		@ApiModelProperty(value = "支付类型")
		private Integer payType;
		
		@ApiModelProperty(value = "订单状态")
		private Integer orderStatus;
		
		@ApiModelProperty(value = "入住时间")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date roomInTime;

		@ApiModelProperty(value = "离店时间")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date roomOutTime;
	}
}
