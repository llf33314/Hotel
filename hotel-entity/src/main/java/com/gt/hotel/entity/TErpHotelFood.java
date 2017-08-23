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
 * ERP酒店菜品
 * </p>
 *
 * @author 
 * @since 2017-08-16
 */
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_food")
public class TErpHotelFood extends Model<TErpHotelFood> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 酒店ID
     */
	@TableField("hotel_id")
	private Integer hotelId;
    /**
     * 菜品名称
     */
	private String name;
    /**
     * 早餐
     */
	private Integer breakfast;
    /**
     * 午餐
     */
	private Integer lunch;
    /**
     * 晚餐
     */
	private Integer dinner;
    /**
     * 宵夜
     */
	private Integer supper;
    /**
     * 菜品提供方 1:本酒店 2:合作方
     */
	@TableField("provide_from")
	private Integer provideFrom;
    /**
     * 合作方名称
     */
	@TableField("company_name")
	private String companyName;
    /**
     * 新订单接受电话
     */
	@TableField("order_phone")
	private String orderPhone;
    /**
     * 菜品单价
     */
	@TableField("food_price")
	private Integer foodPrice;
    /**
     * 配送时间 分钟为单位
     */
	@TableField("food_deliver_time")
	private Integer foodDeliverTime;
    /**
     * 是否启用
     */
	@TableField("if_use")
	private Integer ifUse;
    /**
     * 创建者
     */
	private String creator;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelFood{" +
			"id=" + id +
			", hotelId=" + hotelId +
			", name=" + name +
			", breakfast=" + breakfast +
			", lunch=" + lunch +
			", dinner=" + dinner +
			", supper=" + supper +
			", provideFrom=" + provideFrom +
			", companyName=" + companyName +
			", orderPhone=" + orderPhone +
			", foodPrice=" + foodPrice +
			", foodDeliverTime=" + foodDeliverTime +
			", ifUse=" + ifUse +
			", creator=" + creator +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(Integer breakfast) {
		this.breakfast = breakfast;
	}

	public Integer getLunch() {
		return lunch;
	}

	public void setLunch(Integer lunch) {
		this.lunch = lunch;
	}

	public Integer getDinner() {
		return dinner;
	}

	public void setDinner(Integer dinner) {
		this.dinner = dinner;
	}

	public Integer getSupper() {
		return supper;
	}

	public void setSupper(Integer supper) {
		this.supper = supper;
	}

	public Integer getProvideFrom() {
		return provideFrom;
	}

	public void setProvideFrom(Integer provideFrom) {
		this.provideFrom = provideFrom;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOrderPhone() {
		return orderPhone;
	}

	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}

	public Integer getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(Integer foodPrice) {
		this.foodPrice = foodPrice;
	}

	public Integer getFoodDeliverTime() {
		return foodDeliverTime;
	}

	public void setFoodDeliverTime(Integer foodDeliverTime) {
		this.foodDeliverTime = foodDeliverTime;
	}

	public Integer getIfUse() {
		return ifUse;
	}

	public void setIfUse(Integer ifUse) {
		this.ifUse = ifUse;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
