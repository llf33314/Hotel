package com.gt.hotel.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;

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
@ApiModel(value = "TErpHotelERPSet")
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
	@ApiModelProperty(value = "是否开启早餐券")
	private Integer ifBreakfast;
    /**
     * 默认早餐券数量
     */
	@ApiModelProperty(value = "默认早餐券数量")
	private Integer breakfastQuantity;
    /**
     * 是否开启会员免押金
     */
	@ApiModelProperty(value = "是否开启会员免押金")
	private Integer ifFreeDeposit;
    /**
     * 是否开启会员最晚退房时间
     */
	@ApiModelProperty(value = "是否开启会员最晚退房时间")
	private Integer ifLastCheckOut;
	
	@ApiModelProperty(value = "logo url")
	private String logo;
	
	@ApiModelProperty(value = "会员免押金集合")
	private List<TErpHotelMemberDepositRelation> deposits;

	@ApiModelProperty(value = "会员退房集合")
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
