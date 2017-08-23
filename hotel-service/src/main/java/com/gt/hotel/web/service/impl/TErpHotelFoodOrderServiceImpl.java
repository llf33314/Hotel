package com.gt.hotel.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TErpHotelFoodOrderDAO;
import com.gt.hotel.entity.TErpHotelFoodOrder;
import com.gt.hotel.entity.TErpHotelFoodOrderVO;
import com.gt.hotel.web.service.TErpHotelFoodOrderRelationService;
import com.gt.hotel.web.service.TErpHotelFoodOrderService;

/**
 * <p>
 * ERP酒店-餐饮-订单 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-08-17
 */
@Service
public class TErpHotelFoodOrderServiceImpl extends BaseServiceImpl<TErpHotelFoodOrderDAO, TErpHotelFoodOrder> implements TErpHotelFoodOrderService {
	
	@Autowired
	TErpHotelFoodOrderDAO TErpHotelFoodOrderDAO;
	
	@Autowired
	TErpHotelFoodOrderRelationService TErpHotelFoodOrderRelationService;

	@Override
	public Page<TErpHotelFoodOrderVO> selectFoodOrderPage(Page<TErpHotelFoodOrderVO> page, Map<String, Object> param) {
		Integer c = page.getCurrent();
		Integer s = page.getSize();
		param.put("page", (c-1)*s);
		param.put("pageSize", s);
		List<TErpHotelFoodOrderVO> list = TErpHotelFoodOrderDAO.selectFoodOrderVOList(param);
		Integer total = TErpHotelFoodOrderDAO.selectFoodOrderVOListCount(param);
		page.setTotal(total);
//		page.setPages((total + s -1) / s);
		page.setRecords(list);
		return page;
	}

	@Override
	public List<TErpHotelFoodOrderVO> selectFoodOrderExport(Map<String, Object> param) {
		return TErpHotelFoodOrderDAO.selectFoodOrderVOList(param);
	}

	@Transactional
	@Override
	public boolean deleteFoodOrder(List<Integer> idList) {
		boolean flag = false;
		TErpHotelFoodOrderDAO.updateOrderDel(idList);
		flag = true;
		return flag;
	}

	@Override
	public boolean updateStatus(TErpHotelFoodOrder po) {
		boolean flag = false;
		TErpHotelFoodOrderDAO.updateStatus(po);
		flag = true;
		return flag;
	}
	
}
