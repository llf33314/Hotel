package com.gt.hotel.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订餐订单详情
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
@Data
@Accessors(chain = true)
public class TOrderFoodDetail extends Model<TOrderFoodDetail> {

    private static final long serialVersionUID = 1L;

    private Integer id;
	private Integer orderFoodId;
	private Integer foodId;
    /**
     * 菜名
     */
	private String foodName;
    /**
     * 单价
     */
	private Integer foodPrice;
    /**
     * 菜品提供方
     */
	private String foodProvidesName;
    /**
     * 菜品提供方电话
     */
	private String foodProvidesPhone;
    /**
     * 数量
     */
	private Integer foodNumber;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
