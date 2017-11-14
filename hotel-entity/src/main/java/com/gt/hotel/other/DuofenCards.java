/**
  * Copyright 2017 bejson.com 
  */
package com.gt.hotel.other;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 多粉卡券
 * @author Reverien9@gmail.com
 * 2017年11月14日 上午11:36:23
 */
@Api("多粉卡券")
@Data
public class DuofenCards {

	@ApiModelProperty("图片")
    private String image;
    
	@ApiModelProperty("卡券code,")
    private String code;
    
	@ApiModelProperty("用户拥有卡券表id")
    private int gId;
    
    private String color;
    
    private String time_limit;
    
    @ApiModelProperty("0不允许 1已允许 ,")
    private int addUser;
    
    @ApiModelProperty("减免金额(代金券)")
    private int reduce_cost;
    
    private int timeType;
    
    private double discount;
    
    @ApiModelProperty("商户名字")
    private String brand_name;
    
    private String location_id_list;
    
    private String type;
    
    @ApiModelProperty("卡券名")
    private String title;
    
    @ApiModelProperty("卡券类型 0折扣券 1减免券")
    private int card_type;
    
    private int card_status;
    
    @ApiModelProperty("表示起用金额(代金券)")
    private int cash_least_cost;
    
    @ApiModelProperty("卡券数量")
    private int countId;
    
    @ApiModelProperty("卡券模板")
    private int cId;

}