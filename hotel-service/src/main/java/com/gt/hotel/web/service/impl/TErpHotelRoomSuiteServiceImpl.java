package com.gt.hotel.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TErpHotelRoomSuiteDAO;
import com.gt.hotel.entity.TErpHotelRoomSuite;
import com.gt.hotel.entity.TErpHotelRoomSuiteFloorVer;
import com.gt.hotel.web.service.TErpHotelRoomSuiteService;

/**
 * <p>
 * ERP酒店房型楼层房间 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-08-11
 */
@Service
public class TErpHotelRoomSuiteServiceImpl extends BaseServiceImpl<TErpHotelRoomSuiteDAO, TErpHotelRoomSuite> implements TErpHotelRoomSuiteService {
	
	@Autowired
	TErpHotelRoomSuiteDAO TErpHotelRoomSuiteDAO;

	@Override
	public List<TErpHotelRoomSuiteFloorVer> selectFloorVerList(Integer roomId) {
		List<TErpHotelRoomSuiteFloorVer> list = new ArrayList<TErpHotelRoomSuiteFloorVer>();
		Wrapper<TErpHotelRoomSuite> wrapper = new EntityWrapper<TErpHotelRoomSuite>();
		wrapper.eq(roomId != null, "room_id", roomId);
		List<TErpHotelRoomSuite> suites = TErpHotelRoomSuiteDAO.selectList(wrapper);
		for(TErpHotelRoomSuite rs : suites){
			TErpHotelRoomSuiteFloorVer fv = new TErpHotelRoomSuiteFloorVer(rs.getFloor());
			if(!list.contains(fv)) list.add(fv);
		}
		for(TErpHotelRoomSuiteFloorVer fv : list){
			List<TErpHotelRoomSuite> _suites = new ArrayList<TErpHotelRoomSuite>();
			for(TErpHotelRoomSuite rs : suites){
				if(fv.getFloor().equals(rs.getFloor())) _suites.add(rs);
			}
			fv.settErpHotelRoomSuites(_suites);
		}
		return list;
	}

	@Transactional
	@Override
	public boolean insertAll(Integer roomId, List<TErpHotelRoomSuite> suiteList) {
		boolean flag = false;
		if(suiteList != null && suiteList.size() > 0){
			for(TErpHotelRoomSuite rs : suiteList) rs.setRoomId(roomId);
			Wrapper<TErpHotelRoomSuite> wrapper = new EntityWrapper<TErpHotelRoomSuite>();
			wrapper.eq("room_id", roomId);
			this.delete(wrapper);
			this.insertBatch(suiteList);
			flag = true;
		}
		return flag;
	}
	
}
