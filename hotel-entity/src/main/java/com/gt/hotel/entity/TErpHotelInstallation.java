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
 * ERP酒店基础设施
 * </p>
 *
 * @author 
 * @since 2017-08-08
 */
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_installation")
public class TErpHotelInstallation extends Model<TErpHotelInstallation> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 名称
     */
	private String name;
    /**
     * LOGO路径
     */
	private String logo;
    /**
     * 是否启用
     */
	@TableField("if_use")
	private Integer ifUse;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelInstallation{" +
			"id=" + id +
			", name=" + name +
			", logo=" + logo +
			", ifUse=" + ifUse +
			"}";
	}
}
