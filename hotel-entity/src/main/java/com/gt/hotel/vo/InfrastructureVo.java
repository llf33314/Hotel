package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 基础设施 包含 酒店 客房
 * @author Administrator
 *
 */
@Data
@Api(description = "基础设施 包含 酒店 客房")
public class InfrastructureVo {


    private Integer id;

    @ApiModelProperty("名称")
    private String  name;

    @ApiModelProperty("图标地址")
    private String  iconUrl;

    @ApiModelProperty("模块")
    private String  module;

}
