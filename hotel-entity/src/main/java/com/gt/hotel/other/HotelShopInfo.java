package com.gt.hotel.other;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(description = "新增酒店 - 门店列表")
public class HotelShopInfo {

    @ApiModelProperty("门店ID")
    private Integer shopId;

    @ApiModelProperty("门店名称")
    private String name;

    @ApiModelProperty("联系电话")
    private String tel;

    @ApiModelProperty("地址")
    private String addr;

    @ApiModelProperty("图片地址")
    private String image;

    @ApiModelProperty("酒店ID")
    private Integer hotelId;
    
    @ApiModelProperty("酒店名称")
    private String hotelName;
    
    @ApiModelProperty("酒店logo")
    private String logo;
    
}
