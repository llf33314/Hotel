package com.gt.hotel.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 基础设施表关系(酒店/房型 关联的基础属性都在这里)
 * </p>
 *
 * @author
 * @since 2017-10-11
 */
@Data
public class InfrastructureRelationVo {

    @ApiModelProperty("ID")
    private Integer id;
    /**
     * 基础设施表
     */
    @ApiModelProperty("基础设施表")
    private Integer infrastructureId;
    /**
     * 模块类型 冗余参数 酒店 客房 …. hotel room
     */
    @ApiModelProperty("模块类型 冗余参数 酒店 客房 …. hotel room")
    private String  module;
    /**
     * 引用ID，如果 module_type = room 即保存 room_id 具体 按业务模块类型 定义
     */
    @ApiModelProperty("引用ID，如果 module_type = room 即保存 room_id 具体 按业务模块类型 定义")
    private Integer referenceId;
    /**
     * 创建者ID
     */
    @ApiModelProperty("创建者ID")
    private Integer createdBy;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date    createdAt;
    /**
     * 最后修改人 ID
     */
    @ApiModelProperty("最后修改人 ID")
    private Integer updatedBy;
    /**
     * 最后修改时间
     */
    @ApiModelProperty("最后修改时间")
    private Date    updatedAt;
    /**
     * 展示值
     */
    @ApiModelProperty("展示值")
    private String  displayValue;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图标地址")
    private String iconUrl;
}
