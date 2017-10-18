package com.gt.hotel.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 客房长包房
 * </p>
 *
 * @author 
 * @since 2017-10-18
 */
@Data
@Accessors(chain = true)
public class TRoomPermanent extends Model<TRoomPermanent> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer categoryId;
    /**
     * 酒店ID t_hotel.id
     */
	private Integer hotelId;
	private String ruleName;
    /**
     * 入住天数
     */
	private Integer checkInDay;
    /**
     * 折扣 默认：1 无折扣
     */
	private Integer discount;
    /**
     * 押金
     */
	private Integer deposit;
    /**
     * 0 正常 1 禁用 2 删除
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


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
