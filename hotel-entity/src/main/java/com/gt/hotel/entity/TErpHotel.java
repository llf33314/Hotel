package com.gt.hotel.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

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
 * ERP酒店主表
 * </p>
 *
 * @author 
 * @since 2017-08-07
 */
@ApiModel(value = "TErpHotel", description = "酒店")
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel")
public class TErpHotel extends Model<TErpHotel> {
	
	/**
	 * 支付方式(1：在线支付)
	 */
	public static final Integer ONLINE_PAY = 1;
	/**
	 * 支付方式(2：到店支付)
	 */
	public static final Integer OFFLINE_PAY = 2;
	/**
	 * 支付方式(3：1&2)
	 */
	public static final Integer ALL_PAY = 3;

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 商家ID
     */
	@ApiModelProperty(value = "商家ID")
	@TableField("bus_id")
	private Integer busId;
    /**
     * 门店ID
     */
	@NotNull(message = "门店ID不能为空")
	@ApiModelProperty(value = "门店ID")
	@TableField("shop_id")
	private Integer shopId;
    /**
     * 名称
     */
	@ApiModelProperty(value = "名称")
	private String name;
    /**
     * 介绍
     */
	@ApiModelProperty(value = "介绍")
	private String introduction;
    /**
     * 电话
     */
	@ApiModelProperty(value = "电话")
	private String phone;
    /**
     * 地址
     */
	@ApiModelProperty(value = "地址")
	private String address;
	/**
     * 经度
     */
	@ApiModelProperty(value = "经度")
	private Double longitude;
	/**
     * 纬度
     */
	@ApiModelProperty(value = "纬度")
	private Double latitude;
    /**
     * 是否收集预约人
     */
	@ApiModelProperty(value = "是否收集预约人")
	@TableField("if_reserve_man")
	private Integer ifReserveMan;
    /**
     * 是否收集预约电话
     */
	@ApiModelProperty(value = "是否收集预约电话")
	@TableField("if_reserve_phone")
	private Integer ifReservePhone;
    /**
     * 是否收集备注
     */
	@ApiModelProperty(value = "是否收集备注")
	@TableField("if_remark")
	private Integer ifRemark;
    /**
     * 支付方式(1：在线支付 | 2：到店支付 | 3：1&2)
     */
	@ApiModelProperty(value = "支付方式(1：在线支付 | 2：到店支付 | 3：1&2)")
	@TableField("pay_mode")
	private Integer payMode;
    /**
     * 是否开启短信通知
     */
	@ApiModelProperty(value = "是否开启短信通知")
	@TableField("if_sms")
	private Integer ifSms;
    /**
     * 接受信息手机号
     */
	@ApiModelProperty(value = "接受信息手机号")
	@TableField("sms_phone")
	private String smsPhone;
    /**
     * 是否开启一键退房
     */
	@ApiModelProperty(value = "是否开启一键退房")
	@TableField("if_check_out")
	private Integer ifCheckOut;
    /**
     * 是否开启餐饮
     */
	@ApiModelProperty(value = "是否开启餐饮")
	@TableField("if_food")
	private Integer ifFood;
    /**
     * 是否开启公告
     */
	@ApiModelProperty(value = "是否开启公告")
	@TableField("if_bulletin")
	private Integer ifBulletin;
    /**
     * 公告
     */
	@ApiModelProperty(value = "公告")
	private String bulletin;
    /**
     * 是否显示剩余房型
     */
	@ApiModelProperty(value = "是否显示剩余房型")
	@TableField("if_remnant_room")
	private Integer ifRemnantRoom;
    /**
     * 是否显示一键续住
     */
	@ApiModelProperty(value = "是否显示一键续住")
	@TableField("if_continue")
	private Integer ifContinue;
    /**
     * 是否确认订单信息功能
     */
	@ApiModelProperty(value = "是否确认订单信息功能")
	@TableField("if_confirm_info")
	private Integer ifConfirmInfo;
    /**
     * 是否开启早餐券
     */
	@ApiModelProperty(value = "是否开启早餐券")
	@TableField("if_breakfast")
	private Integer ifBreakfast;
    /**
     * 默认早餐券数量
     */
	@ApiModelProperty(value = "默认早餐券数量")
	@TableField("breakfast_quantity")
	private Integer breakfastQuantity;
    /**
     * 是否开启房价活动
     */
	@ApiModelProperty(value = "是否开启房价活动")
	@TableField("if_activity_prices")
	private Integer ifActivityPrices;
    /**
     * 是否开启会员免押金
     */
	@ApiModelProperty(value = "是否开启会员免押金")
	@TableField("if_free_deposit")
	private Integer ifFreeDeposit;
    /**
     * 是否开启会员最晚退房时间
     */
	@ApiModelProperty(value = "是否开启会员最晚退房时间")
	@TableField("if_last_check_out")
	private Integer ifLastCheckOut;
	/**
     * 是否开启团购
     */
	@ApiModelProperty(value = "是否开启团购")
	@TableField("if_group_buy")
	private Integer ifGroupBuy;
	/**
     * 是否开启秒杀房
     */
	@ApiModelProperty(value = "是否开启秒杀房")
	@TableField("if_spike")
	private Integer ifSpike;
	/**
     * 是否开启钟点房
     */
	@ApiModelProperty(value = "是否开启钟点房")
	@TableField("if_hour")
	private Integer ifHour;
	/**
	 * 是否开启特价房
	 */
	@ApiModelProperty(value = "是否开启特价房")
	@TableField("if_special")
	private Integer ifSpecial;
    /**
     * 创建者
     */
	@ApiModelProperty(value = "创建者")
	private String creator;
    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间")
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@ApiModelProperty(value = "更新时间")
	@TableField("update_time")
	private Date updateTime;
	
