package com.gt.hotel.vo.erp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <pre>
 * 1.0.0 2018/01/02 zhangmz -
 * ---------------------------------------------------------------------------------------
 * Version Date Author Description
 * 客房状态统计
 * ---------------------------------------------------------------------------------------
 * </pre>
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2018-01-02
 * @since 1.0.0
 */
@Api(description = "房间对象")
@Data
public class ErpRoomStatusVo {

    @ApiModelProperty("空房数量")
    private int vacantRoomCount;

    @ApiModelProperty("锁定客房数量")
    private int lockCount;

    @ApiModelProperty("入住客房数量")
    private int checkInCount;

    @ApiModelProperty("清洁客房数量")
    private int cleanCount;

    @ApiModelProperty("维护客房数量")
    private int maintain;

    @ApiModelProperty(value = "已预约数量",notes = "当前酒店预约当日入住客房数量")
    private int bookingCount;
}