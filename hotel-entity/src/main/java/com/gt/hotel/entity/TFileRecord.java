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
 * 文件记录表 — 文件实际存储在 素材库。此表仅做记录
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Data
@Accessors( chain = true )
@TableName( "t_file_record" )
public class TFileRecord extends Model< TFileRecord > {

    private static final long serialVersionUID = 1L;

    /**
     * 文件ID
     */
    @TableId( value = "id", type = IdType.AUTO )
    private Integer id;
    /**
     * 显示文件名称
     */
    private String  name;
    /**
     * 文件上传时的原始名称
     */
    @TableField( "original_name" )
    private String  originalName;
    /**
     * 文件路径 例如：/aa/bb/cc/f.txt
     */
    private String  path;
    /**
     * 模块类型 常量类型定义在后台文件里 — 酒店 客房 ….
     */
    private String  module;
    /**
     * 引用ID，如果 module_type = room 即保存 room_id 具体 按业务模块类型 定义 如果，module_type = hotel_thumbnail 即对应酒店的缩略图
     */
    @TableField( "reference_id" )
    private Integer referenceId;
    /**
     * 是否启用 0 默认开启 1 关闭 2 删除标记
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

}
