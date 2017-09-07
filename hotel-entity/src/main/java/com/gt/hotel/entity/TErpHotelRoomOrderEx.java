package com.gt.hotel.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * ERP酒店-房间-订单
 * </p>
 *
 * @author 
 * @since 2017-08-17
 */
@ApiModel("TErpHotelRoomOrderEx")
@Data
public class TErpHotelRoomOrderEx {
	
	@ApiModelProperty("ID")
	Integer id;

	@ApiModelProperty("酒店名称")
	String name;
	
	@ApiModelProperty("预定人")
	String book_name;
	
	@ApiModelProperty("预订电话")
	String book_phone;
	
	@ApiModelProperty("入住时间")
	String check_in_time;

	@ApiModelProperty("离店时间")
	String check_out_time;
	
	@ApiModelProperty("入住标准(0：全天房，1：钟点房，2：长包房)")
	Integer check_in_standard;
	
	@ApiModelProperty("价格")
	Integer price;
	
	@ApiModelProperty("数量")
	Integer quantity;
	
	@ApiModelProperty("订单状态")
	Integer order_status;
	
	@ApiModelProperty("支付状态")
	Integer pay_status;
	
	@ApiModelProperty("支付类型")
	Integer pay_type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_phone() {
		return book_phone;
	}

	public void setBook_phone(String book_phone) {
		this.book_phone = book_phone;
	}

	public String getCheck_in_time() {
		return check_in_time;
	}

	public void setCheck_in_time(String check_in_time) {
		this.check_in_time = check_in_time;
	}

	public String getCheck_out_time() {
		return check_out_time;
	}

	public void setCheck_out_time(String check_out_time) {
		this.check_out_time = check_out_time;
	}

	public Integer getCheck_in_standard() {
		return check_in_standard;
	}

	public void setCheck_in_standard(Integer check_in_standard) {
		this.check_in_standard = check_in_standard;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}

	public Integer getPay_status() {
		return pay_status;
	}

	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}

	public Integer getPay_type() {
		return pay_type;
	}

	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TErpHotelRoomOrderEx [id=" + id + ", name=" + name + ", book_name=" + book_name + ", book_phone="
				+ book_phone + ", check_in_time=" + check_in_time + ", check_out_time=" + check_out_time
				+ ", check_in_standard=" + check_in_standard + ", price=" + price + ", quantity=" + quantity
				+ ", order_status=" + order_status + ", pay_status=" + pay_status + ", pay_type=" + pay_type + "]";
	}

	
}
