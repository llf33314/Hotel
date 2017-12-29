package com.gt.hotel.vo.erp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <pre>
 * 1.0.0 2017/12/28 zhangmz -
 * ---------------------------------------------------------------------------------------
 * Version Date Author Description
 * ---------------------------------------------------------------------------------------
 * </pre>
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017-12-28
 * @since 1.0.0
 */
@Data
public class ErpRoomVo {

    @ApiModelProperty("房间ID")
    private Integer id;

    @ApiModelProperty("房型ID")
    private Integer categoryId;

    @ApiModelProperty("门市价")
    private Integer rackRate;

    @ApiModelProperty("客房编号")
    private String roomNum;

    @ApiModelProperty("楼层")
    private String  floor;

    @ApiModelProperty("房间状态 字典值 1\t空房,2\t锁定,3\t在住,4\t清洁,5\t维护")
    private Integer status;

    @ApiModelProperty("0 正常 1 禁用 2 删除")
    private Integer markModified;

    @ApiModelProperty("押金")
    private Integer deposit;

    @ApiModelProperty("今日预离 0 否  1 是")
    private Integer departure;
}
