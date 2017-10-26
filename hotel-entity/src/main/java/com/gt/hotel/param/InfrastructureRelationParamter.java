package com.gt.hotel.param;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api("房间设施关系ID")
public class InfrastructureRelationParamter {
    @ApiModelProperty("设施ID")
    private Integer infrastructureId;

//	@ApiModelProperty("房型ID")
//	private Integer referenceId;

    @ApiModelProperty("数值")
    private String displayValue;
}
