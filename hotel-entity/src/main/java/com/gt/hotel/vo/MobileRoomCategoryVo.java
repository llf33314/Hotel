package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Api(description = "房型列表对象")
@Data
public class MobileRoomCategoryVo {
    @ApiModelProperty("酒店ID")
    private Integer hotelId;

    @ApiModelProperty("房型ID")
    private Integer id;

    @ApiModelProperty("房间类型")
    private String name;

    @ApiModelProperty("门市价")
    private Integer rackRate;

    @ApiModelProperty("是否开启周末价 0 开启 1 禁用，即显示门市价即可")
    private Integer weekendFareEnable;

    @ApiModelProperty("周末价")
    private Integer weekendFare;

    @ApiModelProperty("押金")
    private Integer deposit;

    @ApiModelProperty("房间总数")
    private Integer roomCount;

    @ApiModelProperty("是否开启早餐券 0 开启 1 关闭")
    private Integer breakfastEnable;

    @ApiModelProperty("早餐数量")
    private Integer breakfastQuantity;

    @ApiModelProperty(value = "简要说明")
    private String desc;
    
    @ApiModelProperty(value = "日历价")
    private Integer calenderPrice;
    
    @ApiModelProperty(value = "订单已订房间数量")
    private Integer orderCount;
    
    @ApiModelProperty(value = "是否显示剩余房间数 默认 1 关闭 0 开启")
    private Integer remnantRoomEnable;

    @ApiModelProperty("显示价格")
    private Integer displayPrice;
    
    @ApiModelProperty("图片集合")
    private List<FileRecordVo> images;

    @ApiModelProperty("设施 关系-数值 集合")
    private List<InfrastructureRelationVo> infrastructureRelations;
}