package com.gt.hotel.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TErpHotelRoomDAO;
import com.gt.hotel.entity.TErpHotelImage;
import com.gt.hotel.entity.TErpHotelRoom;
import com.gt.hotel.entity.TErpHotelRoomSuite;
import com.gt.hotel.web.service.TErpHotelImageService;
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
	
	@Transactional
	@Override
	public boolean roomInsertOrUpdate(TErpHotelRoom room, List<TErpHotelImage> imageList) {
		boolean flag = false;
		this.insertOrUpdate(room);
		System.err.println(room);
		if(imageList != null){
			Wrapper<TErpHotelImage> wrapper = new EntityWrapper<TErpHotelImage>();
			wrapper.eq("subjection_id", room.getId());
			wrapper.eq("subjection", 1);
			wrapper.eq("type", "0");
			tErpHotelImageService.delete(wrapper);
			for(TErpHotelImage a : imageList){
				a.setSubjection(1);
				a.setSubjectionId(room.getId());
				a.setType("0");
			}
//			System.err.println(imageList.get(0));
			tErpHotelImageService.insertBatch(imageList);
		}
		flag = true;
		return flag;
	}

	@Transactional
	@Override
	public boolean delRoom(List<Integer> idList) {
		boolean flag = false;
		if(idList != null){
			this.deleteBatchIds(idList);
			Wrapper<TErpHotelImage> wrapper = new EntityWrapper<TErpHotelImage>();
			wrapper.in("subjection_id", idList);
			wrapper.eq("subjection", 1);
			wrapper.eq("type", "0");
			tErpHotelImageService.delete(wrapper);
			Wrapper<TErpHotelRoomSuite> wrapper1 = new EntityWrapper<TErpHotelRoomSuite>();
			wrapper1.in("room_id", idList);
			tErpHotelRoomSuiteService.delete(wrapper1);
			flag = true;
		}
		return flag;
	}

	
}
