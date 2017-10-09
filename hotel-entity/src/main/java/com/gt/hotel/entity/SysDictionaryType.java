package com.gt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.enums.FieldFill;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统的字典类型表(全局缓存 管理员操作)
 * </p>
 *
 * @author 
 * @since 2017-10-09
 */
@Data
@Accessors(chain = true)
@TableName("sys_dictionary_type")
public class SysDictionaryType extends Model<SysDictionaryType> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 字典名称(例如：酒店设施)
     */
	@TableField("dict_name")
	private String dictName;
    /**
     * 字典状态：0 使用中(默认) 1 不再使用
     */
	@TableField("dict_status")
	private Integer dictStatus;
    /**
     * 备注说明
     */
	@TableField("dict_remark")
	private String dictRemark;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysDictionaryType{" +
			"id=" + id +
			", dictName=" + dictName +
			", dictStatus=" + dictStatus +
			", dictRemark=" + dictRemark +
			"}";
	}
}
