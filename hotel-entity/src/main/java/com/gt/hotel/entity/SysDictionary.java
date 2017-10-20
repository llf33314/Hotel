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
 * 系统的字典数据表
 * </p>
 *
 * @author 
 * @since 2017-10-19
 */
@Data
@Accessors(chain = true)
public class SysDictionary extends Model<SysDictionary> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 字典类型ID
     */
	private Integer dictTypeId;
    /**
     * 字典编码(例子: 0 女 1 男  )
     */
	private Integer dictCode;
    /**
     * 字典中文名称(例如：男女，比如状态，可以放在字典里，作为查看依据)
     */
	private String dictCnName;
    /**
     * 字典英文名称 全大写
     */
	private String dictEnName;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
