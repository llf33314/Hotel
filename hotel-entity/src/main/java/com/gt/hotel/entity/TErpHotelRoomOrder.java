package com.gt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.enums.FieldFill;

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
@Accessors(chain = true)
@TableName("t_erp_hotel_room_order")
public class TErpHotelRoomOrder extends Model<TErpHotelRoomOrder> {

    private static final long serialVersionUID = 1L;
    
    public TErpHotelRoomOrder() {
	}

	public TErpHotelRoomOrder(Integer id, Integer hotelId, Integer busId, Integer checkInMode, Integer checkInStandard,
			Integer price, Integer quantity, Integer ifCashPledge, Integer cashPledge, String orderNumber,
			Date checkInTime, Date checkOutTime, Date createTime, Integer payType, Date payTime, Integer payStatus,
			Integer orderStatus, String remark, String source) {
		super();
		this.id = id;
		this.hotelId = hotelId;
		this.busId = busId;
		this.checkInMode = checkInMode;
		this.checkInStandard = checkInStandard;
		this.price = price;
		this.quantity = quantity;
		this.ifCashPledge = ifCashPledge;
		this.cashPledge = cashPledge;
		this.orderNumber = orderNumber;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.createTime = createTime;
		this.payType = payType;
		this.payTime = payTime;
		this.payStatus = payStatus;
		this.orderStatus = orderStatus;
		this.remark = remark;
		this.source = source;
	}

	/**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 酒店ID
     */
	@TableField("hotel_id")
	private Integer hotelId;
    /**
     * 商家id
     */
	@TableField("bus_id")
	private Integer busId;
    /**
     * 入住方式(0：散客，1：协议单位， 2：团队)
     */
	@TableField("check_in_mode")
	private Integer checkInMode;
    /**
     * 入住标准(0：全天房，1：钟点房，2：长包房)
     */
	@TableField("check_in_standard")
	private Integer checkInStandard;
    /**
     * 订单价格
     */
	private Integer price;
    /**
     * 数量
     */
	private Integer quantity;
    /**
     * 是否免押金
     */
	@TableField("if_cash_pledge")
	private Integer ifCashPledge;
    /**
     * 订单押金
     */
	@TableField("cash_pledge")
	private Integer cashPledge;
    /**
     * 订单号
     */
	@TableField("order_number")
	private String orderNumber;
    /**
     * 入住时间
     */
	@TableField("check_in_time")
	private Date checkInTime;
    /**
     * 离店时间
     */
	@TableField("check_out_time")
	private Date checkOutTime;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 支付方式 1:在线支付 2:到店支付 3:储值卡支付 4:支付宝 5:银行卡 6:现金
     */
	@TableField("pay_type")
	private Integer payType;
    /**
     * 支付时间时间
     */
	@TableField("pay_time")
	private Date payTime;
    /**
     * 支付状态 0:未支付 1:已支付 2:挂账
     */
	@TableField("pay_status")
	private Integer payStatus;
    /**
     * 订单状态
     */
	@TableField("order_status")
	private Integer orderStatus;
    /**
     * 备注
     */
	private String remark;
    /**
     * 来源
     */
	private String source;
	/**
	 * 是否可用 
	 */
	private String available;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelRoomOrder{" +
			"id=" + id +
			", hotelId=" + hotelId +
			", busId=" + busId +
			", checkInMode=" + checkInMode +
			", checkInStandard=" + checkInStandard +
			", price=" + price +
			", quantity=" + quantity +
			", ifCashPledge=" + ifCashPledge +
			", cashPledge=" + cashPledge +
			", orderNumber=" + orderNumber +
			", checkInTime=" + checkInTime +
			", checkOutTime=" + checkOutTime +
			", createTime=" + createTime +
			", payType=" + payType +
			", payTime=" + payTime +
			", payStatus=" + payStatus +
			", orderStatus=" + orderStatus +
			", remark=" + remark +
			", source=" + source +
			", available=" + available +
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

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public Integer getCheckInMode() {
		return checkInMode;
	}

	public void setCheckInMode(Integer checkInMode) {
		this.checkInMode = checkInMode;
	}

	public Integer getCheckInStandard() {
		return checkInStandard;
	}

	public void setCheckInStandard(Integer checkInStandard) {
		this.checkInStandard = checkInStandard;
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

	public Integer getIfCashPledge() {
		return ifCashPledge;
	}

	public void setIfCashPledge(Integer ifCashPledge) {
		this.ifCashPledge = ifCashPledge;
	}

	public Integer getCashPledge() {
		return cashPledge;
	}

	public void setCashPledge(Integer cashPledge) {
		this.cashPledge = cashPledge;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Date getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}
}
