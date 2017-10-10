package com.gt.hotel.web.serviceVO;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.requestEntity.ReqRoomCategory;
import com.gt.hotel.responseEntity.ResRoomCategory;

/**
 * 酒店后台 房间管理  业务接口
 * @author ReverieNight@Foxmail.com
 * @date 2017年10月9日 下午5:52:48
 */
public interface RoomCategoryService {

	/**
	 * 查询 房间管理 首页 房型列表
	 * @param page 分页对象
	 * @return 房型列表
	 */
	Page<ResRoomCategory> queryRoomCategory(ReqRoomCategory roomCate, Page<ResRoomCategory> page);


}
