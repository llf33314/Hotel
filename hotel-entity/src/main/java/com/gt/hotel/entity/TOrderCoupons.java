package com.gt.hotel.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单与优惠券 1 : N 
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
@Data
@Accessors(chain = true)
public class TOrderCoupons extends Model<TOrderCoupons> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer orderId;
	private String orderNum;
    /**
     * 优惠券ID
     */
	private Integer couponsId;
    /**
     * 优惠券数量
     */
	private Integer couponsNum;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
