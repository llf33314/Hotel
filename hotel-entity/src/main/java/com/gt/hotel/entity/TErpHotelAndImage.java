package com.gt.hotel.entity;

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
@ApiModel(value = "仅供参考(返回数据肯定不是这个样子的)")
@Data
public class TErpHotelAndImage extends TErpHotel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "门店名")
    String businessName;

    @ApiModelProperty(value = "门店电话")
    String telephone;
    
    @ApiModelProperty(value = "门店地址")
    String businessAddress;
    
    @ApiModelProperty(value = "logo名")
    String logoName;
    @ApiModelProperty(value = "logo url")
    String logoUrl;

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	@Override
	public String toString() {
		return "TErpHotelAndImage [businessName=" + businessName + ", telephone=" + telephone + ", businessAddress="
				+ businessAddress + ", logoName=" + logoName + ", logoUrl=" + logoUrl + "]";
	}

}
