package com.gt.hotel.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统的字典类型表(全局缓存 管理员操作)
 * </p>
 *
 * @author zhangmz
 * @since 2017-10-26
 */
@Data
@Accessors(chain = true)
public class SysDictionaryType extends Model<SysDictionaryType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 字典类型中文名称(例如：性别)
     */
    private String  dictTypeCnName;
    /**
     * 字典类型英文名称 大写(例如： GENDER)
     */
    private String  dictTypeEnName;
    /**
     * 字典状态：0 使用中(默认) 1 禁用
     */
    private Integer dictTypeStatus;
    /**
     * 备注说明
     */
    private String  dictTypeRemark;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
