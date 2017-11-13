package com.gt.hotel.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TFoodDAO;
import com.gt.hotel.entity.TFood;
import com.gt.hotel.param.FoodMobileParameter.FoodMobileQuery;
import com.gt.hotel.vo.FoodVo;
import com.gt.hotel.web.service.TFoodService;

/**
 * <p>
 * 菜品 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-20
 */
@Service
public class TFoodServiceImpl extends BaseServiceImpl<TFoodDAO, TFood> implements TFoodService {

	@Autowired
	TFoodDAO tFoodDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<FoodVo> queryFood(FoodMobileQuery param, Integer hotelId) {
		Page<FoodVo> page = param.initPage();
		page.setRecords(tFoodDAO.queryFood(param, hotelId, page));
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<FoodVo> queryFoodNoPage(FoodMobileQuery param, Integer hotelId) {
		Page<FoodVo> page = param.initPage();
		page.setRecords(tFoodDAO.queryFood(param, hotelId));
		return page;
	}
	
}
