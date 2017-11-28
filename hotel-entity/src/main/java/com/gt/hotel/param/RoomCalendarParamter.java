package com.gt.hotel.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Api(description = "房型日历价格参数")
public class RoomCalendarParamter {

    @Data
    @Api("房型日历价格 查询 参数")
    public static class CalendarQuery extends HotelPage {

        @ApiModelProperty(value = "日历ID")
        private Integer id;

        @ApiModelProperty(value = "开始日期")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date beginTime;

        @ApiModelProperty(value = "结束日期")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date endTime;
    }

    @Data
    @Api("房型日历价格 编辑 参数")
    public static class CalendarSaveOrUpdate {
        @ApiModelProperty(value = "酒店ID", required = true)
        @NotNull(message = "酒店ID不能为空")
        private Integer hotelId;

//		@ApiModelProperty(value = "房型ID")
//		@NotNull(message = "房型ID不能为空")
//		private Integer categoryId;

        @ApiModelProperty(value = "日历ID(更新需要)")
        private Integer id;

        @ApiModelProperty(value = "开始日期", required = true)
        @NotNull(message = "开始日期不能为空")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date beginTime;

        @ApiModelProperty(value = "结束日期", required = true)
        @NotNull(message = "结束日期不能为空")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date endTime;

        @ApiModelProperty(value = "日历价", required = true)
        @NotNull(message = "日历价不能为空")
        private Integer price;
    }

}
