package com.gt.hotel.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * ERP酒店主表
 * </p>
 *
 * @author 
 * @since 2017-08-07
 */
@ApiModel(description = "门店", value = "仅供参考")
public class TErpHotelShop {

	@ApiModelProperty(value = "门店ID")
	Integer id;
	
	@ApiModelProperty(value = "门店名")
    String businessName;

    @ApiModelProperty(value = "门店电话")
    String telephone;
    
    @ApiModelProperty(value = "门店地址")
    String businessAddress;
    
    @ApiModelProperty(value = "服务器图片地址")
    String localAddress;
    
    @ApiModelProperty(value = "微信图片地址")
    String wxAddress;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	public String getWxAddress() {
		return wxAddress;
	}

	public void setWxAddress(String wxAddress) {
		this.wxAddress = wxAddress;
	}

	@Override
	public String toString() {
		return "TErpHotelShop [id=" + id + ", businessName=" + businessName + ", telephone=" + telephone
				+ ", businessAddress=" + businessAddress + ", localAddress=" + localAddress + ", wxAddress=" + wxAddress
				+ "]";
	}

}