	/**
     * 餐饮支付方式(1：在线支付 | 2：到店支付 | 3：1&2)
     */
	@ApiModelProperty(value = "餐饮支付方式(1：在线支付 | 2：到店支付 | 3：1&2)")
	@TableField("food_pay_mode")
	private String foodPayMode;
	/**
     * 退房接受信息手机号
     */
	@ApiModelProperty(value = "退房接受信息手机号")
	@TableField("check_out_phone")
	private String checkOutPhone;
	/**
     * 是否开启预约发票
     */
	@ApiModelProperty(value = "是否开启预约发票")
	@TableField("if_invoice")
	private String ifInvoice;
	/**
     * 需发票退房成功提示
     */
	@ApiModelProperty(value = "需发票退房成功提示")
	@TableField("neek_prompt")
	private String neekPrompt;
	/**
     * 无需发票退房成功提示
     */
	@ApiModelProperty(value = "无需发票退房成功提示")
	@TableField("unneek_prompt")
	private String unneekPrompt;

	@Override
	protected Serializable pkVal() {
		return this.id;
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

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getIfReserveMan() {
		return ifReserveMan;
	}

	public void setIfReserveMan(Integer ifReserveMan) {
		this.ifReserveMan = ifReserveMan;
	}

	public Integer getIfReservePhone() {
		return ifReservePhone;
	}

	public void setIfReservePhone(Integer ifReservePhone) {
		this.ifReservePhone = ifReservePhone;
	}

	public Integer getIfRemark() {
		return ifRemark;
	}

	public void setIfRemark(Integer ifRemark) {
		this.ifRemark = ifRemark;
	}

	public Integer getPayMode() {
		return payMode;
	}

	public void setPayMode(Integer payMode) {
		this.payMode = payMode;
	}

	public Integer getIfSms() {
		return ifSms;
	}

	public void setIfSms(Integer ifSms) {
		this.ifSms = ifSms;
	}

	public String getSmsPhone() {
		return smsPhone;
	}

	public void setSmsPhone(String smsPhone) {
		this.smsPhone = smsPhone;
	}

	public Integer getIfCheckOut() {
		return ifCheckOut;
	}

	public void setIfCheckOut(Integer ifCheckOut) {
		this.ifCheckOut = ifCheckOut;
	}

	public Integer getIfFood() {
		return ifFood;
	}

	public void setIfFood(Integer ifFood) {
		this.ifFood = ifFood;
	}

	public Integer getIfBulletin() {
		return ifBulletin;
	}

	public void setIfBulletin(Integer ifBulletin) {
		this.ifBulletin = ifBulletin;
	}

	public String getBulletin() {
		return bulletin;
	}

	public void setBulletin(String bulletin) {
		this.bulletin = bulletin;
	}

	public Integer getIfRemnantRoom() {
		return ifRemnantRoom;
	}

	public void setIfRemnantRoom(Integer ifRemnantRoom) {
		this.ifRemnantRoom = ifRemnantRoom;
	}

	public Integer getIfContinue() {
		return ifContinue;
	}

	public void setIfContinue(Integer ifContinue) {
		this.ifContinue = ifContinue;
	}

	public Integer getIfConfirmInfo() {
		return ifConfirmInfo;
	}

	public void setIfConfirmInfo(Integer ifConfirmInfo) {
		this.ifConfirmInfo = ifConfirmInfo;
	}

	public Integer getIfBreakfast() {
		return ifBreakfast;
	}

	public void setIfBreakfast(Integer ifBreakfast) {
		this.ifBreakfast = ifBreakfast;
	}

	public Integer getBreakfastQuantity() {
		return breakfastQuantity;
	}

	public void setBreakfastQuantity(Integer breakfastQuantity) {
		this.breakfastQuantity = breakfastQuantity;
	}

	public Integer getIfActivityPrices() {
		return ifActivityPrices;
	}

	public void setIfActivityPrices(Integer ifActivityPrices) {
		this.ifActivityPrices = ifActivityPrices;
	}

	public Integer getIfFreeDeposit() {
		return ifFreeDeposit;
	}

	public void setIfFreeDeposit(Integer ifFreeDeposit) {
		this.ifFreeDeposit = ifFreeDeposit;
	}

	public Integer getIfLastCheckOut() {
		return ifLastCheckOut;
	}

	public void setIfLastCheckOut(Integer ifLastCheckOut) {
		this.ifLastCheckOut = ifLastCheckOut;
	}

	public Integer getIfGroupBuy() {
		return ifGroupBuy;
	}

	public void setIfGroupBuy(Integer ifGroupBuy) {
		this.ifGroupBuy = ifGroupBuy;
	}

	public Integer getIfSpike() {
		return ifSpike;
	}

	public void setIfSpike(Integer ifSpike) {
		this.ifSpike = ifSpike;
	}

	public Integer getIfHour() {
		return ifHour;
	}

	public void setIfHour(Integer ifHour) {
		this.ifHour = ifHour;
	}

	public Integer getIfSpecial() {
		return ifSpecial;
	}

	public void setIfSpecial(Integer ifSpecial) {
		this.ifSpecial = ifSpecial;
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

	public String getFoodPayMode() {
		return foodPayMode;
	}

	public void setFoodPayMode(String foodPayMode) {
		this.foodPayMode = foodPayMode;
	}

	public String getCheckOutPhone() {
		return checkOutPhone;
	}

	public void setCheckOutPhone(String checkOutPhone) {
		this.checkOutPhone = checkOutPhone;
	}

	public String getIfInvoice() {
		return ifInvoice;
	}

	public void setIfInvoice(String ifInvoice) {
		this.ifInvoice = ifInvoice;
	}

	public String getNeekPrompt() {
		return neekPrompt;
	}

	public void setNeekPrompt(String neekPrompt) {
		this.neekPrompt = neekPrompt;
	}

	public String getUnneekPrompt() {
		return unneekPrompt;
	}

	public void setUnneekPrompt(String unneekPrompt) {
		this.unneekPrompt = unneekPrompt;
	}

	@Override
	public String toString() {
		return "TErpHotel [id=" + id + ", busId=" + busId + ", shopId=" + shopId + ", name=" + name + ", introduction="
				+ introduction + ", phone=" + phone + ", address=" + address + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", ifReserveMan=" + ifReserveMan + ", ifReservePhone=" + ifReservePhone
				+ ", ifRemark=" + ifRemark + ", payMode=" + payMode + ", ifSms=" + ifSms + ", smsPhone=" + smsPhone
				+ ", ifCheckOut=" + ifCheckOut + ", ifFood=" + ifFood + ", ifBulletin=" + ifBulletin + ", bulletin="
				+ bulletin + ", ifRemnantRoom=" + ifRemnantRoom + ", ifContinue=" + ifContinue + ", ifConfirmInfo="
				+ ifConfirmInfo + ", ifBreakfast=" + ifBreakfast + ", breakfastQuantity=" + breakfastQuantity
				+ ", ifActivityPrices=" + ifActivityPrices + ", ifFreeDeposit=" + ifFreeDeposit + ", ifLastCheckOut="
				+ ifLastCheckOut + ", ifGroupBuy=" + ifGroupBuy + ", ifSpike=" + ifSpike + ", ifHour=" + ifHour
				+ ", ifSpecial=" + ifSpecial + ", creator=" + creator + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", foodPayMode=" + foodPayMode + ", checkOutPhone=" + checkOutPhone + ", ifInvoice="
				+ ifInvoice + ", neekPrompt=" + neekPrompt + ", unneekPrompt=" + unneekPrompt + "]";
	}

}
