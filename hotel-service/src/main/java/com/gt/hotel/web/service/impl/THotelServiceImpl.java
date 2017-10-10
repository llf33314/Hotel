package com.gt.hotel.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.THotelDAO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.responseEntity.HotelList;
import com.gt.hotel.web.service.THotelService;

/**
 * <p>
 * 酒店主表 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Service
public class THotelServiceImpl extends BaseServiceImpl< THotelDAO, THotel > implements THotelService {

	@Autowired
	THotelDAO tHotelDAO;
	
	@Override
	public Page<HotelList> queryHotelHome(Integer busid, Page<HotelList> page) {
		page.setRecords(tHotelDAO.queryHotelHome(page, busid));
		return page;
	}

}
