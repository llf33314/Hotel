package com.gt.hotel.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(description = "酒店后台-订单管理-餐饮订单列表对象")
public class HotelBackFoodOrderVo {
    @ApiModelProperty("订单ID")
    private Integer id;

    @ApiModelProperty("外部订单编号")
    private String orderNum;
    
    @ApiModelProperty("房号 来自 t_room.number 预约订单则不会自动锁定房号")
    private String roomNum;

    @ApiModelProperty("酒店ID")
    private Integer hotelId;

    @ApiModelProperty("商家ID")
    private Integer busId;

    @ApiModelProperty("会员ID")
    private Integer memberId;
    
    @ApiModelProperty("支付类型 =0 支付宝 =1 微信 =2 到店支付 =3 储值卡支付 =4 信用卡 =5 现金")
	private Integer payType;

    @ApiModelProperty("支付状态 =0 待支付 =1 已支付 =2 退款中 =3 已退款 默认 0")
    private Integer payStatus;

    @ApiModelProperty("支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date payTime;
    
    @ApiModelProperty("订单流程状态 =0 处理中 =1 已确认 =2 已取消 =3 已完成 =4 已入住 默认0")
	private Integer orderStatus;

    @ApiModelProperty("创建订单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

    @ApiModelProperty("实收金额")
	private Integer realPrice;

    @ApiModelProperty("账单金额(入住期间消费的金额统计)")
    private Integer billPrice;

    @ApiModelProperty("粉币")
    private Integer fb;

    @ApiModelProperty("积分 积分比例 按照商家定义")
    private Integer integral;

    @ApiModelProperty("t_order_coupons.id 订单与优惠券的关联表")
    private Integer orderCouponsId;

    @ApiModelProperty("应收金额 ")
    private Integer receivablePrice;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("总订单完成时间 (已完成状态) 完成总订单 必须把子订单全部都完成")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date completionTime;
    
    @ApiModelProperty("酒店名称")
    private String hotelName;

    @ApiModelProperty("客户姓名")
	private String customerName;
   
    @ApiModelProperty("客户联系电话")
	private String customerPhone;
    
    @ApiModelProperty("菜品提供方")
    private String foodProvidesName;
}