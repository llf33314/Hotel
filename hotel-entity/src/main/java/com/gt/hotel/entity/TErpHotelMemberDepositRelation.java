package com.gt.hotel.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 酒店 - 会员免押设置
 * </p>
 *
 * @author 
 * @since 2017-08-08
 */
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_member_deposit_relation")
public class TErpHotelMemberDepositRelation extends Model<TErpHotelMemberDepositRelation> {

    /**
     * 酒店ID
     */
	@TableId
	@TableField("hotel_id")
	private Integer hotelId;
    /**
     * 会员等级
     */
	@TableField("vip_level")
	private Integer vipLevel;
    /**
     * 是否免押金
     */
	@TableField("if_free_deposit")
	private Integer ifFreeDeposit;


	@Override
	public String toString() {
		return "TErpHotelMemberDepositRelation{" +
			"hotelId=" + hotelId +
			", vipLevel=" + vipLevel +
			", ifFreeDeposit=" + ifFreeDeposit +
			"}";
	}


	@Override
	protected Serializable pkVal() {
		return this.hotelId;
	}


	public Integer getHotelId() {
		return hotelId;
	}


	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}


	public Integer getVipLevel() {
		return vipLevel;
	}


	public void setVipLevel(Integer vipLevel) {
		this.vipLevel = vipLevel;
	}


	public Integer getIfFreeDeposit() {
		return ifFreeDeposit;
	}


	public void setIfFreeDeposit(Integer ifFreeDeposit) {
		this.ifFreeDeposit = ifFreeDeposit;
	}
	
	
}
