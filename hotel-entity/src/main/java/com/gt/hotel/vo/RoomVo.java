package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 房间对象
 *
 * @author ReverieNight@Foxmail.com
 * @date 2017年10月13日 上午11:12:09
 */
@Api(description = "房间对象")
@Data
public class RoomVo {

    @ApiModelProperty("房间ID")
    private Integer id;

    @ApiModelProperty("房型ID")
    private Integer categoryId;

    @ApiModelProperty("房型名称")
    private String categoryName;

    @ApiModelProperty("门市价 ")
    private Integer rackRate;

    @ApiModelProperty("客房编号")
    private String roomNum;

    @ApiModelProperty("楼层")
    private String  floor;
    /**
     * 1	空房
     * 2	锁定
     * 3	在住
     * 4	清洁
     * 5	维护
     */
    @ApiModelProperty("房间状态 字典值 1\t空房,2\t锁定,3\t在住,4\t清洁,5\t维护")
    private Integer status;

    @ApiModelProperty("0 正常 1 禁用 2 删除")
    private Integer markModified;

    @ApiModelProperty("创建者ID")
    private Integer createdBy;

    @ApiModelProperty("创建时间")
    private Date createdAt;

    @ApiModelProperty("最后修改人 ID")
    private Integer updatedBy;

    @ApiModelProperty("最后修改时间")
    private Date updatedAt;

    @ApiModelProperty("押金")
    private Integer deposit;

}
