package com.gt.hotel.param;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 新增or更新酒店请求对象
 *
 * @author ReverieNight@Foxmail.com
 */
public class HotelParameter {
    /**
     * 新增或更新实体参数
     */
    @Data
    @Api("保存或更新实体")
    public static class HotelSaveOrUpdate {
        @ApiModelProperty(value = "酒店ID")
        private Integer hotelId;

        @ApiModelProperty(value = "门店ID", required = true)
        @NotNull(message = "门店ID不能为空")
        private Integer shopId;

        @ApiModelProperty(value = "酒店名称", required = true)
        @NotEmpty(message = "酒店名称不能为空")
        @Length(max = 60, message = "酒店名称长度过长")
        private String name;

        @ApiModelProperty(value = "酒店电话", required = true)
        @NotEmpty(message = "酒店电话不能为空")
        @Length(max = 12, message = "酒店电话长度过长")
        private String tel;

        @ApiModelProperty(value = "酒店地址", required = true)
        @NotEmpty(message = "酒店地址不能为空")
        @Length(max = 200, message = "酒店地址长度过长")
        private String addr;

        @ApiModelProperty(value = "经度", required = true)
        @NotNull(message = "经度不能为空")
        private Double longitude;

        @ApiModelProperty(value = "纬度", required = true)
        @NotNull(message = "纬度不能为空")
        private Double latitude;

        @ApiModelProperty(value = "酒店介绍", required = true)
        @NotEmpty(message = "酒店介绍不能为空")
        @Length(max = 200, message = "酒店介绍长度过长")
        private String desc;

    }

    @Api("酒店查询参数")
    @Data
    public static class HotelQuery extends HotelPage {
        @ApiModelProperty("酒店ID")
        private Integer hotelId;
    }

}
