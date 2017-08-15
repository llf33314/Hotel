package com.gt.hotel.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TErpHotelActivityDAO;
import com.gt.hotel.entity.TErpHotelActivity;
import com.gt.hotel.entity.TErpHotelActivityRoomSuite;
import com.gt.hotel.web.service.TErpHotelActivityRoomSuiteService;
import com.gt.hotel.web.service.TErpHotelActivityService;

/**
 * <p>
 * ERP酒店-活动 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-08-14
 */
@Service
public class TErpHotelActivityServiceImpl extends BaseServiceImpl<TErpHotelActivityDAO, TErpHotelActivity> implements TErpHotelActivityService {
	
	@Autowired
	TErpHotelActivityRoomSuiteService TErpHotelActivityRoomSuiteService;
	
	@Override
	public boolean insertOrUpdate(TErpHotelActivity activity, List<TErpHotelActivityRoomSuite> activitySuiteList) {
		boolean flag = false;
		if(activity.getId() != null){
			Wrapper<TErpHotelActivityRoomSuite> wrapper = new EntityWrapper<TErpHotelActivityRoomSuite>();
			wrapper.eq("activity_id", activity.getId());
			TErpHotelActivityRoomSuiteService.delete(wrapper);
		}
		this.insertOrUpdate(activity);
		for(TErpHotelActivityRoomSuite a : activitySuiteList){
			a.setActivityId(activity.getId());
		}
		if(activitySuiteList != null && activitySuiteList.size() > 0) TErpHotelActivityRoomSuiteService.insertBatch(activitySuiteList);
		flag = true;
		return flag;
	}
	
}
