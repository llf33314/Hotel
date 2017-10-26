package com.gt.hotel.vo;

import java.util.Date;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 房间对象
 * @author ReverieNight@Foxmail.com
 * @date 2017年10月13日 上午11:12:09
 */
@Api( description = "房间对象" )
@Data
public class RoomVo {

    @ApiModelProperty("房间ID")
    private Integer id;
    
    @ApiModelProperty("房型ID")
    private Integer categoryId;

    @ApiModelProperty("客房编号")
    private String  number;

    @ApiModelProperty("楼层")
    private String  floor;

    @ApiModelProperty("房间状态 字典值 TODO: 后续给出编号  -- 可定义为字典值：在住、空房、脏房、已预订、已锁定、维护")
    private String  status;

    @ApiModelProperty("0 正常 1 禁用 2 删除")
    private Integer markModified;

    @ApiModelProperty("创建者ID")
    private Integer createdBy;

    @ApiModelProperty("创建时间")
    private Date    createdAt;

    @ApiModelProperty("最后修改人 ID")
    private Integer updatedBy;

    @ApiModelProperty("最后修改时间")
    private Date    updatedAt;

}
