package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(description = "系统的字典数据表")
public class SysDictionaryVo {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("字典类型ID")
    private Integer dictTypeId;

    @ApiModelProperty("字典编码")
    private Integer dictCode;

    @ApiModelProperty("字典中文名称(例如：男女，比如状态，可以放在字典里，作为查看依据)")
    private String dictCnName;

    @ApiModelProperty("字典英文名称 全大写")
    private String dictEnName;

}
