package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Api(description = "酒店会员设置")
public class HotelMemberSettingVo {

    @ApiModelProperty("酒店ID")
    private Integer hotelId;

    @ApiModelProperty("会员等级 目前只有4个等级 1,2,3,4")
    private Integer vipLevel;

    @ApiModelProperty("会员 最晚退房时间(默认退房时间均为离店当天14点)")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Date vipCheckOut;

    @ApiModelProperty("是否免押金  0 是 1 否")
    private Integer freeDepositEnable;
    
}
