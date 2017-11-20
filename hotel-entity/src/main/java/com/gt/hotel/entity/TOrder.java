package com.gt.hotel.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 算客户实际 预约(到店办理)->入住->消费(酒店内消费)->退房->结算 的所有消费(房费+其他费)
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
@Data
@Accessors(chain = true)
public class TOrder extends Model<TOrder> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 外部订单编号 提供对外显示 ID 格式 前缀 DD+时间戳年月日时分秒毫秒 14位
     */
	private String orderNum;
    /**
     * 酒店ID
     */
	private Integer hotelId;
    /**
     * 商家ID
     */
	private Integer busId;
    /**
     * 会员ID
     */
	private Integer memberId;
    /**
     * 支付类型 =0 支付宝 =1 微信 =2 到店支付 =3 储值卡支付 =4 信用卡 =5 现金
     */
	private Integer payType;
    /**
     * 支付状态 =0 待支付 =1 已支付 =2 退款中 =3 已退款 默认 0
     */
	private Integer payStatus;
    /**
     * 支付时间
     */
	private Date payTime;
    /**
     * 订单流程状态 =0 处理中 =1 已确认 =2 已取消 =3 已完成 =4 已入住 默认0
     */
	private Integer orderStatus;
    /**
     * 创建订单时间
     */
	private Date createTime;
    /**
     * 实收金额
     */
	private Integer realPrice;
    /**
     * 账单金额(入住期间消费的金额统计)
     */
	private Integer billPrice;
    /**
     * 粉币
     */
	private Integer fb;
    /**
     * 积分 积分比例 按照商家定义
     */
	private Integer integral;
    /**
     * t_order_coupons.id 订单与优惠券的关联表
     */
	private Integer orderCouponsId;
    /**
     * 应收金额 ： 所有消费账单和 房费(优惠后的价格) 一起累加
     */
	private Integer receivablePrice;
    /**
     * 备注
     */
	private String remark;
    /**
     * 总订单完成时间 (已完成状态) 完成总订单 必须把子订单全部都完成
     */
	private Date completionTime;
    /**
     * 标记备注 0 启用 1 禁用 2 删除 默认0  可查看 sys_dictionary.dict_type_id=2
     */
	private Integer markModified;
    /**
     * 创建者ID
     */
	private Integer createdBy;
    /**
     * 创建时间
     */
	private Date createdAt;
    /**
     * 最后修改人 ID
     */
	private Integer updatedBy;
    /**
     * 最后修改时间
     */
	private Date updatedAt;
	/**
	 * 粉币折扣金额
	 */
	private Integer fbDiscount;
	/**
	 * 积分折扣金额
	 */
	private Integer integralDiscount;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
