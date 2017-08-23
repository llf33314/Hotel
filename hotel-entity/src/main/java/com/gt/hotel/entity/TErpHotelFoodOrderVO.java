package com.gt.hotel.entity;

import lombok.Data;

/**
 * <p>
 * ERP酒店-餐饮-订单
 * </p>
 *
 * @author 
 * @since 2017-08-17
 */
@Data
public class TErpHotelFoodOrderVO extends TErpHotelFoodOrder {

	private static final long serialVersionUID = -8929620571340634735L;

    String hotelName;
    
    String companyName;

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

	@Override
	public String toString() {
		return "TErpHotelFoodOrderVO [hotelName=" + hotelName + ", companyName=" + companyName + "]";
	}
    
}
