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
 * ERP酒店-房间-订单-客人
 * </p>
 *
 * @author 
 * @since 2017-08-17
 */
@ApiModel("TErpHotelRoomOrderGuest")
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_room_order_guest")
public class TErpHotelRoomOrderGuest extends Model<TErpHotelRoomOrderGuest> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 订单ID
     */
	@ApiModelProperty("订单ID")
	@TableField("order_id")
	private Integer orderId;
    /**
     * 会员ID
     */
	@ApiModelProperty("会员ID")
	@TableField("member_id")
	private Integer memberId;
    /**
     * 预订人
     */
	@ApiModelProperty("预订人")
	@TableField("book_name")
	private String bookName;
    /**
     * 电话
     */
	@ApiModelProperty("电话")
	@TableField("book_phone")
	private String bookPhone;
    /**
     * 性别
     */
	@ApiModelProperty("性别")
	@TableField("book_gender")
	private String bookGender;
    /**
     * 证件类型
     */
	@ApiModelProperty("证件类型")
	@TableField("document_type")
	private String documentType;
    /**
     * 证件号码
     */
	@ApiModelProperty("证件号码")
	@TableField("document_type_value")
	private String documentTypeValue;
    /**
     * 客人类型(0：主，1：从)
     */
	@ApiModelProperty("客人类型(0：主，1：从)")
	@TableField("guest_type")
	private Integer guestType;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelRoomOrderGuest{" +
			"id=" + id +
			", orderId=" + orderId +
			", memberId=" + memberId +
			", bookName=" + bookName +
			", bookPhone=" + bookPhone +
			", bookGender=" + bookGender +
			", documentType=" + documentType +
			", documentTypeValue=" + documentTypeValue +
			", guestType=" + guestType +
			"}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookPhone() {
		return bookPhone;
	}

	public void setBookPhone(String bookPhone) {
		this.bookPhone = bookPhone;
	}

	public String getBookGender() {
		return bookGender;
	}

	public void setBookGender(String bookGender) {
		this.bookGender = bookGender;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentTypeValue() {
		return documentTypeValue;
	}

	public void setDocumentTypeValue(String documentTypeValue) {
		this.documentTypeValue = documentTypeValue;
	}

	public Integer getGuestType() {
		return guestType;
	}

	public void setGuestType(Integer guestType) {
		this.guestType = guestType;
	}
}
