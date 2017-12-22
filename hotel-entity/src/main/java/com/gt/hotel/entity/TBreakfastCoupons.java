package com.gt.hotel.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 早餐券
 * </p>
 *
 * @author 
 * @since 2017-12-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TBreakfastCoupons extends Model<TBreakfastCoupons> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 订单ID
     */
	private Integer orderId;
    /**
     * 房型ID
     */
	private Integer categoryId;
    /**
     * 房号
     */
	private String roomNum;
    /**
     * 早餐券编号
     */
	private String code;
    /**
     * 核销状态 0 未核销 1 已核销
     */
	private Integer writeOffStatus;
    /**
     * 核销人ID(memberId)
     */
	private Integer writer;
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


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
