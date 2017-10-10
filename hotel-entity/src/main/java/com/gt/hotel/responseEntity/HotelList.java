package com.gt.hotel.responseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
<<<<<<< HEAD

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
	
	@ApiModelProperty("图片地址")
	private String imageurl;
	
	public HotelList() {
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

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	@Override
	public String toString() {
		return "HotelList [hotelId=" + hotelId + ", name=" + name + ", tel=" + tel + ", addr=" + addr + ", imageurl="
				+ imageurl + "]";
	}
=======
import lombok.Data;

/**
 * 新增酒店 - 酒店列表
 *
 * @author ReverieNight@Foxmail.com
 */
@Data
@Api( description = "新增酒店 - 酒店列表" )
public class HotelList {

    /**
     * 酒店ID
     */
    @ApiModelProperty( "酒店ID" )
    private Integer hotelId;

    /**
     * 酒店名称
     */
    @ApiModelProperty( "酒店名称" )
    private String name;

    /**
     * 联系电话
     */
    @ApiModelProperty( "联系电话" )
    private String tel;

    /**
     * 地址
     */
    @ApiModelProperty( "地址" )
    private String addr;

    @ApiModelProperty( "图片地址" )
    private String imageurl;

>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6

}
