package com.gt.hotel.requestEntity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.gt.hotel.util.RequestBeanUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新增or更新酒店请求对象
 * @author ReverieNight@Foxmail.com
 *
 */
@Api(description = "新增酒店or更新请求对象")
public class HotelInsertOb {
	
	@ApiModelProperty("门店ID" + RequestBeanUtil.OPTIONAL)
	private Integer hotelId;
	
	@ApiModelProperty("门店ID")
	@NotNull(message = "门店ID不能为空")
	@Length(max = 11, message = "门店ID长度过长")
	private Integer shopid;
	
	@ApiModelProperty("酒店名称")
	@NotNull(message = "酒店名称不能为空")
	@Length(max = 60, message = "酒店名称长度过长")
	private String name;
	
	@ApiModelProperty("酒店电话")
	@NotNull(message = "酒店电话不能为空")
	@Length(max = 12, message = "酒店电话长度过长")
	private String tel;
	
	@ApiModelProperty("酒店地址")
	@NotNull(message = "酒店地址不能为空")
	@Length(max = 200, message = "酒店地址长度过长")
	private String addr;
	
	@ApiModelProperty("经度")
	@NotNull(message = "经度不能为空")
	private Double longitude;
	
	@ApiModelProperty("纬度")
	@NotNull(message = "纬度不能为空")
	private Double latitude;
	
	@ApiModelProperty("酒店介绍")
	@NotNull(message = "酒店介绍不能为空")
	@Length(max = 200, message = "酒店介绍长度过长")
	private String desc;

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	@Override
	public String toString() {
		return "HotelInsertOb [hotelId=" + hotelId + ", shopid=" + shopid + ", name=" + name + ", tel=" + tel
				+ ", addr=" + addr + ", longitude=" + longitude + ", latitude=" + latitude + ", desc=" + desc + "]";
	}

}
