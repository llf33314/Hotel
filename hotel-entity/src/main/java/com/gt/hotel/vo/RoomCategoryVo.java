package com.gt.hotel.vo;

import java.util.List;

import com.gt.hotel.entity.TRoom;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api( description = "房型列表对象" )
@Data
public class RoomCategoryVo {
    @ApiModelProperty( "酒店ID" )
    private Integer hotelId;

    @ApiModelProperty( "房型ID" )
    private Integer roomCategoryId;

    @ApiModelProperty( "房间类型" )
    private String name;

    @ApiModelProperty( "门市价" )
    private Integer rackRate;

    @ApiModelProperty( "折扣率" )
    private Integer discount;

    @ApiModelProperty( "周末价" )
    private Integer weekendFare;

    @ApiModelProperty( "押金" )
    private Integer deposit;

    @ApiModelProperty( "房间总数" )
    private Integer roomCount;

    @ApiModelProperty("房间集合")
    private List< TRoom > tRoomList;

    @ApiModelProperty("图片集合")
    private List<FileRecordVo> images;
    
    @ApiModelProperty("设施 关系-数值 集合")
    private List<InfrastructureRelationVo> infrastructureRelations;
}