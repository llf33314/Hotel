package com.gt.hotel.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;

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
public class TErpHotelERPSet extends Model<TErpHotelERPSet> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	private Integer hotelId;
    
    /**
     * 是否开启早餐券
     */
	private Integer ifBreakfast;
    /**
     * 默认早餐券数量
     */
	private Integer breakfastQuantity;
    /**
     * 是否开启会员免押金
     */
	private Integer ifFreeDeposit;
    /**
     * 是否开启会员最晚退房时间
     */
	private Integer ifLastCheckOut;
	
	private String logo;
	
	private List<TErpHotelMemberDepositRelation> deposits;

	private List<TErpHotelMemberCheckOutRelation> checkOuts;
	
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<TErpHotelMemberDepositRelation> getDeposits() {
		return deposits;
	}

	public void setDeposits(List<TErpHotelMemberDepositRelation> deposits) {
		this.deposits = deposits;
	}

	public List<TErpHotelMemberCheckOutRelation> getCheckOuts() {
		return checkOuts;
	}

	public void setCheckOuts(List<TErpHotelMemberCheckOutRelation> checkOuts) {
		this.checkOuts = checkOuts;
	}

}
