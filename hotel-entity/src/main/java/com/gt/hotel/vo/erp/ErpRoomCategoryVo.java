package com.gt.hotel.vo.erp;

import com.gt.hotel.vo.RoomVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <pre>
 * 1.0.0 2017/12/28 zhangmz -
 * ---------------------------------------------------------------------------------------
 * Version Date Author Description
 * Erp前台客房 房型列表
 * ---------------------------------------------------------------------------------------
 * </pre>
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017-12-28
 * @since 1.0.0
 */
@Api("Erp前台房态房型列表")
@Data
public class ErpRoomCategoryVo {

    @ApiModelProperty("房型ID")
    private Integer id;
    /**
     * 房型名称
     */
    @ApiModelProperty("房型名称")
    private String  name;
    /**
     * 房间总数
     */
    @ApiModelProperty("客房数量")
    private Integer roomCount;
    /**
     * 酒店ID
     */
    @ApiModelProperty("酒店ID")
    private Integer hotelId;

    /**
     * 今日预订数量 房型
     * 当天入住数量
     */
    @ApiModelProperty("今日预订数量")
    private Integer bookingCount;

    /**
     * 客房列表
     */
    @ApiModelProperty("客房列表")
    private List<RoomVo> roomList;


}
