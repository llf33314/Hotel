package com.gt.hotel.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 基础设施 包含 酒店 客房
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
@Data
@Accessors(chain = true)
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
	private String iconUrl;
    /**
     * 模块 hotel 酒店基础设施 room 客房基础设施 字典值 sys_dictionary_type.id=5
     */
	private String module;
    /**
     * 排序字段 根据这个排序
     */
	private Integer sortBy;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
