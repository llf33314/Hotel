package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@Api(description = "客房长包房")
public class RoomPermanentVo {

    @ApiModelProperty(value = "长包房ID")
    private Integer id;

    @ApiModelProperty(value = "房型ID")
    private Integer categoryId;

    @ApiModelProperty(value = "酒店ID")
    private Integer hotelId;

    @ApiModelProperty(value = "规则名称")
    private String ruleName;

    @ApiModelProperty(value = "入住天数")
    private Integer checkInDay;

    @ApiModelProperty(value = "折扣 默认：1 无折扣")
    private Integer discount;

    @ApiModelProperty(value = "押金")
    private Integer deposit;

    @ApiModelProperty(value = "0 正常 1 禁用 2 删除")
    private Integer markModified;

    @ApiModelProperty(value = "创建者ID")
    private Integer createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "最后修改人 ID")
    private Integer updatedBy;

    @ApiModelProperty(value = "最后修改时间")
    private Date updatedAt;

    @ApiModelProperty(value = "门市价")
    private Integer rackRate;
    
    @ApiModelProperty(value = "房型名称")
    private String categoryName;
}
