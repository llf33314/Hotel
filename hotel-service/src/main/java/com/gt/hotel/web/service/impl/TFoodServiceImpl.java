package com.gt.hotel.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TFoodDAO;
import com.gt.hotel.entity.TFood;
import com.gt.hotel.param.HotelPage;
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
	public Page<FoodVo> queryFood(HotelPage hpage) {
		Page<FoodVo> page = hpage.initPage();
		page.setRecords(tFoodDAO.queryFood(hpage, page));
		return page;
	}
	
}
