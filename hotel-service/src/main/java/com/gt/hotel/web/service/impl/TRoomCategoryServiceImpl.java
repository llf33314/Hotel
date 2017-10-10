package com.gt.hotel.web.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TRoomCategoryDAO;
import com.gt.hotel.entity.TRoomCategory;
import com.gt.hotel.responseEntity.ResRoomCatepory.RoomCategoryList;
import com.gt.hotel.web.service.TRoomCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 房型 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Service
public class TRoomCategoryServiceImpl extends BaseServiceImpl< TRoomCategoryDAO, TRoomCategory > implements TRoomCategoryService {

	@Autowired
	TRoomCategoryDAO tRoomCategoryDAO;
	
	@Override
	public Page<RoomCategoryList> queryRoomCategory(Integer hotelId, Page<RoomCategoryList> page) {
		page.setRecords(tRoomCategoryDAO.queryRoomCategory(hotelId, page));
		return page;
	}

}
