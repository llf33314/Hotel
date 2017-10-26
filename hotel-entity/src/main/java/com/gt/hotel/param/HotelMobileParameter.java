package com.gt.hotel.param;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 酒店移动端 请求对象
 *
 * @author ReverieNight@Foxmail.com
 */
@Api(description = "酒店移动端 请求对象")
public class HotelMobileParameter {
	
	/**
	 * 保存 实体参数
	 */
	@Data
	@Api( "保存 实体参数" )
	public static class MobileSaveOrUpdate {
		@ApiModelProperty(value = "酒店ID", required = true)
		@NotNull(message = "酒店ID不能为空")
	    private Integer hotelId;
	    
	    @ApiModelProperty(value = "支付方式(1：在线支付 | 2：到店支付 | 3：1&2)  默认 在线支付", required = true)
	    @NotNull(message = "支付方式不能为空")
	    private Integer payMode;
	    
	    @ApiModelProperty(value = "是否开启短信通知 默认 1 不开启 0 开启", required = true)
	    @NotNull(message = "短信通知设置不能为空")
	    private Integer smsEnable;
	    
	    @ApiModelProperty(value = "接收短信的手机号")
	    private String  smsPhone;
	    
	    @ApiModelProperty(value = "酒店公告 1 不开启 0 开启", required = true)
	    @NotNull(message = "酒店公告设置不能为空")
	    private Integer noticeEnable;
	    
	    @ApiModelProperty(value = "公告")
	    private String  bulletin;
	    
	    @ApiModelProperty(value = "是否显示剩余房间数 默认 1 关闭 0 开启", required = true)
	    private Integer remnantRoomEnable;
	    
	    @ApiModelProperty("开启客房订餐 0 开启  1 不开启 默认1")
		private Integer roomReservationEnable;
	    
	    @ApiModelProperty(value = "餐饮支付方式 1 在线 2 到付 3 在线&到付", required = true)
	    @NotNull(message = "餐饮支付方式不能为空")
	    private Integer foodPayMode;
	    
	    @ApiModelProperty(value = "预约退房 0 开启 1 关闭  默认1", required = true)
	    private Integer reservationCheckOutEnable;
	    
	    @ApiModelProperty(value = "预约退房 接收短信的手机号")
	    private String  reservationCheckOutPhone;
	    
	    @ApiModelProperty(value = "发票支持的类目  存储方式 1,2,3,4,5 or 1,2,3")
	    private String  InvoiceCategory;
	    
	    @ApiModelProperty(value =  "图片地址 集合" )
	    private List<String> imageurls;
	    
	    @ApiModelProperty( value = "设施ID 集合" )
	    private List<InfrastructureRelationParamter> installations;
	}
	
	@Data
	@Api( "餐饮 保存 实体参数" )
	public static class FoodSaveOrUpdate {
		@ApiModelProperty(value = "餐饮ID")
		private Integer id;
		
		@ApiModelProperty(value = "酒店ID", required = true)
		@NotNull(message = "酒店ID不能为空")
		private Integer hotelId;

		@ApiModelProperty(value = "菜品名称", required = true)
		@NotEmpty(message = "菜品名称不能为空")
		private String name;

		@ApiModelProperty(value = "单价", required = true)
		@NotNull(message = "单价不能为空")
		private Integer price;

		@ApiModelProperty(value = "配送时间 单位分钟", required = true)
		@NotNull(message = "配送时间不能为空")
		private Integer deliveryTime;

		@ApiModelProperty(value = "菜品提供方 0 本酒店 1 合作方")
		private Integer foodProvides;

		@ApiModelProperty(value = "提供方名称 food_provides =0 为酒店名称 =1 则填写合作方名称")
		private String foodProvidesName;

		@ApiModelProperty(value = "新订单接收(短信)手机号", required = true)
		@NotNull(message = "新订单接收(短信)手机号 不能为空")
		private String newOrderReceive;

		@ApiModelProperty(value = "菜品图片URL")
		private String foodImage;

		@ApiModelProperty(value = "早餐时段是否提供 0 提供 1 不提供")
		private Integer breakfastEnable;

		@ApiModelProperty(value = "早餐开始时间 (示例: 6:00-10:00)")
		@JsonFormat(pattern = "HH:mm")
		private Date breakfastBegin;

		@ApiModelProperty(value = "早餐结束时间 (示例: 6:00-10:00)")
		@JsonFormat(pattern = "HH:mm")
		private Date breakfastEnd;

		@ApiModelProperty(value = "午餐时段是否提供 0 提供 1 不提供")
		private Integer lunchEnable;

		@ApiModelProperty(value = "午餐开始时间 (示例: 6:00-10:00)")
		@JsonFormat(pattern = "HH:mm")
		private Date lunchBegin;

		@ApiModelProperty(value = "午餐结束时间 (示例: 6:00-10:00)")
		@JsonFormat(pattern = "HH:mm")
		private Date lunchEnd;

		@ApiModelProperty(value = "晚餐是否提供 0 是 1 否")
		private Integer dinnerEnable;

		@ApiModelProperty(value = "晚餐开始时间")
		@JsonFormat(pattern = "HH:mm")
		private Date dinnerBegin;

		@ApiModelProperty(value = "晚餐结束时间")
		@JsonFormat(pattern = "HH:mm")
		private Date dinnerEnd;

		@ApiModelProperty(value = "宵夜是否提供 0 是 1 否")
		private Integer supperEnable;

		@ApiModelProperty(value = "宵夜开始时间")
		@JsonFormat(pattern = "HH:mm")
		private Date supperBegin;

		@ApiModelProperty(value = "宵夜结束时间")
		@JsonFormat(pattern = "HH:mm")
		private Date supperEnd;
	}
	
}
