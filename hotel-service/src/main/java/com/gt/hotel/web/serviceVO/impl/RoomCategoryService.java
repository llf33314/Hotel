package com.gt.hotel.web.serviceVO.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.dao.TRoomCategoryDAO;
import com.gt.hotel.requestEntity.ReqRoomCategory;
import com.gt.hotel.responseEntity.ResRoomCategory;

/**
 * 酒店后台 房间管理 业务接口 实现类
 * @author ReverieNight@Foxmail.com
 * @date 2017年10月9日 下午5:54:34
 */
@Service
public class RoomCategoryService implements com.gt.hotel.web.serviceVO.RoomCategoryService {
	
	@Autowired
	TRoomCategoryDAO tRoomCategoryDAO;

	@Override
	public Page<ResRoomCategory> queryRoomCategory(ReqRoomCategory roomCate, Page<ResRoomCategory> page) {
		Integer c = page.getCurrent();
		Integer s = page.getSize();
		Map<String, Object> param = new HashMap<>();
		param.put("hotel_id", roomCate.getHotelid());
		param.put("page", (c-1)*s);
		param.put("pageSize", s);
		List<ResRoomCategory> list = tRoomCategoryDAO.queryRoomCategory(param);
		Integer total = tRoomCategoryDAO.queryRoomCategoryCount(param);
		page.setTotal(total);
		page.setRecords(list);
		return page;
	}

}
