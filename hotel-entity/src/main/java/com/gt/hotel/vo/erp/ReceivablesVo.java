package com.gt.hotel.vo.erp;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 针对协议组织(单位/中介) 收款对象
 * @author Reverien9@gmail.com
 * 2018年1月5日 上午10:35:09
 */
@Api("针对协议组织(单位/中介) 收款对象")
@Data
public class ReceivablesVo {

	@ApiModelProperty("订单ID")
	private Integer id;

	@ApiModelProperty("订单号")
	private String orderNum;
	
	@ApiModelProperty("客人名称")
	private String customerName;

	@ApiModelProperty("手机号码")
	private String customerPhone;

	@ApiModelProperty("入住天数")
	private String daysStay;

	@ApiModelProperty("单位名称")
	private String name;

	@ApiModelProperty("合同号")
	private String contractNum;

	@ApiModelProperty("订单总额(分)/实收金额")
	private String realPrice;
	
	@ApiModelProperty("操作员ID")
	private Integer operationId;

	@ApiModelProperty("操作员名称")
	private String operationName;

    @ApiModelProperty("酒店ID")
    private Integer hotelId;

    @ApiModelProperty("商家ID")
    private Integer busId;

    @ApiModelProperty("会员ID")
    private Integer memberId;
    
    @ApiModelProperty("支付类型 =0 支付宝 =1 微信 =2 到店支付 =3 储值卡支付 =4 信用卡 =5 现金")
	private Integer payType;

    @ApiModelProperty("订单流程状态 =0 处理中 =1 已确认 =2 已取消 =3 已完成 =4 已入住 默认0")
	private Integer orderStatus;

    @ApiModelProperty("创建订单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

    @ApiModelProperty("账单金额(入住期间消费的金额统计)")
    private Integer billPrice;

    @ApiModelProperty("t_order_coupons.id 订单与优惠券的关联表")
    private Integer orderCouponsId;

    @ApiModelProperty("应收金额 ")
    private Integer receivablePrice;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("证件类型 =0 二代身份证 =1 一代身份证 =2 驾驶证 =3 护照 =4 军官证 =5 士兵证 =6 港澳通行证 =7 其他")
    private Integer customerIdType;

    @ApiModelProperty("证件号")
    private String customerIdCard;

    @ApiModelProperty("证件照 图片地址")
    private String customerIdCardImage;

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

    @ApiModelProperty("来源 1 后台(线下订单) 2 ERP 3 移动端(H5) 4 小程序  5 客户经理 6 中介，默认 0 未知来源 ")
    private Integer orderFrom;
    
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
    
    @ApiModelProperty("客人s")
    private List<ReceivablesCustomersVo> customers;
    
    @ApiModelProperty("房间信息s")
    private List<ReceivablesRoomVo> rooms;
    
    @ApiModelProperty("赔偿损坏")
    private Integer compensation;
    
    @ApiModelProperty("商品消费")
    private Integer consumption;
    
}
