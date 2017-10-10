package com.gt.hotel.requestEntity;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * 房型列表请求对象
 * @author ReverieNight@Foxmail.com
 * @date 2017年10月9日 下午5:31:34
 */
@Api(description = "房型列表")
public class ReqRoomCategory extends HotelPage {
	
	@ApiModelProperty("酒店ID")
	@NotNull(message = "酒店ID不能为空")
	private Integer hotelid;

	public Integer getHotelid() {
		return hotelid;
	}

	public void setHotelid(Integer hotelid) {
		this.hotelid = hotelid;
	}

	@Override
	public String toString() {
		return "ReqRoomCategory [hotelid=" + hotelid + "]";
	}
	
	
}
