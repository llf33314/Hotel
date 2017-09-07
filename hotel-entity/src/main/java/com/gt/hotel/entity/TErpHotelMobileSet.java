package com.gt.hotel.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * ERP酒店主表
 * </p>
 *
 * @author 
 * @since 2017-08-07
 */
@ApiModel("TErpHotelMobileSet")
@Data
public class TErpHotelMobileSet extends Model<TErpHotelMobileSet> {

    private static final long serialVersionUID = 1L;

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
    
    /**
     * ID
     */
	private Integer id;
	/**
     * 是否收集预约人
     */
	@ApiModelProperty("是否收集预约人")
	@TableField("if_reserve_man")
	private Integer ifReserveMan;
    /**
     * 是否收集预约电话
     */
	@ApiModelProperty("是否收集预约电话")
	@TableField("if_reserve_phone")
	private Integer ifReservePhone;
    /**
     * 是否收集备注
     */
	@ApiModelProperty("是否收集备注")
	@TableField("if_remark")
	private Integer ifRemark;
	/**
     * 支付方式(1：在线支付 | 2：到店支付 | 3：1&2)
     */
	@ApiModelProperty("支付方式(1：在线支付 | 2：到店支付 | 3：1&2)")
	@TableField("pay_mode")
	private Integer payMode;
    /**
     * 是否开启短信通知
     */
	@ApiModelProperty("是否开启短信通知")
	@TableField("if_sms")
	private Integer ifSms;
    /**
     * 接受信息手机号
     */
	@ApiModelProperty("接受信息手机号")
	@TableField("sms_phone")
	private String smsPhone;
    /**
     * 是否开启一键退房
     */
	@ApiModelProperty("是否开启一键退房")
	@TableField("if_check_out")
	private Integer ifCheckOut;
    /**
     * 是否开启餐饮
     */
	@ApiModelProperty("是否开启餐饮")
	@TableField("if_food")
	private Integer ifFood;
    /**
     * 是否开启公告
     */
	@ApiModelProperty("是否开启公告")
	@TableField("if_bulletin")
	private Integer ifBulletin;
    /**
     * 公告
     */
	@ApiModelProperty("公告")
	private String bulletin;
    /**
     * 是否显示剩余房型
     */
	@ApiModelProperty("是否显示剩余房型")
	@TableField("if_remnant_room")
	private Integer ifRemnantRoom;
    /**
     * 是否显示一键续住
     */
	@ApiModelProperty("是否显示一键续住")
	@TableField("if_continue")
	private Integer ifContinue;
    /**
     * 是否确认订单信息功能
     */
	@ApiModelProperty("是否确认订单信息功能")
	@TableField("if_confirm_info")
	private Integer ifConfirmInfo;
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

	List<TErpHotelImage> images;
	
	List<TErpHotelInstallation> installations;
	
	List<TErpHotelInvoiceRelation> invoices;
	
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

	public List<TErpHotelImage> getImages() {
		return images;
	}

	public void setImages(List<TErpHotelImage> images) {
		this.images = images;
	}

	public List<TErpHotelInstallation> getInstallations() {
		return installations;
	}

	public void setInstallations(List<TErpHotelInstallation> installations) {
		this.installations = installations;
	}

	public List<TErpHotelInvoiceRelation> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<TErpHotelInvoiceRelation> invoices) {
		this.invoices = invoices;
	}

	@Override
	public String toString() {
		return "TErpHotelMobileSet [id=" + id + ", ifReserveMan=" + ifReserveMan + ", ifReservePhone=" + ifReservePhone
				+ ", ifRemark=" + ifRemark + ", payMode=" + payMode + ", ifSms=" + ifSms + ", smsPhone=" + smsPhone
				+ ", ifCheckOut=" + ifCheckOut + ", ifFood=" + ifFood + ", ifBulletin=" + ifBulletin + ", bulletin="
				+ bulletin + ", ifRemnantRoom=" + ifRemnantRoom + ", ifContinue=" + ifContinue + ", ifConfirmInfo="
				+ ifConfirmInfo + ", ifGroupBuy=" + ifGroupBuy + ", ifSpike=" + ifSpike + ", ifHour=" + ifHour
				+ ", ifSpecial=" + ifSpecial + ", foodPayMode=" + foodPayMode + ", checkOutPhone=" + checkOutPhone
				+ ", ifInvoice=" + ifInvoice + ", neekPrompt=" + neekPrompt + ", unneekPrompt=" + unneekPrompt
				+ ", images=" + images + ", installations=" + installations + "]";
	}

}
