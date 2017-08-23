package com.gt.hotel.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;

import lombok.Data;

/**
 * <p>
 * ERP酒店主表
 * </p>
 *
 * @author 
 * @since 2017-08-07
 */
@Data
public class TErpHotelMobileSet extends Model<TErpHotelMobileSet> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	private Integer id;
	/**
     * 是否收集预约人
     */
	@TableField("if_reserve_man")
	private Integer ifReserveMan;
    /**
     * 是否收集预约电话
     */
	@TableField("if_reserve_phone")
	private Integer ifReservePhone;
    /**
     * 是否收集备注
     */
	@TableField("if_remark")
	private Integer ifRemark;
	/**
     * 支付方式(1：在线支付 | 2：到店支付 | 3：1&2)
     */
	@TableField("pay_mode")
	private Integer payMode;
    /**
     * 是否开启短信通知
     */
	@TableField("if_sms")
	private Integer ifSms;
    /**
     * 接受信息手机号
     */
	@TableField("sms_phone")
	private String smsPhone;
    /**
     * 是否开启一键退房
     */
	@TableField("if_check_out")
	private Integer ifCheckOut;
    /**
     * 是否开启餐饮
     */
	@TableField("if_food")
	private Integer ifFood;
    /**
     * 是否开启公告
     */
	@TableField("if_bulletin")
	private Integer ifBulletin;
    /**
     * 公告
     */
	private String bulletin;
    /**
     * 是否显示剩余房型
     */
	@TableField("if_remnant_room")
	private Integer ifRemnantRoom;
    /**
     * 是否显示一键续住
     */
	@TableField("if_continue")
	private Integer ifContinue;
    /**
     * 是否确认订单信息功能
     */
	@TableField("if_confirm_info")
	private Integer ifConfirmInfo;
	
	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	List<TErpHotelImage> images;
	
	List<TErpHotelInstallation> installations;

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

	@Override
	public String toString() {
		return "TErpHotelMobileSet [id=" + id + ", ifReserveMan=" + ifReserveMan + ", ifReservePhone=" + ifReservePhone
				+ ", ifRemark=" + ifRemark + ", payMode=" + payMode + ", ifSms=" + ifSms + ", smsPhone=" + smsPhone
				+ ", ifCheckOut=" + ifCheckOut + ", ifFood=" + ifFood + ", ifBulletin=" + ifBulletin + ", bulletin="
				+ bulletin + ", ifRemnantRoom=" + ifRemnantRoom + ", ifContinue=" + ifContinue + ", ifConfirmInfo="
				+ ifConfirmInfo + ", images=" + images + ", installations=" + installations + "]";
	}
	
	
}
