package com.gt.hotel.entity;

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
 * ERP酒店-发票类目
 * </p>
 *
 * @author 
 * @since 2017-09-04
 */
@ApiModel(description = "酒店-发票类目")
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_invoice_category")
public class TErpHotelInvoiceCategory extends Model<TErpHotelInvoiceCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 发票类目名
     */
	@ApiModelProperty("发票类目名")	
	private String name;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelInvoiceCategory{" +
			"id=" + id +
			", name=" + name +
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
}
