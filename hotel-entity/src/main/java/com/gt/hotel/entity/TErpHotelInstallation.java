package com.gt.hotel.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("ERP酒店基础设施")
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_installation")
public class TErpHotelInstallation extends Model<TErpHotelInstallation> {

	private static final long serialVersionUID = 1L;

	/**
     * 类型(0:酒店)
     */
    public static final Integer HOTEL = 0;
    /**
     * 启用 0 
     */
    public static final Integer AVAILABLE = 0;
    /**
     * 不启用 1
     */
    public static final Integer UNAVAILABLE = 1;
	
    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 名称
     */
	@ApiModelProperty("名称")
	private String name;
    /**
     * LOGO路径
     */
	@ApiModelProperty("LOGO路径")
	private String logo;
    /**
     * 是否启用
     */
	@ApiModelProperty("是否启用")
	private Integer ifUse;
    /**
     * 类型(0:酒店)
     */
	@ApiModelProperty("类型(0:酒店)")
	private Integer type;


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
			", type=" + type +
			"}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getIfUse() {
		return ifUse;
	}

	public void setIfUse(Integer ifUse) {
		this.ifUse = ifUse;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
