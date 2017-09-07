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
 * 
 * </p>
 *
 * @author 
 * @since 2017-09-04
 */
@ApiModel(description = "酒店-发票")
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_invoice_relation")
public class TErpHotelInvoiceRelation extends Model<TErpHotelInvoiceRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 酒店ID
     */
	@ApiModelProperty("酒店ID")
	@TableField("hotel_id")
	private Integer hotelId;
    /**
     * 发票id
     */
	@ApiModelProperty("发票id")
	@TableField("invoice_id")
	private Integer invoiceId;
    /**
     * 发票类目名
     */
	@ApiModelProperty("发票类目名")
	@TableField("invoice_name")
	private String invoiceName;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelInvoiceRelation{" +
			"id=" + id +
			", hotelId=" + hotelId +
			", invoiceId=" + invoiceId +
			", invoiceName=" + invoiceName +
			"}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
}
