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
 * 客房日历价 — 优先级 第三方协议 > 活动价 > 
 * </p>
 *
 * @author 
 * @since 2017-10-16
 */
@Data
@Accessors(chain = true)
public class TRoomCalendar extends Model<TRoomCalendar> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer hotelId;
    /**
     * 房型ID
     */
	private Integer roomCategoryId;
    /**
     * 开始日期
     */
	private Date beginTime;
    /**
     * 结束日期
     */
	private Date endTime;
    /**
     * 日历价
     */
	private Integer price;
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