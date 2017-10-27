package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Api(description = "酒店移动端设置")
public class HotelSettingVo {

    @ApiModelProperty("酒店ID")
    private Integer hotelId;

    @ApiModelProperty("支付方式(1：在线支付 | 2：到店支付 | 3：1&2)  默认 在线支付")
    private Integer payMode;

    @ApiModelProperty("是否开启短信通知 默认 1 不开启 0 开启")
    private Integer smsEnable;

    @ApiModelProperty("接收短信的手机号")
    private String smsPhone;

    @ApiModelProperty("酒店公告 1 不开启 0 开启")
    private Integer noticeEnable;

    @ApiModelProperty("公告")
    private String bulletin;

    @ApiModelProperty("是否显示剩余房间数 默认 1 关闭 0 开启")
    private Integer remnantRoomEnable;

    @ApiModelProperty("开启客房订餐 0 开启  1 不开启 默认1")
    private Integer roomReservationEnable;

    @ApiModelProperty("餐饮支付方式 1 在线 2 到付 3 在线&到付")
    private Integer foodPayMode;

    @ApiModelProperty("预约退房 0 开启 1 关闭  默认1")
    private Integer reservationCheckOutEnable;

    @ApiModelProperty("预约退房 接收短信的手机号")
    private String reservationCheckOutPhone;

    @ApiModelProperty("发票支持的类目 1 办公用品 2 住宿费 3 餐费 4 培训费 5 打球费 6 健身费 存储方式 1,2,3,4,5 or 1,2,3")
    private String  InvoiceCategorys;
    
    @ApiModelProperty("创建者ID")
    private Integer createdBy;

    @ApiModelProperty("创建时间")
    private Date createdAt;

    @ApiModelProperty("最后修改人 ID")
    private Integer updatedBy;

    @ApiModelProperty("最后修改时间")
    private Date updatedAt;

    @ApiModelProperty("图片地址集合")
    private List<FileRecordVo> imageurls;

    @ApiModelProperty("设施集合")
    private List<InfrastructureRelationVo> installations;

}
