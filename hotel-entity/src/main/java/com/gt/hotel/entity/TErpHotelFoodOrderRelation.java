package com.gt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.enums.FieldFill;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * ERP酒店-餐饮-订单-列表
 * </p>
 *
 * @author 
 * @since 2017-08-17
 */
@ApiModel("TErpHotelFoodOrderRelation")
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_food_order_relation")
public class TErpHotelFoodOrderRelation extends Model<TErpHotelFoodOrderRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @ApiModelProperty("订单ID")
    @TableId("order_id")
	private Integer orderId;
    /**
     * 酒店菜品ID
     */
    @ApiModelProperty("酒店菜品ID")
	@TableField("food_id")
	private Integer foodId;


	@Override
	protected Serializable pkVal() {
		return this.orderId;
	}

	@Override
	public String toString() {
		return "TErpHotelFoodOrderRelation{" +
			"orderId=" + orderId +
			", foodId=" + foodId +
			"}";
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
}
