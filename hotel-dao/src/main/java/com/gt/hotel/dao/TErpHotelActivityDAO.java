package com.gt.hotel.dao;

import com.gt.hotel.entity.TErpHotelActivity;
import com.gt.hotel.entity.TErpHotelActivityAndSuite;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * ERP酒店-活动 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-08-14
 */
public interface TErpHotelActivityDAO extends BaseMapper<TErpHotelActivity> {

	List<Integer> selectActivityIdsByHotelIds(List<Integer> idList);

	List<TErpHotelActivityAndSuite> selectActivitySuitePage(Map<String, Object> param);

	Integer selectActivitySuitePageCount(Map<String, Object> param);

	Integer updateAStatus(TErpHotelActivity entity);

}