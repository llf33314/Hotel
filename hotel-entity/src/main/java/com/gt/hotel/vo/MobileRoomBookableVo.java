package com.gt.hotel.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 客房可预约对象
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/12/11
 */
@Data
@ApiModel("移动端可预约客房")
public class MobileRoomBookableVo {

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

    @ApiModelProperty("已预约的客房数")
    private Integer reserved;

    @ApiModelProperty("房间总数")
    private Integer roomCount;

    @ApiModelProperty("是否开启早餐券 0 开启 1 关闭")
    private Integer breakfastEnable;

    @ApiModelProperty("早餐数量")
    private Integer breakfastQuantity;

    @ApiModelProperty("图片集合")
    private List<FileRecordVo> images;

    @ApiModelProperty("设施 关系-数值 集合")
    private List<InfrastructureRelationVo> infrastructureRelations;
}
