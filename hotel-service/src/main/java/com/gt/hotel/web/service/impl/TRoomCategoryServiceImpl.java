package com.gt.hotel.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TRoomCategoryDAO;
import com.gt.hotel.entity.TRoomCategory;
import com.gt.hotel.vo.RoomCategoryVo;
import com.gt.hotel.web.service.TRoomCategoryService;

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
    public Page< RoomCategoryVo > queryRoomCategory(com.gt.hotel.param.RoomCategoryParameter.queryRoomCategory param, Page< RoomCategoryVo > page) {
	page.setRecords(tRoomCategoryDAO.queryRoomCategory(param, page));
	return page;
    }

}
