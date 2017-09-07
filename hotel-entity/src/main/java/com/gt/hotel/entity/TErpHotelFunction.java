package com.gt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.enums.FieldFill;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 授权功能
 * </p>
 *
 * @author 
 * @since 2017-08-09
 */
@ApiModel("授权功能")
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_function")
public class TErpHotelFunction extends Model<TErpHotelFunction> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 功能名称
     */
	@ApiModelProperty("功能名称")
	@TableField("function_name")
	private String functionName;
    /**
     * 功能描述
     */
	@ApiModelProperty("功能描述")
	private String description;
	/**
	 * 模块
	 */
	@ApiModelProperty("模块")
	private String model;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelFunction{" +
			"id=" + id +
			", functionName=" + functionName +
			", description=" + description +
			", model=" + model +
			"}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	
}
