package com.gt.hotel.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * ERP酒店-房间-订单
 * </p>
 *
 * @author 
 * @since 2017-08-17
 */
@Data
public class TErpHotelRoomOrderEx {

	String name;
	
	String book_name;
	
	String book_phone;
	
	String check_in_time;

	String check_out_time;
	
	Integer check_in_standard;
	
	Integer price;
	
	Integer quantity;
	
	Integer order_status;
	
	Integer pay_status;
	
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

	@Override
	public String toString() {
		return "TErpHotelRoomOrderEx [name=" + name + ", book_name=" + book_name + ", book_phone=" + book_phone
				+ ", check_in_time=" + check_in_time + ", check_out_time=" + check_out_time + ", check_in_standard="
				+ check_in_standard + ", price=" + price + ", quantity=" + quantity + ", order_status=" + order_status
				+ ", pay_status=" + pay_status + ", pay_type=" + pay_type + "]";
	}
	
	
}
