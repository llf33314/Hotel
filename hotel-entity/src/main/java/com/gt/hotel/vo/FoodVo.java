package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 菜品
 *
 * @author Administrator
 */
@Data
@Api(description = "菜品")
public class FoodVo {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("酒店ID")
    private Integer hotelId;

    @ApiModelProperty("菜品名称")
    private String name;

    @ApiModelProperty("单价")
    private Integer price;

    @ApiModelProperty("配送时间 单位分钟")
    private Integer deliveryTime;

    @ApiModelProperty("菜品提供方 0 本酒店 1 合作方")
    private Integer foodProvides;

    @ApiModelProperty("提供方名称 food_provides =0 为酒店名称 =1 则填写合作方名称")
    private String foodProvidesName;

	@ApiModelProperty("新订单接收(短信)手机号")
	private String newOrderReceive;

    @ApiModelProperty("菜品图片URL")
    private String foodImage;

    @ApiModelProperty("早餐时段是否提供 0 提供 1 不提供")
    private Integer breakfastEnable;

    @ApiModelProperty("早餐开始时间 (示例: 6:00-10:00)")
    private Date breakfastBegin;

    @ApiModelProperty("早餐结束时间 (示例: 6:00-10:00)")
    private Date breakfastEnd;

    @ApiModelProperty("午餐时段是否提供 0 提供 1 不提供")
    private Integer lunchEnable;

    @ApiModelProperty("午餐开始时间 (示例: 6:00-10:00)")
    private Date lunchBegin;

    @ApiModelProperty("午餐结束时间 (示例: 6:00-10:00)")
    private Date lunchEnd;

    @ApiModelProperty("晚餐是否提供 0 是 1 否")
    private Integer dinnerEnable;

    @ApiModelProperty("晚餐开始时间")
    private Date dinnerBegin;

    @ApiModelProperty("晚餐结束时间")
    private Date dinnerEnd;

    @ApiModelProperty("宵夜是否提供 0 是 1 否")
    private Integer supperEnable;

    @ApiModelProperty("宵夜开始时间")
    private Date supperBegin;

    @ApiModelProperty("宵夜结束时间")
    private Date supperEnd;

}
