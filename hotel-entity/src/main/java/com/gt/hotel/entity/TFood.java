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
 * 菜品
 * </p>
 *
 * @author 
 * @since 2017-10-20
 */
@Data
@Accessors(chain = true)
public class TFood extends Model<TFood> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 酒店ID
     */
	private Integer hotelId;
    /**
     * 菜品名称
     */
	private String name;
    /**
     * 单价
     */
	private Integer price;
    /**
     * 配送时间 单位分钟
     */
	private Integer deliveryTime;
    /**
     * 菜品提供方 0 本酒店 1 合作方
     */
	private Integer foodProvides;
    /**
     * 提供方名称 food_provides =0 为酒店名称 =1 则填写合作方名称
     */
	private String foodProvidesName;
    /**
     * 新订单接收(短信)手机号
     */
	private Integer newOrderReceive;
    /**
     * 菜品图片URL
     */
	private String foodImage;
    /**
     * 早餐时段是否提供 0 提供 1 不提供
     */
	private Integer breakfastEnable;
    /**
     * 早餐开始时间 (示例: 6:00-10:00)
     */
	private Date breakfastBegin;
    /**
     * 早餐结束时间 (示例: 6:00-10:00)
     */
	private Date breakfastEnd;
    /**
     * 午餐时段是否提供 0 提供 1 不提供
     */
	private Integer lunchEnable;
    /**
     * 午餐开始时间 (示例: 6:00-10:00)
     */
	private Date lunchBegin;
    /**
     * 午餐结束时间 (示例: 6:00-10:00)
     */
	private Date lunchEnd;
    /**
     * 晚餐是否提供 0 是 1 否
     */
	private Integer dinnerEnable;
    /**
     * 晚餐开始时间
     */
	private Date dinnerBegin;
    /**
     * 晚餐结束时间
     */
	private Date dinnerEnd;
    /**
     * 宵夜是否提供 0 是 1 否
     */
	private Integer supperEnable;
    /**
     * 宵夜开始时间
     */
	private Date supperBegin;
    /**
     * 宵夜结束时间
     */
	private Date supperEnd;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
