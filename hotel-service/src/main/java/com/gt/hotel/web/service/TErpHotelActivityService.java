package com.gt.hotel.web.service;

import com.gt.hotel.entity.TErpHotelActivity;
import com.gt.hotel.entity.TErpHotelActivityAndSuite;
import com.gt.hotel.entity.TErpHotelActivityRoomSuite;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;

/**
 * <p>
 * ERP酒店-活动 服务类
 * </p>
 *
 * @author 
 * @since 2017-08-14
 */
public interface TErpHotelActivityService extends BaseService<TErpHotelActivity> {

	boolean insertOrUpdate(TErpHotelActivity activity, List<TErpHotelActivityRoomSuite> activitySuiteList);

	boolean delHotelActivity(List<Integer> idList);

	List<Integer> selectActivityIdsByHotelIds(List<Integer> idList);

	Page<TErpHotelActivityAndSuite> selectActivitySuite(Page<TErpHotelActivityAndSuite> page, Map<String, Object> param);

	boolean updateAStatus(TErpHotelActivity entity);
	
}
