package com.gt.hotel.responseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新增酒店 - 酒店列表
 * @author ReverieNight@Foxmail.com
 *
 */
@Api(description = "新增酒店 - 酒店列表")
public class HotelList {

	/**
	 * 酒店ID
	 */
	@ApiModelProperty("酒店ID")
	private Integer hotelId;
	
	/**
	 * 酒店名称
	 */
	@ApiModelProperty("酒店名称")
	private String name;
	
	/**
	 * 联系电话
	 */
	@ApiModelProperty("联系电话")
	private String tel;
	
	/**
	 * 地址
	 */
	@ApiModelProperty("地址")
	private String addr;
	
	public HotelList() {
	}
	
	public HotelList(Integer hotelId, String name, String tel, String addr) {
		super();
		this.hotelId = hotelId;
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
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

	@Override
	public String toString() {
		return "HotelList [hotelId=" + hotelId + ", name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
	}
	
}
