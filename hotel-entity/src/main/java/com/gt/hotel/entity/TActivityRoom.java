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
 * 活动与房间关系表
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
@Data
@Accessors(chain = true)
public class TActivityRoom extends Model<TActivityRoom> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 酒店ID
     */
	private Integer hotelId;
    /**
     * 活动ID
     */
	private Integer activityId;
    /**
     * 房型ID
     */
	private Integer categoryId;
    /**
     * 房型名称 冗余参数
     */
	private String categoryName;
    /**
     * 客房ID
     */
	private Integer roomId;
    /**
     * 客房编号
     */
	private String roomNum;
    /**
     * 门市价
     */
	private Integer rackRate;
    /**
     * 活动价 单位分 记录
     */
	private Integer activityPrice;
    /**
     * 状态标记
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
