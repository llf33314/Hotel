package com.gt.hotel.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 订单与优惠券 1 : N
 * </p>
 *
 * @author zhangmz
 * @since 2017-10-26
 */
@Data
@Accessors(chain = true)
public class TOrderCoupons extends Model<TOrderCoupons> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer orderId;
    private String  orderNum;
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
