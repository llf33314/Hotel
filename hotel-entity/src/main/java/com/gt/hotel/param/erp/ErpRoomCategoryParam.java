package com.gt.hotel.param.erp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * 1.0.0 2017/12/29 zhangmz -
 * ---------------------------------------------------------------------------------------
 * Version Date Author Description
 * Erp房型参数列表
 * ---------------------------------------------------------------------------------------
 * </pre>
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017-12-29
 * @since 1.0.0
 */
public class ErpRoomCategoryParam {


    @Api("房态查询")
    @Data
    @Component
    public static class RoomCategorySearch {

        @ApiModelProperty("房号检索")
        private String roomNum;

        @ApiModelProperty("楼层")
        private String floor;

        @ApiModelProperty("客房状态 1\t空房,2\t锁定,3\t在住,4\t清洁,5\t维护")
        private Integer roomStatus;

    }


}
