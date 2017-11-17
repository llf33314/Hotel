package com.gt.hotel.other;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(description = "新增酒店 - 门店列表")
public class HotelShopInfo {

    @ApiModelProperty("门店ID")
    private Integer shopid;

    @ApiModelProperty("门店名称")
    private String name;

    @ApiModelProperty("联系电话")
    private String tel;

    @ApiModelProperty("地址")
    private String addr;

    @ApiModelProperty("图片地址")
    private String image;

}
