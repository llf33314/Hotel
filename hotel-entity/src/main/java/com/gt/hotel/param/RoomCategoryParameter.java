package com.gt.hotel.param;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.gt.hotel.vo.FileRecordVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新增or更新房型请求对象
 *
 * @author ReverieNight@Foxmail.com
 */

@Api(description = "房型实体参数")
public class RoomCategoryParameter {

    /**
     * 房型列表查询参数
     */
    @Data
    @Api(description = "房型列表查询参数")
    public static class QueryRoomCategory extends HotelPage {
        @ApiModelProperty(value = "酒店ID", required = true)
        @NotNull(message = "酒店ID不能为空")
        private Integer hotelId;

        @ApiModelProperty(value = "房型ID")
        private Integer categoryId;
    }

    @Data
    @Api(description = "移动端 房型列表 查询参数")
    public static class MobileQueryRoomCategory {
        @ApiModelProperty(value = "房型ID")
        private Integer categoryId;

        @ApiModelProperty(value = "入住时间")
//        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private String roomInTime;

        @ApiModelProperty(value = "离店时间")
//        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private String roomOutTime;
    }

    /**
     * 房型对象查询参数
     */
    @Data
    @Api(description = "房型对象查询参数")
    public static class QueryRoomCategoryOne {
        @ApiModelProperty(value = "房型ID")
        @NotNull(message = "房型ID不能为空")
        private Integer categoryId;
    }

    /**
     * 新增 或 更新 参数
     */
    @Data
    @Api(description = "保存 或 更新 参数")
    public static class CategorySaveOrUpdate {
        @ApiModelProperty(value = "房型ID")
        private Integer categoryId;

        @ApiModelProperty(value = "酒店ID", required = true)
        @NotNull(message = "酒店ID不能为空")
        private Integer hotelId;

        @ApiModelProperty(value = "门店ID")
        private Integer shopId;

        @ApiModelProperty(value = "房间类型", required = true)
        @NotEmpty(message = "房间类型不能为空")
        private String name;

        @ApiModelProperty(value = "图片路径集合")
        private List<FileRecordVo> images;

        @ApiModelProperty(value = "简要说明", required = true)
        @NotEmpty(message = "简要说明不能为空")
        private String desc;

        @ApiModelProperty(value = "门市价", required = true)
        @NotNull(message = "门市价不能为空")
        private Integer rackRate;

//		@ApiModelProperty("是否需要计算优惠价：0 需要 1 不需要")
//		private Integer discountEnable;
//		
//		@ApiModelProperty("折扣率范围 1 - 100 默认 100(计算方式：85/100=0.85 即85折)，即不打折")
//		private Integer discount;

        @ApiModelProperty("是否开启周末价 0 开启 1 禁用，即显示门市价即可")
        private Integer weekendFareEnable;

        @ApiModelProperty("周末价格")
        private Integer weekendFare;

        @ApiModelProperty("是否开启早餐券 0 开启 1 关闭")
        private Integer breakfastEnable;

        @ApiModelProperty("早餐数量")
        private Integer breakfastQuantity;

        @ApiModelProperty(value = "押金", required = true)
        @NotNull(message = "押金不能为空")
        private Integer deposit;

        @ApiModelProperty("房型设施集合 ")
        private List<InfrastructureRelationParamter> infrastructureRelations;
    }

}

