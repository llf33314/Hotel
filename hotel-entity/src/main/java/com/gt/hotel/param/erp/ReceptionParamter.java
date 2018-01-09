package com.gt.hotel.param.erp;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 直接入住 对象
 * @author Reverien9@gmail.com
 * 2018年1月9日 上午9:42:58
 */
@Api(description = "直接入住 对象")
public class ReceptionParamter {
	
	
	@Api("直接入住 编辑 对象")
	@Data
	public static class ImmediateCheckInParam {
		@ApiModelProperty(value = "酒店ID", required = true)
		private Integer hotelId;
		
		@ApiModelProperty(value = "酒店名称", required = true)
		@NotEmpty(message = "酒店名称不能为空")
		private String hotelName;
		
		@ApiModelProperty(value = "会员ID")
		private String memberId;
		
		@ApiModelProperty(value = "客户姓名", required = true)
		@NotEmpty(message = "客户姓名不能为空")
		private String customerName;
		
		@ApiModelProperty(value = "手机号码", required = true)
		@NotEmpty(message = "手机号码不能为空")
		private String customerPhone;

		@ApiModelProperty(value = "房型ID")
		private Integer categoryId;

		@ApiModelProperty(value = "房型名称")
		private String categoryName;
		
		@ApiModelProperty(value = "预留时间")
//		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private String reserveTime;

		@ApiModelProperty(value = "证件类型 =0 二代身份证 =1 一代身份证 =2 驾驶证 =3 护照 =4 军官证 =5 士兵证 =6 港澳通行证 =7 其他", required = true)
		@NotNull(message = "证件类型不能为空")
		private Integer customerIdType;
		
		@ApiModelProperty(value = "证件号", required = true)
		@NotEmpty(message = "证件号不能为空")
		private String customerIdCard;
		
		@ApiModelProperty(value = "性别(0:男, 1:女)", required = true)
		@NotNull(message = "性别不能为空")
		private Integer customerGender;
		
		@ApiModelProperty(value = "入住时间", required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date roomInTime;
		
		@ApiModelProperty(value = "离店时间", required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date roomOutTime;
		
		@ApiModelProperty(value = "住客类型(0:散客/会员, 1:协议单位)", required = true)
		@NotNull(message = "住客类型不能为空")
		private Integer guestType;
		
		@ApiModelProperty(value = "入住标准 0 全天房 1 特价房 2 钟点房 3 秒杀房 4 团购房", required = true)
		@NotNull(message = "入住标准 不能为空")
		private Integer checkStandard;

		@ApiModelProperty(value = "协议单位ID")
		private Integer organizationId;
		
		@ApiModelProperty(value = "客人集合")
		private List<ImmediateCheckInCustomer> customers;
		
		@ApiModelProperty(value = "房间集合")
		private List<ImmediateCheckInRoom> rooms;
		
		@ApiModelProperty(value = "备注")
		private String remark;
		
		@ApiModelProperty(value = "押金  0 则为免押金")
		private Integer deposit;
		
		@ApiModelProperty(value = "支付类型 =0 支付宝 =1 微信 =2 到店支付 =3 会员卡支付 =4 信用卡 =5 现金 =6 挂账", required = true)
		@NotNull(message = "支付类型不能为空")
		private Integer payType;
		
		@ApiModelProperty(value = "订单流程状态 =3 已完成 =4 已入住 默认 0", hidden = true)
		private Integer orderStatus = 4;
		
		@ApiModelProperty(value = "实收金额", required = true)
		@NotNull(message = "实收金额不能为空")
		private Integer realPrice;
		
		@ApiModelProperty(value = "当前门市价 固定从房型获取(周一至周四为 门市价 周五周六 为周末价。如果有日历价，则为日历价)", required = true)
		@NotNull(message = "当前门市价不能为空")
		private Integer rackRate;
		
		@ApiModelProperty(value = "应收( (客房价格+押金) * 数量 room_price+deposit ) ", required = true)
		@NotNull(message = "应收价不能为空")
		private Integer receivablePrice;
		
		@ApiModelProperty(value = "当前客房价格 可以是改价后的价格", required = true)
		@NotNull(message = "当前客房价格不能为空")
		private Integer roomPrice;

		@ApiModelProperty(value = "粉币")
        private Integer fb;

        @ApiModelProperty(value = "积分")
        private Integer integral;
        
        @ApiModelProperty(value = "支付价格")
        private Integer payPrice;

        @ApiModelProperty("显示价格")
        private Integer displayPrice;

        @ApiModelProperty(value = "优惠券Code")
        private String couponsCode;

        @ApiModelProperty(value = "优惠券数量")
        private Integer couponsNum;
        
        @ApiModelProperty(value = "粉币折扣金额")
        private Integer fbDiscount;

        @ApiModelProperty(value = "积分折扣金额")
        private Integer integralDiscount;

        @ApiModelProperty(value = "优惠券折扣金额")
        private Integer couponsDiscount;

        @ApiModelProperty(value = "钟点房入住时间 HH:mm:ss")
        private String hourRoomCheckInTime;
	}
	
	@Api("直接入住 客人参数")
	@Data
	public static class ImmediateCheckInCustomer {
		@ApiModelProperty(value = "客户姓名")
		private String customerName;

		@ApiModelProperty(value = "手机号码")
		private String customerPhone;
		
		@ApiModelProperty(value = "证件类型")
		private Integer customerIdType;
		
		@ApiModelProperty(value = "证件号")
		private String customerIdCard;
		
		@ApiModelProperty(value = "性别(0:男, 1:女)")
		private Integer customerGender;
	}
	
	@Api("直接入住 房间参数")
	@Data
	public static class ImmediateCheckInRoom {
		@ApiModelProperty(value = "房型ID")
		private Integer categoryId;

		@ApiModelProperty(value = "房型名")
		private String categoryName;
		
		@ApiModelProperty(value = "房间ID")
		private Integer roomId;
		
		@ApiModelProperty(value = "房间号")
		private String roomNum;
	}



}
