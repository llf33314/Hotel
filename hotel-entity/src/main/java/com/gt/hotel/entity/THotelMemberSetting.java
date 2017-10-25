package com.gt.hotel.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 酒店会员设置
 * </p>
 *
 * @author 
 * @since 2017-10-18
 */
@Data
@Accessors(chain = true)
public class THotelMemberSetting extends Model<THotelMemberSetting> {

    private static final long serialVersionUID = 1L;

    /**
     * 酒店ID
     */
	private Integer hotelId;
    /**
     * 会员等级 目前只有4个等级 1,2,3,4
     */
	private Integer vipLevel;
    /**
     * 会员 最晚退房时间(需要默认退房时间均为离店当天14点)
     */
	private Date vipCheckOut;
    /**
     * 是否免押金  0 是 1 否
     */
	private Integer freeDepositEnable;


	@Override
	protected Serializable pkVal() {
		return this.hotelId;
	}

}