package com.gt.hotel.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
 * ERP酒店-餐饮-订单
 * </p>
 *
 * @author 
 * @since 2017-08-17
 */
@ApiModel("TErpHotelFoodOrder")
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_food_order")
public class TErpHotelFoodOrder extends Model<TErpHotelFoodOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 支付方式 1:在线支付(其实就是微信)
     */
    public static final Integer ONLINE_PAY = 1;
    /**
     * 支付方式 2:到店支付
     */
    public static final Integer OFFLINE_PAY = 2;
    /**
     * 支付方式 3:储值卡支付
     */
    public static final Integer VALUE_PAY = 3;
    /**
     * 支付方式  4:支付宝 
     */
    public static final Integer ALI_PAY = 4;
    /**
     * 支付方式 5:银行卡
     */
    public static final Integer CARD_PAY = 5;
    /**
     * 支付方式 6:现金
     */
    public static final Integer CASH_PAY = 6;
    /**
     * 支付状态 0:未支付
     */
    public static final Integer UNPAID = 0;
    /**
     * 支付状态 1:已支付
     */
    public static final Integer PAID = 1;
    /**
     * 支付状态 2:挂账
     */
    public static final Integer DEBTS = 2;
    /**
     * 订单状态【订单状态 0:处理中】
     */
    public static final Integer PROCESS = 0;
    /**
     * 订单状态【订单状态 1:已确认】
     */
    public static final Integer CONFIRM = 1;
    /**
     * 订单状态【订单状态 2:已取消】
     */
    public static final Integer CANCEL = 2;
    /**
     * 订单状态【订单状态 3:已入住】
     */
    public static final Integer CHECK_IN = 3;
    /**
     * 订单状态【订单状态 4:已完成】
     */
    public static final Integer END = 4;
    /**
     * 可用
     */
    public static final Integer AVAILABLE = 1;
    /**
     * 不可用
     */
    public static final Integer UNAVAILABLE = 0;
    
    /**
     * id
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
     * 商家id
     */
	@ApiModelProperty("商家id")
	@TableField("bus_id")
	private Integer busId;
    /**
     * 房间号
     */
	@ApiModelProperty("房间号")
	private String number;
    /**
     * 订单价格
     */
	@ApiModelProperty("订单价格")
	private Integer price;
    /**
     * 订单号
     */
	@ApiModelProperty("订单号")
	@TableField("order_number")
	private String orderNumber;
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
     * 创建时间
     */
	@ApiModelProperty("创建时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("create_time")
	private Date createTime;
    /**
     * 预计到达时间
     */
	@ApiModelProperty("预计到达时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("arrival_time")
	private Date arrivalTime;
    /**
     * 支付方式 1:在线支付 2:到店支付 3:储值卡支付 4:支付宝 5:银行卡 6:现金
     */
	@ApiModelProperty("支付方式 1:在线支付 2:到店支付 3:储值卡支付 4:支付宝 5:银行卡 6:现金")
	@TableField("pay_type")
	private Integer payType;
    /**
     * 支付时间时间
     */
	@ApiModelProperty("支付时间时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("pay_time")
	private Date payTime;
    /**
     * 支付状态 0:未支付 1:已支付 2:挂账
     */
	@ApiModelProperty("支付状态 0:未支付 1:已支付 2:挂账")
	@TableField("pay_status")
	private Integer payStatus;
    /**
     * 订单状态
     */
	@ApiModelProperty("订单状态【订单状态 0:处理中 1:已确认 2:已取消 3:已入住(不需要) 4:已完成】")
	@TableField("order_status")
	private Integer orderStatus;
    /**
     * 备注
     */
	@ApiModelProperty("备注")
	private String remark;
    /**
     * 是否可用(0:false, 1:true)
     */
	@ApiModelProperty("是否可用(0:false, 1:true)")
	private String source;
	/**
	 * 是否可用 
	 */
	@ApiModelProperty("是否可用")
	private String available;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelFoodOrder [id=" + id + ", hotelId=" + hotelId + ", busId=" + busId + ", number=" + number
				+ ", price=" + price + ", orderNumber=" + orderNumber + ", bookName=" + bookName + ", bookPhone="
				+ bookPhone + ", createTime=" + createTime + ", arrivalTime=" + arrivalTime + ", payType=" + payType
				+ ", payTime=" + payTime + ", payStatus=" + payStatus + ", orderStatus=" + orderStatus + ", remark="
				+ remark + ", source=" + source + ", available=" + available + "]";
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
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

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
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
	
}
