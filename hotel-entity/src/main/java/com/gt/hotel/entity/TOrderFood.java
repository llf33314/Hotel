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
 * 订餐订单 独立订单
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
@Data
@Accessors(chain = true)
public class TOrderFood extends Model<TOrderFood> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 总订单id
     */
	private Integer orderId;
    /**
     * 订单编号
     */
	private String orderNum;
	private Integer hotelId;
    /**
     * 酒店名称
     */
	private String hotelName;
	private String roomNum;
	private Integer categoryId;
    /**
     * 房型名称
     */
	private String categoryName;
    /**
     * 客户姓名
     */
	private String customerName;
    /**
     * 客户联系电话
     */
	private String customerPhone;
    /**
     * 订单状态 =0 处理中 =1 已确认 =2 已取消 =3 已完成 默认0
     */
	private Integer orderStatus;
    /**
     * 支付状态：=0 待支付 =1 已支付 =2 已退款
     */
	private Integer payStatus;
    /**
     * 退款到账时间
     */
	private Date refundTime;
    /**
     * 支付时间
     */
	private Date payTime;
    /**
     * 创建订单时间
     */
	private Date createTime;
    /**
     * 订单完成时间( 确认订单已完成 )
     */
	private Date completionTime;
    /**
     * 订餐订单总价
     */
	private Integer foodTotalPrice;
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
	 * 发票抬头
	 */
	private String invoiceHead;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
