package com.gt.hotel.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
	@Override
	public boolean insertOrUpdate(TErpHotelActivity activity, List<TErpHotelActivityRoomSuite> activitySuiteList) {
		boolean flag = false;
		Integer activityStatus = 0;
		Long now = System.currentTimeMillis();
		Long st = activity.getActivityStime().getTime();
		Long et = activity.getActivityEtime().getTime();
		activityStatus = now > st && now < et ? 1 : (now > et ? 2 : 0);
		activity.setActivityStatus(activityStatus);
		if(activity.getId() != null){
			Wrapper<TErpHotelActivityRoomSuite> wrapper = new EntityWrapper<TErpHotelActivityRoomSuite>();
			wrapper.eq("activity_id", activity.getId());
			TErpHotelActivityRoomSuiteService.delete(wrapper);
		}else
			activity.setCreatetime(new Date());
		this.insertOrUpdate(activity);
		for(TErpHotelActivityRoomSuite a : activitySuiteList){
			a.setActivityId(activity.getId());
		}
		if(activitySuiteList != null && activitySuiteList.size() > 0) TErpHotelActivityRoomSuiteService.insertBatch(activitySuiteList);
		flag = true;
		return flag;
	}

	@Transactional
	@Override
	public boolean delHotelActivity(List<Integer> idList) {
		boolean flag = false;
		this.deleteBatchIds(idList);
		Wrapper<TErpHotelActivityRoomSuite> wrapper = new EntityWrapper<TErpHotelActivityRoomSuite>();
		wrapper.in("activity_id", idList);
		TErpHotelActivityRoomSuiteService.delete(wrapper);
		flag = true;
		return flag;
	}
	
}
