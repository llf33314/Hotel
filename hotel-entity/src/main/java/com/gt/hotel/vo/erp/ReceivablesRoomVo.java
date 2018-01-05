package com.gt.hotel.vo.erp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author Reverien9@gmail.com
 * 2018年1月5日 上午11:27:46
 */
@Api("收款订单 房间信息")
@Data
public class ReceivablesRoomVo {

	@ApiModelProperty("房号")
	private String roomNum;

	@ApiModelProperty("房型")
	private String categoryName;
	
	@ApiModelProperty("房型ID")
	private Integer categoryId;
	
	@ApiModelProperty("早餐券")
    private Integer breakfastQuantity;

    @ApiModelProperty("门市价")
    private Integer rackRate;
    
    @ApiModelProperty("房价")
    private Integer roomPrice;
    
    @ApiModelProperty("押金")
    private Integer deposit;
    
    @ApiModelProperty("入住天数")
    private Integer daysStay;
    
    @ApiModelProperty("备注")
    private String remark;
	
}
