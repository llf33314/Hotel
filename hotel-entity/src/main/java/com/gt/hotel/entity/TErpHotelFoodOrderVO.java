package com.gt.hotel.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * ERP酒店-餐饮-订单
 * </p>
 *
 * @author 
 * @since 2017-08-17
 */
@ApiModel("TErpHotelFoodOrderVO")
@Data
public class TErpHotelFoodOrderVO extends TErpHotelFoodOrder {

	private static final long serialVersionUID = -8929620571340634735L;

	@ApiModelProperty("酒店名称")
    String hotelName;
    
	@ApiModelProperty("公司名称")
    String companyName;
	
	@ApiModelProperty("菜品提供方 1:本酒店 2:合作方")
	Integer provide_from;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getProvide_from() {
		return provide_from;
	}

	public void setProvide_from(Integer provide_from) {
		this.provide_from = provide_from;
	}

	@Override
	public String toString() {
		return super.toString() + "TErpHotelFoodOrderVO [hotelName=" + hotelName + ", companyName=" + companyName + "]";
	}
    
}
