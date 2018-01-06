package com.gt.hotel.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 酒店房间 移动端 请求对象
 *
 * @author Reverien9@gmail.com
 * 2017年11月14日 下午2:31:23
 */
@Api(description = "酒店房间 移动端 请求对象")
public class RoomMobileParameter {

    @Data
    @Api("预定 参数")
    public static class BookParam {
        @NotNull(message = "房型ID不能为空")
        @ApiModelProperty(value = "房型ID", required = true)
        private Integer categoryId;

        @NotEmpty(message = "房型名称不能为空")
        @ApiModelProperty(value = "房型名称", required = true)
        private String categoryName;

        @ApiModelProperty(value = "入住时间", required = true)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date roomInTime;

        @ApiModelProperty(value = "离店时间", required = true)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date roomOutTime;

        @NotNull(message = "门市价不能为空")
        @ApiModelProperty(value = "门市价", required = true)
        private Integer rackRate;

        @NotNull(message = "押金不能为空")
        @ApiModelProperty(value = "押金", required = true)
        private Integer deposit;

        @NotNull(message = "客房数量不能为空")
        @ApiModelProperty(value = "客房数量", required = true)
        private Integer roomOrderNum;

        @ApiModelProperty(value = "客户姓名")
        private String customerName;

        @ApiModelProperty(value = "手机号码")
        private String customerPhone;

        @ApiModelProperty(value = "备注")
        private String remark;

        @ApiModelProperty(value = "支付类型 =0 支付宝 =1 微信 =2 到店支付 =3 储值卡支付 =4 信用卡 =5 现金 =6 挂账", required = true)
        @NotNull(message = "支付类型不能为空")
        private Integer payType;

        @ApiModelProperty(value = "粉币")
        private Integer fb;

        @ApiModelProperty(value = "积分")
        private Integer integral;

        @ApiModelProperty(value = "支付价格")
        private Integer payPrice;

        @ApiModelProperty("显示价格")
        private Integer displayPrice;

        @ApiModelProperty(value = "优惠券Code")
        private String couponsCode;

        @ApiModelProperty(value = "优惠券数量")
        private Integer couponsNum;

        @ApiModelProperty(value = "活动ID")
        private Integer activityId;

        @ApiModelProperty(value = "入住标准 0 全天房 1 特价房 2 钟点房 3 秒杀房 4 团购房(活动类型 非活动为0)", required = true)
        @NotNull(message = "入住标准不能为空")
        private Integer checkStandard;

        @ApiModelProperty(value = "粉币折扣金额")
        private Integer fbDiscount;

        @ApiModelProperty(value = "积分折扣金额")
        private Integer integralDiscount;

        @ApiModelProperty(value = "优惠券折扣金额")
        private Integer couponsDiscount;

        @ApiModelProperty(value = "钟点房入住时间 HH:mm:ss")
        private String hourRoomCheckInTime;
        
        @ApiModelProperty(value = "房费")
        private Integer roomPrice;
    }

    @Data
    @Api("房卡参数")
    public static class RoomCardParam extends HotelPage {

    }

}