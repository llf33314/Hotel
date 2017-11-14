package com.gt.hotel.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api("移动端 房间订单 详情")
public class MobileRoomOrderVo {
	
	@ApiModelProperty(value = "订单ID")
	private Integer orderId;
	
	@ApiModelProperty(value = "客户姓名")
	private String customerName;
	
	@ApiModelProperty(value = "客户联系电话")
	private String customerPhone;
	
	@ApiModelProperty(value = "入住时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date roomInTime;

	@ApiModelProperty(value = "离店时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date roomOutTime;
	
	@ApiModelProperty(value = "房型名称")
	private Integer categoryName;
	
	@ApiModelProperty(value = "客房数量")
	private Integer number;
	
	@ApiModelProperty(value = "支付类型 =0 支付宝 =1 微信 =2 到店支付 =3 储值卡支付 =4 信用卡 =5 现金")
	private Integer payType;
	
	@ApiModelProperty(value = "备注")
	private String remark;
	
	@ApiModelProperty("酒店ID")
    private Integer hotelId;
	
	@ApiModelProperty("酒店名称")
    private String name;

    @ApiModelProperty("酒店联系电话")
    private String phone;
    
    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("经度")
    private Double longitude;

    @ApiModelProperty("纬度")
    private Double latitude;
    
    @ApiModelProperty(value = "粉币")
	private Integer fb;
	
	@ApiModelProperty(value = "积分")
	private Integer	integral;
	
	@ApiModelProperty(value = "粉币折扣金额")
	private Integer fbDiscount;
	
	@ApiModelProperty(value = "积分折扣金额")
	private Integer integralDiscount;
	
	@ApiModelProperty(value = "优惠券折扣金额")
	private Integer couponsDiscount;
	
	@ApiModelProperty(value = "优惠券ID")
	private Integer couponsId;
	
	@ApiModelProperty(value = "优惠券数量")
	private Integer couponsNum;
	
	@ApiModelProperty(value = "押金")
	private Integer deposit;
	
	@ApiModelProperty(value = "总金额（实收金额）")
	private Integer realPrice;
}
