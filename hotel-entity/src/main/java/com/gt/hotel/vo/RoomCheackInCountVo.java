package com.gt.hotel.vo;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 客房入住数
 * @author Reverien9@gmail.com
 * 2017年11月22日 上午10:19:46
 */
@Api("客房入住数")
@Data
public class RoomCheackInCountVo {
	
	@ApiModelProperty("房间总数")
    private Integer roomCount = 0;

	@ApiModelProperty("在住间数")
    private Integer checkInRoomCount = 0;
	
	@ApiModelProperty("房型列表")
	private List<RoomCategoryVo> roomCategorys;
}
