package com.gt.hotel.web.serviceVO.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.dao.THotelDAO;
import com.gt.hotel.responseEntity.HotelList;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.serviceVO.HotelService;

/**
 * 酒店后台业务接口 实现类
 * @author ReverieNight@Foxmail.com
 *
 */
@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	THotelService tHotelService;
	
	@Autowired
	THotelDAO tHotelDAO;

	@Override
	public Page<HotelList> queryHotelHome(Integer busid, Page<HotelList> page) {
		Integer c = page.getCurrent();
		Integer s = page.getSize();
		Map<String, Object> param = new HashMap<>();
		param.put("bus_id", busid);
		param.put("page", (c-1)*s);
		param.put("pageSize", s);
		List<HotelList> list = tHotelDAO.queryHotelHome(param);
		Integer total = tHotelDAO.queryHotelHomeCount(param);
		page.setTotal(total);
		page.setRecords(list);
		return page;
	}
	
}
