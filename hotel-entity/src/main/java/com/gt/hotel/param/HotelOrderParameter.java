package com.gt.hotel.param;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author Reverien9@gmail.com
 * 2017年10月30日 上午9:59:06
 */
@Api(description = "订单请求对象")
public class HotelOrderParameter {
	
	@Api("房间订单 查询参数")
	@Data
	public static class RoomOrderQuery extends HotelPage {
		@ApiModelProperty(value = "订单ID")
		private Integer id;

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

		@ApiModelProperty(value = "支付状态 =0 待支付 =1 已支付 =2 退款中 =3 已退款 ")
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
		@ApiModelProperty(value = "房型ID")
		private Integer categoryId;

		@ApiModelProperty(value = "房型名称")
		private String categoryName;
		
		@ApiModelProperty(value = "客户姓名", required = true)
		private String customerName;

		@ApiModelProperty(value = "手机号码", required = true)
		private String customerPhone;

		@ApiModelProperty(value = "证件类型", required = true)
		private Integer customerIdType;
		
		@ApiModelProperty(value = "证件号", required = true)
		private String customerIdCard;
		
		@ApiModelProperty(value = "酒店ID", required = true)
		private Integer hotelId;
		
		@ApiModelProperty(value = "酒店名称", required = true)
		private String hotelName;
		
		@ApiModelProperty(value = "支付类(方)型(式)", required = true)
		private Integer payType;
		
		@ApiModelProperty(value = "订单状态", required = true)
		private Integer orderStatus;
		
		@ApiModelProperty(value = "入住时间", required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date roomInTime;

		@ApiModelProperty(value = "离店时间", required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date roomOutTime;
		
		@ApiModelProperty(value = "住客类型(0:散客/会员, 1:协议单位)", required = true)
		private Integer guestType;

		@ApiModelProperty(value = "性别(0:男, 1:女)", required = true)
		private Integer customerGender;

		@ApiModelProperty(value = "实收金额", required = true)
		private Integer realPrice;
		
		@ApiModelProperty(value = "当前门市价 固定从房型获取(周一至周四为 门市价 周五周六 为周末价。如果有日历价，则为日历价)", required = true)
		private Integer rackRate;

		@ApiModelProperty(value = "应收( (客房价格+押金) * 数量 room_price+deposit ) ", required = true)
		private Integer receivablePrice;

		@ApiModelProperty(value = "押金  0 则为免押金", required = true)
		private Integer deposit;

		@ApiModelProperty(value = "当前客房价格 可以是改价后的价格", required = true)
		private Integer roomPrice;
		
		@ApiModelProperty(value = "房间集合")
		private List<HotelOrderRoomParameter.OrderRoom> rooms;
	}
	
	@Api("房间订单入住  参数")
	@Data
	public static class CheckInParam {
		@ApiModelProperty(value = "客户姓名", required = true)
		private String customerName;

		@ApiModelProperty(value = "手机号码", required = true)
		private String customerPhone;
		
		@ApiModelProperty(value = "证件类型", required = true)
		private Integer customerIdType;
		
		@ApiModelProperty(value = "证件号", required = true)
		private String customerIdCard;
		
		@ApiModelProperty(value = "性别(0:男, 1:女)", required = true)
		private Integer customerGender;
		
		@ApiModelProperty(value = "房间集合")
		private List<HotelOrderRoomParameter.OrderRoom> rooms;
	}
}
