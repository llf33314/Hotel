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
 * 酒店主表
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Data
@Accessors( chain = true )
@TableName( "t_hotel" )
public class THotel extends Model< THotel > {

    private static final long serialVersionUID = 1L;

    @TableId( value = "id", type = IdType.AUTO )
    private Integer id;
    /**
     * 商家ID 酒店所有者
     */
    @TableField( "bus_id" )
    private Integer busId;
    /**
     * 门店ID
     */
    @TableField( "store_id" )
    private Integer storeId;
    /**
     * 酒店名称
     */
    private String  name;
    /**
     * 酒店描述
     */
    private String  desc;
    /**
     * 酒店负责人 手机号
     */
    private String  phone;
    /**
     * 酒店地址
     */
    private String  address;
    /**
     * 酒店缩略图 URL 地址
     */
    private String  thumbnail;
    /**
     * 酒店Logo
     */
    private String  logo;
    private Double  longitude;
    private Double  latitude;
    /**
     * 标记备注 0 默认开启 1 关闭 2 删除标记
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
