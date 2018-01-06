package com.gt.hotel.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(description = "酒店后台-订单管理-房间订单列表对象")
public class HotelBackRoomOrderVo {
    @ApiModelProperty("订单ID")
    private Integer id;

    @ApiModelProperty("外部订单编号")
    private String orderNum;

    @ApiModelProperty("酒店ID")
    private Integer hotelId;

    @ApiModelProperty("商家ID")
    private Integer busId;

    @ApiModelProperty("会员ID")
    private Integer memberId;
    
    @ApiModelProperty("支付类型 =0 支付宝 =1 微信 =2 到店支付 =3 储值卡支付 =4 信用卡 =5 现金 =6 挂账")
	private Integer payType;

    @ApiModelProperty("支付状态 ")
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
    
    @ApiModelProperty("冗余参数 酒店名称")
    private String hotelName;

    @ApiModelProperty("房型ID")
    private Integer categoryId;

    @ApiModelProperty("冗余参数 房型名称")
    private String categoryName;

    @ApiModelProperty("入住方式 0 预订订单(预约订单: 网上|电话|移动端 预订过产生了一条预订消息则为预订订单)  1 到店入住(直接办理入住)")
    private Integer checkInWay;

    @ApiModelProperty("房号 来自 t_room.number 预约订单则不会自动锁定房号")
    private String roomNum;

    @ApiModelProperty("预订客户姓名")
    private String customerName;

    @ApiModelProperty("证件类型 =0 二代身份证 =1 一代身份证 =2 驾驶证 =3 护照 =4 军官证 =5 士兵证 =6 港澳通行证 =7 其他")
    private Integer customerIdType;

    @ApiModelProperty("证件号")
    private String customerIdCard;

    @ApiModelProperty("证件照 图片地址")
    private String customerIdCardImage;

    @ApiModelProperty("预订客户 手机号码")
    private String customerPhone;

    // FIXME: 2017/12/21 删除此字段
    @ApiModelProperty("客房数量 默认1 删除")
    private Integer number;

    @ApiModelProperty("客房数量 默认1")
    private Integer roomOrderNum;

    @ApiModelProperty("抵店时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date roomInTime;

    @ApiModelProperty("离店时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date roomOutTime;

    @ApiModelProperty("当前门市价 固定从房型获取(周一至周四为 门市价 周五周六 为周末价。如果有日历价，则为日历价)")
    private Integer rackRate;

    @ApiModelProperty("押金 来自 t_room_catrgory.deposit 0 则为免押金")
    private Integer deposit;
    
    @ApiModelProperty("当前客房价格 可以是改价后的价格")
    private Integer roomPrice;

    @ApiModelProperty("来源 1 后台(线下订单) 2 ERP 3 移动端(H5) 4 小程序 ，默认 0 未知来源")
    private Integer from;
    
    @ApiModelProperty("活动ID，如果是活动进来的订单即可以使用活动ID")
    private Integer activityId;
    
    @ApiModelProperty("住客类型(0:散客/会员, 1:协议单位)")
    private Integer guestType;
    
    @ApiModelProperty("性别")
    private Integer customerGender;
    
    @ApiModelProperty("入住标准 0 全天房 1 特价房 2 钟点房 3 秒杀房 4 团购房")
    private Integer checkStandard;
    
    @ApiModelProperty("粉币折扣金额")
	private Integer fbDiscount;

    @ApiModelProperty("积分折扣金额")
	private Integer integralDiscount;

    @ApiModelProperty("优惠券折扣金额")
    private Integer couponsDiscount;
    
    @ApiModelProperty("退还金额(退换的押金)")
	private Integer refundAmount;
    
    @ApiModelProperty("优惠金额")
    private Integer discountedPrice;
    
    @ApiModelProperty("房间列表")
    private List<OrderRoomCustomerVo> rooms;
}