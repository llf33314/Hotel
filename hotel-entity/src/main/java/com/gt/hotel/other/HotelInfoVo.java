package com.gt.hotel.other;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 酒店信息+门店信息
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/11/29
 * @since
 */
@Data
@Api("酒店与门店信息")
public class HotelInfoVo {

    @ApiModelProperty("门店ID")
    private Integer shopId;

    @ApiModelProperty("酒店ID")
    private Integer hotelId;

    @ApiModelProperty("酒店名称")
    private String name;

    @ApiModelProperty("酒店联系电话")
    private String phone;

    @ApiModelProperty("酒店地址")
    private String address;

    @ApiModelProperty("图片地址")
    private String logo;

    @ApiModelProperty("经度")
    private Double longitude;

    @ApiModelProperty("纬度")
    private Double latitude;

    @ApiModelProperty("酒店描述")
    private String desc;

    ///// 门店信息 /////

    @ApiModelProperty("门店名称")
    private String shopName;

    @ApiModelProperty("门店联系电话")
    private String shopTel;

    @ApiModelProperty("门店地址")
    private String shopAddress;

    @ApiModelProperty("图片地址")
    private String shopImage;


}
