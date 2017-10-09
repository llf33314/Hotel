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
 * 基础设施 包含 酒店 客房
 * </p>
 *
 * @author 
 * @since 2017-10-09
 */
@Data
@Accessors(chain = true)
@TableName("t_infrastructure")
public class TInfrastructure extends Model<TInfrastructure> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 名称
     */
	private String name;
    /**
     * 图标地址
     */
	@TableField("icon_url")
	private String iconUrl;
    /**
     * 模块 hotel 酒店基础设施 room 客房基础设施
     */
	private String module;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TInfrastructure{" +
			"id=" + id +
			", name=" + name +
			", iconUrl=" + iconUrl +
			", module=" + module +
			"}";
	}
}
