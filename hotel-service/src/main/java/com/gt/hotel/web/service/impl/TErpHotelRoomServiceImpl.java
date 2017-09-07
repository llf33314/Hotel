package com.gt.hotel.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TErpHotelRoomDAO;
import com.gt.hotel.entity.TErpHotelImage;
import com.gt.hotel.entity.TErpHotelRoom;
import com.gt.hotel.entity.TErpHotelRoomAndCount;
import com.gt.hotel.entity.TErpHotelRoomCalendar;
import com.gt.hotel.entity.TErpHotelRoomSuite;
import com.gt.hotel.web.service.TErpHotelImageService;
import com.gt.hotel.web.service.TErpHotelRoomCalendarService;
import com.gt.hotel.web.service.TErpHotelRoomService;
import com.gt.hotel.web.service.TErpHotelRoomSuiteService;

/**
 * <p>
 * ERP酒店房型 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-08-11
 */
@Service
public class TErpHotelRoomServiceImpl extends BaseServiceImpl<TErpHotelRoomDAO, TErpHotelRoom> implements TErpHotelRoomService {

	@Autowired
	TErpHotelImageService tErpHotelImageService;
	
	@Autowired
	TErpHotelRoomSuiteService tErpHotelRoomSuiteService;
	
	@Autowired
	TErpHotelRoomDAO TErpHotelRoomDAO;

	@Autowired
	TErpHotelRoomCalendarService TErpHotelRoomCalendarService;
	
	@Transactional
	@Override
	public Integer roomInsertOrUpdate(TErpHotelRoom room, List<TErpHotelImage> imageList) {
//		boolean flag = false;
		this.insertOrUpdate(room);
//		System.err.println(room);
		if(imageList != null){
			Wrapper<TErpHotelImage> wrapper = new EntityWrapper<TErpHotelImage>();
			wrapper.eq("subjection_id", room.getId());
			wrapper.eq("subjection", TErpHotelImage.ROOM);
			wrapper.eq("type", "0");
			tErpHotelImageService.delete(wrapper);
			for(TErpHotelImage a : imageList){
				a.setSubjection(TErpHotelImage.ROOM);
				a.setSubjectionId(room.getId());
				a.setType("0");
			}
//			System.err.println(imageList.get(0));
			tErpHotelImageService.insertBatch(imageList);
		}
//		flag = true;
//		return flag;
		return room.getId();
	}

	@Transactional
	@Override
	public boolean delRoom(List<Integer> idList) {
		boolean flag = false;
		if(idList != null){
			this.deleteBatchIds(idList);
			Wrapper<TErpHotelImage> wrapper = new EntityWrapper<TErpHotelImage>();
			wrapper.in("subjection_id", idList);
			wrapper.eq("subjection", TErpHotelImage.ROOM);
			wrapper.eq("type", "0");
			tErpHotelImageService.delete(wrapper);
			Wrapper<TErpHotelRoomSuite> wrapper1 = new EntityWrapper<TErpHotelRoomSuite>();
			wrapper1.in("room_id", idList);
			tErpHotelRoomSuiteService.delete(wrapper1);
			Wrapper<TErpHotelRoomCalendar> wrapper2 = new EntityWrapper<TErpHotelRoomCalendar>();
			wrapper2.in("room_id", idList);
			TErpHotelRoomCalendarService.delete(wrapper2);
			flag = true;
		}
		return flag;
	}

	@Override
	public List<Integer> selectRoomIdsByHotelIds(List<Integer> idList) {
		return TErpHotelRoomDAO.selectRoomIdsByHotelIds(idList);
	}

	@Override
	public Page<TErpHotelRoomAndCount> selectHotelRoom(Page<TErpHotelRoomAndCount> page, Map<String, Object> param) {
		Integer c = page.getCurrent();
		Integer s = page.getSize();
		param.put("page", (c-1)*s);
		param.put("pageSize", s);
		List<TErpHotelRoomAndCount> list = TErpHotelRoomDAO.selectHotelRoom(param);
		Integer total = TErpHotelRoomDAO.selectHotelRoomCount(param);
		page.setTotal(total);
//		page.setPages((total + s -1) / s);
		page.setRecords(list);
		return page;
	}


	
}
