package com.gt.hotel.web.serviceVO.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.dao.THotelDAO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.requestEntity.HotelParameter;
import com.gt.hotel.responseEntity.HotelList;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.serviceVO.HotelService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 酒店后台业务接口 实现类
 *
 * @author ReverieNight@Foxmail.com
 */
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    THotelService tHotelService;

    @Autowired
    THotelDAO tHotelDAO;

    @Override
    public Page< HotelList > queryHotelHome(Integer busid, Page< HotelList > page) {
	Integer c = page.getCurrent();
	Integer s = page.getSize();
	Map< String, Object > param = new HashMap<>();
	param.put("bus_id", busid);
	param.put("page", (c - 1) * s);
	param.put("pageSize", s);
	List< THotel > hotelList = tHotelDAO.selectPage(new RowBounds(1, 10), null);
	page.setTotal(0);
	page.setRecords(null);
	return page;
    }

    @Override
    public boolean editHotel(Integer busid, HotelParameter.SaveOrUpdate hotel) {

	return false;
    }

}
