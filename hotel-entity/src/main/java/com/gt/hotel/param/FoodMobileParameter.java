package com.gt.hotel.param;

import java.util.List;

import com.gt.hotel.vo.OrderFoodDetailVo;

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
		
		@ApiModelProperty(value = "早餐时段是否提供 0 提供 1 不提供")
	    private Integer breakfastEnable;

	    @ApiModelProperty(value = "午餐时段是否提供 0 提供 1 不提供")
	    private Integer lunchEnable;

	    @ApiModelProperty(value = "晚餐是否提供 0 是 1 否")
	    private Integer dinnerEnable;

	    @ApiModelProperty(value = "宵夜是否提供 0 是 1 否")
	    private Integer supperEnable;
	}
	
	@Data
	@Api( "餐饮 移动端 去结算(购物车) 订单 参数" )
	public static class FoodMobileOrder{
		
		@ApiModelProperty(value = "酒店ID", required = true)
		private Integer hotelId;
		
		@ApiModelProperty(value = "酒店名字", required = true)
		private String hotelName;

//		@ApiModelProperty(value = "支付类型 =0 支付宝 =1 微信 =2 到店支付 =3 储值卡支付 =4 信用卡 =5 现金", required = true)
//		private Integer payType;
		
		@ApiModelProperty(value = "实收金额", required = true)
		private Integer realPrice;
		
		@ApiModelProperty(value = "账单金额", required = true)
		private Integer billPrice;
		
		@ApiModelProperty(value = "应收金额", required = true)
		private Integer receivablePrice;
		
		@ApiModelProperty(value = "订餐订单总价", required = true)
		private Integer foodTotalPrice;
		
		@ApiModelProperty(value = "菜品集合")
		private List<OrderFoodDetailVo> foods; 
		
		@ApiModelProperty(value = "餐饮 移动端 支付 订单 参数")
		private FoodMobileBookOrder foodMobileBookOrder;
	}
	
	@Data
	@Api( "餐饮 移动端 支付 订单 参数" )
	public static class FoodMobileBookOrder{
		
		@ApiModelProperty(value = "订单ID", required = true)
		private Integer orderId;
		
		@ApiModelProperty(value = "酒店ID", required = true)
		private Integer hotelId;
		
		@ApiModelProperty(value = "客户姓名", required = true)
		private String customerName;
		
		@ApiModelProperty(value = "客户联系电话", required = true)
		private String customerPhone;
		
		@ApiModelProperty(value = "支付类型 =0 支付宝 =1 微信 =2 到店支付 =3 储值卡支付 =4 信用卡 =5 现金", required = true)
		private Integer payType;
		
		@ApiModelProperty(value = "备注")
		private String remark;
		
		@ApiModelProperty(value = "房间号", required = true)
		private String roomNum;
		
		@ApiModelProperty(value = "发票抬头")
		private String invoiceHead;
	}
}
