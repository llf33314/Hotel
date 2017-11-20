package com.gt.hotel.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * 预订
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/11/07
 * @since 1.0
 */
@Data
public class BookingVo {

    @ApiModelProperty("房型ID")
    private Integer categoryId;

    @ApiModelProperty("房型名称")
    private String  categoryName;

    @ApiModelProperty("支付方式(1：在线支付 | 2：到店支付 | 3：1&2)  默认 在线支付")
    private Integer payMode;



}
