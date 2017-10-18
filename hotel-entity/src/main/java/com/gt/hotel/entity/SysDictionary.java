package com.gt.hotel.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统的字典数据表
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Data
@Accessors(chain = true)
@TableName("sys_dictionary")
public class SysDictionary extends Model< SysDictionary > {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 字典类型ID
     */
    @TableField("dict_type_id")
    private Integer dictTypeId;
    /**
     * 字典编码
     */
    @TableField("dict_code")
    private Integer dictCode;
    /**
     * 字典名称(例如：男女，比如状态，可以放在字典里，作为查看依据)
     */
    @TableField("dict_name")
    private String  dictName;

    @Override protected Serializable pkVal() {
	return this.id;
    }

}
