package com.gt.hotel.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 授权人-功能-关系
 * </p>
 *
 * @author 
 * @since 2017-08-11
 */
@ApiModel(value = "TErpHotelAuthorizationVS")
public class TErpHotelAuthorizationVS extends TErpHotelAuthorization {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("门店名")
    String shopName;
    
    @ApiModelProperty("用户名")
    String accountName;
    
    @ApiModelProperty("用户电话")
    String accountPhone;
    
    @ApiModelProperty("授权功能")
    String funcs;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPhone() {
		return accountPhone;
	}

	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}

	public String getFuncs() {
		return funcs;
	}

	public void setFuncs(String funcs) {
		this.funcs = funcs;
	}

	@Override
	public String toString() {
		return "TErpHotelAuthorizationVS [shopName=" + shopName + ", accountName=" + accountName + ", accountPhone="
				+ accountPhone + ", funcs=" + funcs + "]";
	}
    
}
