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
 * 基础设施表关系(酒店/房型 关联的基础属性都在这里)
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Data
@Accessors( chain = true )
@TableName( "t_infrastructure_relation" )
public class TInfrastructureRelation extends Model< TInfrastructureRelation > {

    private static final long serialVersionUID = 1L;

    @TableId( value = "id", type = IdType.AUTO )
    private Integer id;
    /**
     * 基础设施表
     */
    @TableField( "infrastructure_id" )
    private Integer infrastructureId;
    /**
     * 模块类型 冗余参数 酒店 客房 …. hotel room
     */
    private String  module;
    /**
     * 引用ID，如果 module_type = room 即保存 room_id 具体 按业务模块类型 定义
     */
    @TableField( "reference_id" )
    private Integer referenceId;
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
