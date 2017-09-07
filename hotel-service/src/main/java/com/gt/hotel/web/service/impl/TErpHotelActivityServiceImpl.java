package com.gt.hotel.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TErpHotelActivityDAO;
import com.gt.hotel.entity.TErpHotelActivity;
import com.gt.hotel.entity.TErpHotelActivityAndSuite;
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
	@Autowired
	TErpHotelActivityDAO TErpHotelActivityDAO;
	
	@Transactional
	@Override
	public boolean insertOrUpdate(TErpHotelActivity activity, List<TErpHotelActivityRoomSuite> activitySuiteList) {
		boolean flag = false;
		Integer activityStatus = TErpHotelActivity.NOT_START;
		Long now = System.currentTimeMillis();
		Long st = activity.getActivityStime().getTime();
		Long et = activity.getActivityEtime().getTime();
		activityStatus = now > st && now < et ? TErpHotelActivity.PROCESSING : (now > et ? TErpHotelActivity.OVER : TErpHotelActivity.NOT_START);
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

	@Override
	public List<Integer> selectActivityIdsByHotelIds(List<Integer> idList) {
		return TErpHotelActivityDAO.selectActivityIdsByHotelIds(idList);
	}

	@Override
	public Page<TErpHotelActivityAndSuite> selectActivitySuite(Page<TErpHotelActivityAndSuite> page, Map<String, Object> param) {
		Integer c = page.getCurrent();
		Integer s = page.getSize();
		param.put("page", (c-1)*s);
		param.put("pageSize", s);
		List<TErpHotelActivityAndSuite> list = TErpHotelActivityDAO.selectActivitySuitePage(param);
		Integer total = TErpHotelActivityDAO.selectActivitySuitePageCount(param);
		Wrapper<TErpHotelActivityRoomSuite> wrapper = new EntityWrapper<>();
		wrapper.eq(param.get("id") != null, "activity_id", param.get("id"));
		List<TErpHotelActivityRoomSuite> su = TErpHotelActivityRoomSuiteService.selectList(wrapper);
		for(TErpHotelActivityAndSuite asu : list){
			List<TErpHotelActivityRoomSuite> _su = new ArrayList<>();
			for(TErpHotelActivityRoomSuite sui : su){
				if(sui.getActivityId().equals(asu.getId())) _su.add(sui);
			}
			asu.setSuites(_su);
		}
		page.setTotal(total);
		page.setRecords(list);
		return page;
	}

	@Override
	public boolean updateAStatus(TErpHotelActivity entity) {
		boolean flag = false;
		int i = TErpHotelActivityDAO.updateAStatus(entity);
		flag = i == 0 ? false : true;
		return flag;
	}
	
}
