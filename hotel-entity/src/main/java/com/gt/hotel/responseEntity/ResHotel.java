package com.gt.hotel.responseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 后台 - 酒店响应对象
 * @author ReverieNight@Foxmail.com
 * @date 2017年10月10日 下午5:01:42
 */
@Api(description = "后台 - 酒店响应对象")
public class ResHotel {

	@Data
	@Api( description = "新增酒店 - 酒店列表" )
	public static class HotelList {
	    @ApiModelProperty( "酒店ID" )
	    private Integer hotelId;
	    
	    @ApiModelProperty( "酒店名称" )
	    private String name;
	    
	    @ApiModelProperty( "联系电话" )
	    private String tel;

	    @ApiModelProperty( "地址" )
	    private String addr;

	    @ApiModelProperty( "图片地址" )
	    private String imageurl;
	}
	
	@Data
	@Api( description = "新增酒店 - 门店列表" )
	public static class HotelShopInfo {

	    @ApiModelProperty( "门店ID" )
	    private Integer shopid;
	    
	    @ApiModelProperty( "门店名称" )
	    private String  name;
	    
	    @ApiModelProperty( "联系电话" )
	    private String  tel;
	    
	    @ApiModelProperty( "地址" )
	    private String  addr;
	    
	    @ApiModelProperty( "图片地址" )
	    private String  image;

	}
	
}
