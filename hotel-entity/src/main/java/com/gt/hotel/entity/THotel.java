package com.gt.hotel.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * @author zhangmz
 * @since 2017-10-26
 */
@Data
@Accessors(chain = true)
public class THotel extends Model<THotel> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 商家ID 酒店所有者
     */
    private Integer busId;
    /**
     * 门店ID
     */
    private Integer shopId;
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
     * 标记备注 0 启用 1 禁用 2 删除 默认0  可查看 sys_dictionary.dict_type_id=2
     */
    private Integer markModified;
    /**
     * 创建者ID
     */
    private Integer createdBy;
    /**
     * 创建时间
     */
    private Date    createdAt;
    /**
     * 最后修改人 ID
     */
    private Integer updatedBy;
    /**
     * 最后修改时间
     */
    private Date    updatedAt;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
