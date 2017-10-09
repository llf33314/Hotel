package com.gt.hotel.web.serviceVO;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.responseEntity.HotelList;

/**
 * 酒店后台业务接口
 * @author ReverieNight@Foxmail.com
 *
 */
public interface HotelService {

	/**
	 * 查询 新增酒店 首页 酒店列表
	 * @param busid 用户ID
	 * @param page 分页对象
	 * @return 酒店列表
	 */
	Page<HotelList> queryHotelHome(Integer busid, Page<HotelList> page);

}
