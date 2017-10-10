package com.gt.hotel.entity;

<<<<<<< HEAD
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.enums.FieldFill;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 房型关联 1 - n 客房信息
房型的客房数量 对应 客房数
 * </p>
 *
 * @author 
 * @since 2017-10-09
 */
@Data
@Accessors(chain = true)
@TableName("t_room")
public class TRoom extends Model<TRoom> {
=======
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
@Accessors( chain = true )
@TableName( "t_room" )
public class TRoom extends Model< TRoom > {
>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6

    private static final long serialVersionUID = 1L;

    /**
     * 客房ID
     */
<<<<<<< HEAD
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("room_category_id")
	private Integer roomCategoryId;
    /**
     * 客房编号
     */
	private String number;
    /**
     * 楼层
     */
	private String floor;
    /**
     * 房间状态 字典值 TODO: 后续给出编号  -- 可定义为字典值：在住、空房、脏房、已预订、已锁定、维护
     */
	private String status;
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
	private Date createdAt;
    /**
     * 最后修改人 ID
     */
	@TableField("updated_by")
	private Integer updatedBy;
    /**
     * 最后修改时间
     */
	@TableField("updated_at")
	private Date updatedAt;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TRoom{" +
			"id=" + id +
			", roomCategoryId=" + roomCategoryId +
			", number=" + number +
			", floor=" + floor +
			", status=" + status +
			", markModified=" + markModified +
			", createdBy=" + createdBy +
			", createdAt=" + createdAt +
			", updatedBy=" + updatedBy +
			", updatedAt=" + updatedAt +
			"}";
	}
=======
    @TableId( value = "id", type = IdType.AUTO )
    private Integer id;
    @TableField( "room_category_id" )
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
    @TableField( "mark_modified" )
    private Integer markModified;
    /**
     * 创建者ID
     */
    @TableField( "created_by" )
    private Integer createdBy;
    /**
     * 创建时间
     */
    @TableField( "created_at" )
    private Date    createdAt;
    /**
     * 最后修改人 ID
     */
    @TableField( "updated_by" )
    private Integer updatedBy;
    /**
     * 最后修改时间
     */
    @TableField( "updated_at" )
    private Date    updatedAt;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6
}
