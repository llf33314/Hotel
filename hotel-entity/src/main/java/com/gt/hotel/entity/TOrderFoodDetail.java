package com.gt.hotel.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 订餐订单详情
 * </p>
 *
 * @author zhangmz
 * @since 2017-10-26
 */
@Data
@Accessors(chain = true)
public class TOrderFoodDetail extends Model<TOrderFoodDetail> {

    private static final long serialVersionUID = 1L;

    private Integer orderFoodId;
    private Integer foodId;
    /**
     * 菜名
     */
    private String  foodName;
    /**
     * 单价
     */
    private Integer foodPrice;
    /**
     * 菜品提供方
     */
    private String  foodProvidesName;
    /**
     * 菜品提供方电话
     */
    private String  foodProvidesPhone;
    /**
     * 数量
     */
    private Integer foodNumber;


    @Override
    protected Serializable pkVal() {
        return this.orderFoodId;
    }

}
