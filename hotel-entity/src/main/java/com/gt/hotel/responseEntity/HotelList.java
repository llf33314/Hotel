package com.gt.hotel.responseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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


}
