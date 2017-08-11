package com.gt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.enums.FieldFill;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * ERP酒店房型
 * </p>
 *
 * @author 
 * @since 2017-08-11
 */
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_room")
public class TErpHotelRoom extends Model<TErpHotelRoom> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 酒店ID
     */
	@NotNull(message = "酒店ID不能为空")
	@TableField("hotel_id")
	private Integer hotelId;
    /**
     * 房型类型
     */
	@NotNull(message = "不能为空")
	private String type;
    /**
     * 房型总数
     */
	@NotNull(message = "不能为空")
	private Integer total;
    /**
     * 简介
     */
	private String introduction;
    /**
     * 价格
     */
	@NotNull(message = "不能为空")
	private Integer price;
    /**
     * 押金
     */
	@NotNull(message = "不能为空")
	private Integer deposit;
    /**
     * 是否启用折扣价
     */
	@TableField("if_discount_price")
	private Integer ifDiscountPrice;
    /**
     * 折扣价
     */
	@TableField("discount_price")
	private Integer discountPrice;
    /**
     * 是否启用周末价
     */
	@TableField("if_weekend_price")
	private Integer ifWeekendPrice;
    /**
     * 周末价
     */
	@TableField("weekend_price")
	private Integer weekendPrice;
    /**
     * 是否启用周一价
     */
	@TableField("if_monday_price")
	private Integer ifMondayPrice;
    /**
     * 周末价
     */
	@TableField("monday_price")
	private Integer mondayPrice;
    /**
     * 是否启用周二价
     */
	@TableField("if_tuesday_price")
	private Integer ifTuesdayPrice;
    /**
     * 周末价
     */
	@TableField("tuesday_price")
	private Integer tuesdayPrice;
    /**
     * 是否启用周三价
     */
	@TableField("if_wednesday_price")
	private Integer ifWednesdayPrice;
    /**
     * 周末价
     */
	@TableField("wednesday_price")
	private Integer wednesdayPrice;
    /**
     * 是否启用周四价
     */
	@TableField("if_thursday_price")
	private Integer ifThursdayPrice;
    /**
     * 周末价
     */
	@TableField("thursday_price")
	private Integer thursdayPrice;
    /**
     * 是否启用周日价
     */
	@TableField("if_sunday_price")
	private Integer ifSundayPrice;
    /**
     * 周末价
     */
	@TableField("sunday_price")
	private Integer sundayPrice;
    /**
     * 团队价
     */
	@TableField("team_price")
	private Integer teamPrice;
    /**
     * 团队价生效数量
     */
	@TableField("team_price_effective")
	private Integer teamPriceEffective;
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
		return "TErpHotelRoom{" +
			"id=" + id +
			", hotelId=" + hotelId +
			", type=" + type +
			", total=" + total +
			", introduction=" + introduction +
			", price=" + price +
			", deposit=" + deposit +
			", ifDiscountPrice=" + ifDiscountPrice +
			", discountPrice=" + discountPrice +
			", ifWeekendPrice=" + ifWeekendPrice +
			", weekendPrice=" + weekendPrice +
			", ifMondayPrice=" + ifMondayPrice +
			", mondayPrice=" + mondayPrice +
			", ifTuesdayPrice=" + ifTuesdayPrice +
			", tuesdayPrice=" + tuesdayPrice +
			", ifWednesdayPrice=" + ifWednesdayPrice +
			", wednesdayPrice=" + wednesdayPrice +
			", ifThursdayPrice=" + ifThursdayPrice +
			", thursdayPrice=" + thursdayPrice +
			", ifSundayPrice=" + ifSundayPrice +
			", sundayPrice=" + sundayPrice +
			", teamPrice=" + teamPrice +
			", teamPriceEffective=" + teamPriceEffective +
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getDeposit() {
		return deposit;
	}

	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}

	public Integer getIfDiscountPrice() {
		return ifDiscountPrice;
	}

	public void setIfDiscountPrice(Integer ifDiscountPrice) {
		this.ifDiscountPrice = ifDiscountPrice;
	}

	public Integer getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Integer getIfWeekendPrice() {
		return ifWeekendPrice;
	}

	public void setIfWeekendPrice(Integer ifWeekendPrice) {
		this.ifWeekendPrice = ifWeekendPrice;
	}

	public Integer getWeekendPrice() {
		return weekendPrice;
	}

	public void setWeekendPrice(Integer weekendPrice) {
		this.weekendPrice = weekendPrice;
	}

	public Integer getIfMondayPrice() {
		return ifMondayPrice;
	}

	public void setIfMondayPrice(Integer ifMondayPrice) {
		this.ifMondayPrice = ifMondayPrice;
	}

	public Integer getMondayPrice() {
		return mondayPrice;
	}

	public void setMondayPrice(Integer mondayPrice) {
		this.mondayPrice = mondayPrice;
	}

	public Integer getIfTuesdayPrice() {
		return ifTuesdayPrice;
	}

	public void setIfTuesdayPrice(Integer ifTuesdayPrice) {
		this.ifTuesdayPrice = ifTuesdayPrice;
	}

	public Integer getTuesdayPrice() {
		return tuesdayPrice;
	}

	public void setTuesdayPrice(Integer tuesdayPrice) {
		this.tuesdayPrice = tuesdayPrice;
	}

	public Integer getIfWednesdayPrice() {
		return ifWednesdayPrice;
	}

	public void setIfWednesdayPrice(Integer ifWednesdayPrice) {
		this.ifWednesdayPrice = ifWednesdayPrice;
	}

	public Integer getWednesdayPrice() {
		return wednesdayPrice;
	}

	public void setWednesdayPrice(Integer wednesdayPrice) {
		this.wednesdayPrice = wednesdayPrice;
	}

	public Integer getIfThursdayPrice() {
		return ifThursdayPrice;
	}

	public void setIfThursdayPrice(Integer ifThursdayPrice) {
		this.ifThursdayPrice = ifThursdayPrice;
	}

	public Integer getThursdayPrice() {
		return thursdayPrice;
	}

	public void setThursdayPrice(Integer thursdayPrice) {
		this.thursdayPrice = thursdayPrice;
	}

	public Integer getIfSundayPrice() {
		return ifSundayPrice;
	}

	public void setIfSundayPrice(Integer ifSundayPrice) {
		this.ifSundayPrice = ifSundayPrice;
	}

	public Integer getSundayPrice() {
		return sundayPrice;
	}

	public void setSundayPrice(Integer sundayPrice) {
		this.sundayPrice = sundayPrice;
	}

	public Integer getTeamPrice() {
		return teamPrice;
	}

	public void setTeamPrice(Integer teamPrice) {
		this.teamPrice = teamPrice;
	}

	public Integer getTeamPriceEffective() {
		return teamPriceEffective;
	}

	public void setTeamPriceEffective(Integer teamPriceEffective) {
		this.teamPriceEffective = teamPriceEffective;
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
