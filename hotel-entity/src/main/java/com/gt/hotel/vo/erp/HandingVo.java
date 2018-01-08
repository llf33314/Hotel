package com.gt.hotel.vo.erp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 挂账列表对象
 * @author Reverien9@gmail.com
 * 2018年1月8日 上午11:00:06
 */
@Api(description = "挂账列表对象")
@Data
public class HandingVo {
    @ApiModelProperty("订单ID")
    private Integer orderId;

    @ApiModelProperty("酒店ID")
    private Integer hotelId;

    @ApiModelProperty("姓名")
    private Integer customerName;
    
    @ApiModelProperty("联系电话")
    private Integer customerPhone;
    
    @ApiModelProperty("会员卡号")
    private Integer memberCard;
    
    @ApiModelProperty("是否协议单位")
    private Integer ifOrganization;
    
    @ApiModelProperty("单位名称")
    private Integer name;
    
    @ApiModelProperty("已还款")
    private Integer handingPrice;
    
    @ApiModelProperty("未还款")
    private Integer remandPrice;
    
    @ApiModelProperty("总挂账金额")
    private Integer totalPrice;
    
}