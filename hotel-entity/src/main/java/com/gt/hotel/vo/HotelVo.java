package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api( description = "新增酒店 - 酒店列表" )
public class HotelVo {
	@ApiModelProperty( "门店ID" )
    private Integer storeId;
	
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