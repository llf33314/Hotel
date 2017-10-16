package com.gt.hotel.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 活动与房间关系表
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Data
@Accessors(chain = true)
@TableName("t_activity_room")
public class TActivityRoom extends Model< TActivityRoom > {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 酒店ID
     */
    @TableField("hotel_id")
    private Integer hotelId;
    /**
     * 房型ID
     */
    @TableField("room_category_id")
    private Integer roomCategoryId;
    /**
     * 房型名称 冗余参数
     */
    @TableField("room_category_name")
    private String  roomCategoryName;
    /**
     * 客房ID
     */
    @TableField("room_id")
    private Integer roomId;
    /**
     * 客房编号
     */
    @TableField("room_number")
    private String  roomNumber;
    /**
     * 门市价
     */
    @TableField("rack_rate")
    private Integer rackRate;
    /**
     * 活动价 单位分 记录
     */
    @TableField("activity_price")
    private Integer activityPrice;
    /**
     * 状态标记
     */
    @TableField("mark_modified")
    private Integer markModified;
    /**
     * 创建者ID
     */
    @TableField("created_by")
    private Integer createdBy;
    /**
     * 创建时间
     */
    @TableField("created_at")
    private Date    createdAt;
    /**
     * 最后修改人 ID
     */
    @TableField("updated_by")
    private Integer updatedBy;
    /**
     * 最后修改时间
     */
    @TableField("updated_at")
    private Date    updatedAt;

    @Override protected Serializable pkVal() {
	return this.id;
    }

}
