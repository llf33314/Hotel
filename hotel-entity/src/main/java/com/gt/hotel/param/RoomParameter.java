package com.gt.hotel.param;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 房间 请求对象
 *
 * @author ReverieNight@Foxmail.com
 */

@Api(tags = "房间 实体参数")
public class RoomParameter {

    @Data
    @Api("保存 或 更新 参数")
    public static class RoomSaveOrUpdate {
        @ApiModelProperty(value = "房间ID(更新时需要)")
        private Integer id;

        @ApiModelProperty(value = "房型ID", required = true)
        @NotNull(message = "房型ID不能为空")
        private Integer categoryId;

        @ApiModelProperty(value = "房间编号", required = true)
        @NotEmpty(message = "房间编号不能为空")
        private String number;

        @ApiModelProperty(value = "楼层", required = true)
        @NotEmpty(message = "楼层不能为空")
        private String floor;

        @ApiModelProperty(value = "房间状态")
        private String status = /*RoomStatusConst.VACANT_ROOM*/"空房";
    }

    @Data
    @Api(description = "长包房 保存参数")
    public static class RoomPermanent {
        @ApiModelProperty(value = "长包房ID")
        private Integer id;

        @ApiModelProperty(value = "房型ID", required = true)
        @NotNull(message = "房型ID不能为空")
        private Integer categoryId;

        @ApiModelProperty(value = "酒店ID")
        private Integer hotelId;

        @ApiModelProperty(value = "规则名称", required = true)
        @NotEmpty(message = "规则名称不能为空")
        private String ruleName;

        @ApiModelProperty(value = "入住天数", required = true)
        @NotNull(message = "入住天数不能为空")
        private Integer checkInDay;

        @ApiModelProperty(value = "折扣 默认：1 无折扣", required = true)
        @NotNull(message = "折扣不能为空")
        @Min(value = 1l, message = "折扣数值错误")
        @Max(value = 100l, message = "折扣数值错误")
        private Integer discount;

        @ApiModelProperty(value = "押金", required = true)
        @NotNull(message = "押不能为空")
        private Integer deposit;

    }

    @Data
    @Api(description = "长包房 查询参数")
    public static class RoomPermanentQuery extends HotelPage {
        @ApiModelProperty(value = "长包房ID")
        private Integer id;
    }
}

