package com.gt.hotel.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * ERP酒店-押金
 * </p>
 *
 * @author 
 * @since 2017-09-05
 */
@ApiModel(description = "押金")
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_cash_pledge")
public class TErpHotelCashPledge extends Model<TErpHotelCashPledge> {

    private static final long serialVersionUID = 1L;
    
    /**
     * 押金状态(0: 未退)
     */
    public static final Integer UNREFUNDS = 0;
    /**
     * 押金状态(1:已退)
     */
    public static final Integer REFUNDS = 1;
    
    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 商家id
     */
	@ApiModelProperty("商家id")
	@TableField("bus_id")
	private Integer busId;
    /**
     * 会员id
     */
	@ApiModelProperty("会员id")
	@TableField("member_id")
	private Integer memberId;
    /**
     * 酒店id
     */
	@ApiModelProperty("酒店id")
	@TableField("hotel_id")
	private Integer hotelId;
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
     * 押金金额
     */
	@ApiModelProperty("押金金额")
	@TableField("cash_pledge")
	private Integer cashPledge;
    /**
     * 押金状态(0: 未退, 1:已退)
     */
	@ApiModelProperty("押金状态(0: 未退, 1:已退)")
	private Integer status;
    /**
     * 押金类型，交易类型
     */
	@ApiModelProperty("押金类型，交易类型")
	private String type;
    /**
     * 退款金额
     */
	@ApiModelProperty("退款金额")
	private Integer refunds;
    /**
     * 退款说明
     */
	@ApiModelProperty("退款说明")
	@TableField("refunds_explain")
	private String refundsExplain;
    /**
     * 创建时间
     */
	@ApiModelProperty("创建时间")
	private Date createtime;
    /**
     * 订单id
     */
	@ApiModelProperty("订单id")
	@TableField("order_id")
	private String orderId;
    /**
     * 是否查看
     */
	@ApiModelProperty("是否查看")
	private Integer lookstatus;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelCashPledge{" +
			"id=" + id +
			", busId=" + busId +
			", memberId=" + memberId +
			", hotelId=" + hotelId +
			", bookName=" + bookName +
			", bookPhone=" + bookPhone +
			", cashPledge=" + cashPledge +
			", status=" + status +
			", type=" + type +
			", refunds=" + refunds +
			", refundsExplain=" + refundsExplain +
			", createtime=" + createtime +
			", orderId=" + orderId +
			", lookstatus=" + lookstatus +
			"}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
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

	public Integer getCashPledge() {
		return cashPledge;
	}

	public void setCashPledge(Integer cashPledge) {
		this.cashPledge = cashPledge;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getRefunds() {
		return refunds;
	}

	public void setRefunds(Integer refunds) {
		this.refunds = refunds;
	}

	public String getRefundsExplain() {
		return refundsExplain;
	}

	public void setRefundsExplain(String refundsExplain) {
		this.refundsExplain = refundsExplain;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getLookstatus() {
		return lookstatus;
	}

	public void setLookstatus(Integer lookstatus) {
		this.lookstatus = lookstatus;
	}
	
	
}
