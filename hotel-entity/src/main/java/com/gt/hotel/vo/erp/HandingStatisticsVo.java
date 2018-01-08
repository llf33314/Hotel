package com.gt.hotel.vo.erp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 挂账列表统计对象
 * @author Reverien9@gmail.com
 * 2018年1月8日 上午11:14:39
 */
@Api(description = "挂账列表统计对象")
@Data
public class HandingStatisticsVo {

    @ApiModelProperty("总挂账人数")
    private Integer totalCustomer;
    
    @ApiModelProperty("已还款")
    private Integer handingPrice;
    
    @ApiModelProperty("未还款")
    private Integer remandPrice;
    
    @ApiModelProperty("总挂账金额")
    private Integer totalPrice;
    
}