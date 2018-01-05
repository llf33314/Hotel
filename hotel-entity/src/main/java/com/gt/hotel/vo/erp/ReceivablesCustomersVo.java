package com.gt.hotel.vo.erp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 收款订单客人
 * @author Reverien9@gmail.com
 * 2018年1月5日 上午11:24:27
 */
@Api("收款订单客人")
@Data
public class ReceivablesCustomersVo {

	@ApiModelProperty("客人名称")
	private String customerName;

	@ApiModelProperty("手机号码")
	private String customerPhone;
	
	@ApiModelProperty("证件类型 =0 二代身份证 =1 一代身份证 =2 驾驶证 =3 护照 =4 军官证 =5 士兵证 =6 港澳通行证 =7 其他")
    private Integer customerIdType;

    @ApiModelProperty("证件号")
    private String customerIdCard;
	
}
