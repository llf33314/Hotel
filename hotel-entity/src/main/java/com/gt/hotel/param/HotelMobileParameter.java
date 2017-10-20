package com.gt.hotel.param;

import java.util.List;

import javax.validation.constraints.NotNull;

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
	public static class SaveOrUpdate {
		@ApiModelProperty("酒店ID")
		@NotNull(message = "酒店ID不能为空")
	    private Integer hotelId;
	    
	    @ApiModelProperty("支付方式(1：在线支付 | 2：到店支付 | 3：1&2)  默认 在线支付")
	    @NotNull(message = "支付方式不能为空")
	    private Integer payMode;
	    
	    @ApiModelProperty("是否开启短信通知 默认 1 不开启 0 开启")
	    @NotNull(message = "短信通知设置不能为空")
	    private Integer smsEnable;
	    
	    @ApiModelProperty("接收短信的手机号")
	    private String  smsPhone;
	    
	    @ApiModelProperty("酒店公告 1 不开启 0 开启")
	    @NotNull(message = "酒店公告设置不能为空")
	    private Integer noticeEnable;
	    
	    @ApiModelProperty("公告")
	    private String  bulletin;
	    
	    @ApiModelProperty("是否显示剩余房间数 默认 1 关闭 0 开启")
	    private Integer remnantRoomEnable;
	    
	    @ApiModelProperty("餐饮支付方式 1 在线 2 到付 3 在线&到付")
	    @NotNull(message = "餐饮支付方式不能为空")
	    private Integer foodPayMode;
	    
	    @ApiModelProperty("预约退房 0 开启 1 关闭  默认1")
	    private Integer reservationCheckOutEnable;
	    
	    @ApiModelProperty("预约退房 接收短信的手机号")
	    private String  reservationCheckOutPhone;
	    
	    @ApiModelProperty("发票支持的类目 1 办公用品 2 住宿费 3 餐费 4 培训费 5 打球费 6 健身费 存储方式 1,2,3,4,5 or 1,2,3")
	    private String  InvoiceCategory;
	    
	    @ApiModelProperty( "图片地址 集合" )
	    private List<String> imageurls;
	    
	    @ApiModelProperty( "设施ID 集合" )
	    private List<InfrastructureRelationParamter> installations;
	}
	
}
