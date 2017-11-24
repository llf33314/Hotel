package com.gt.hotel.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 套餐(包含中介/单位)
 * </p>
 *
 * @author 
 * @since 2017-11-24
 */
@Data
@Accessors(chain = true)
public class TPackage extends Model<TPackage> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 套餐名称
     */
	private String name;
    /**
     * 酒店ID
     */
	private Integer hotelId;
    /**
     * 门店ID
     */
	private Integer shopId;
    /**
     * 备注
     */
	private String remark;
    /**
     * 套餐针对的单位类型：0 协议单位  1 中介
     */
	private Integer module;
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
	private Date createdAt;
    /**
     * 最后修改人 ID
     */
	private Integer updatedBy;
    /**
     * 最后修改时间
     */
	private Date updatedAt;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
