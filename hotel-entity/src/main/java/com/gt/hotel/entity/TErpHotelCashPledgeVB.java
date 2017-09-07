package com.gt.hotel.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * ERP酒店-押金
 * </p>
 *
 * @author 
 * @since 2017-09-05
 */
@ApiModel(description = "押金")
public class TErpHotelCashPledgeVB extends TErpHotelCashPledge {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("入住时间")
    private Date check_in_time;
    
    @ApiModelProperty("离店时间")
    private Date check_out_time;
    
    @ApiModelProperty("酒店名称")
    private String hotelName;

	public Date getCheck_in_time() {
		return check_in_time;
	}

	public void setCheck_in_time(Date check_in_time) {
		this.check_in_time = check_in_time;
	}

	public Date getCheck_out_time() {
		return check_out_time;
	}

	public void setCheck_out_time(Date check_out_time) {
		this.check_out_time = check_out_time;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
    
    
}
