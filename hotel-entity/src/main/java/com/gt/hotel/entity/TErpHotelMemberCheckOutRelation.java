package com.gt.hotel.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 酒店 - 会员最晚退房设置
 * </p>
 *
 * @author 
 * @since 2017-08-08
 */
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_member_check_out_relation")
public class TErpHotelMemberCheckOutRelation extends Model<TErpHotelMemberCheckOutRelation> {

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
     * 最晚退房时间
     */
	@TableField("last_check_out")
	private Date lastCheckOut;


	@Override
	public String toString() {
		return "TErpHotelMemberCheckOutRelation{" +
			"hotelId=" + hotelId +
			", vipLevel=" + vipLevel +
			", lastCheckOut=" + lastCheckOut +
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


	public Date getLastCheckOut() {
		return lastCheckOut;
	}


	public void setLastCheckOut(Date lastCheckOut) {
		this.lastCheckOut = lastCheckOut;
	}
	
	
}
