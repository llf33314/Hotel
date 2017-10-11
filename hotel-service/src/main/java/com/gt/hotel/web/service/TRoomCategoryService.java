package com.gt.hotel.web.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TRoomCategory;
import com.gt.hotel.requestEntity.RoomCategoryParameter.queryRoomCategory;
import com.gt.hotel.responseEntity.RoomCategoryList;

/**
 * <p>
 * 房型 服务类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface TRoomCategoryService extends BaseService< TRoomCategory > {

	/**
	 * 查询 房型列表
	 * @param hotelId 酒店ID
	 * @param page
	 * @return
	 */
	Page<RoomCategoryList> queryRoomCategory(queryRoomCategory param, Page<RoomCategoryList> page);

}
