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
 * 房型关联 1 - n 客房信息
 * 房型的客房数量 对应 客房数
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Data
@Accessors(chain = true)
@TableName("t_room")
public class TRoom extends Model< TRoom > {

    private static final long serialVersionUID = 1L;

    /**
     * 客房ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("room_category_id")
    private Integer roomCategoryId;
    /**
     * 客房编号
     */
    private String  number;
    /**
     * 楼层
     */
    private String  floor;
    /**
     * 房间状态 字典值 TODO: 后续给出编号  -- 可定义为字典值：在住、空房、脏房、已预订、已锁定、维护
     */
    private String  status;
    /**
     * 0 正常 1 禁用 2 删除
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
