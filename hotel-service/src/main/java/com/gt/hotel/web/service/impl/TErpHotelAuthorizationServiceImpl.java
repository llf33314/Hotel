package com.gt.hotel.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TErpHotelAuthorizationDAO;
import com.gt.hotel.dao.TErpHotelFunctionDAO;
import com.gt.hotel.entity.TCommonStaff;
import com.gt.hotel.entity.TErpHotelAuthorization;
import com.gt.hotel.entity.TErpHotelAuthorizationVS;
import com.gt.hotel.entity.TErpHotelFunction;
import com.gt.hotel.web.service.TErpHotelAuthorizationService;

/**
 * <p>
 * 授权人-功能-关系 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-08-10
 */
@Service
public class TErpHotelAuthorizationServiceImpl extends BaseServiceImpl<TErpHotelAuthorizationDAO, TErpHotelAuthorization> implements TErpHotelAuthorizationService {
	
	@Autowired
	TErpHotelAuthorizationDAO TErpHotelAuthorizationDAO;
	@Autowired
	TErpHotelFunctionDAO TErpHotelFunctionDAO;

	@Override
	public Page<TCommonStaff> selectShopAccountPage(Page<TCommonStaff> page, Integer shopId) {
		Map<String, Object> param = new HashMap<String, Object>();
		Integer c = page.getCurrent();
		Integer s = page.getSize();
		param.put("page", (c-1)*s);
		param.put("pageSize", s);
		param.put("shopId", shopId);
		List<TCommonStaff> list = TErpHotelAuthorizationDAO.selectShopAccountPage(param);
		Integer total = TErpHotelAuthorizationDAO.selectShopAccountPageCount(param);
		page.setTotal(total);
		page.setRecords(list);
		return page;
	}

	@Override
	public List<TErpHotelFunction> selectAuthorFunction() {
		return TErpHotelFunctionDAO.selectList(null);
	}

	@Override
	public Page<TErpHotelAuthorizationVS> selectAuthorPage(Page<TErpHotelAuthorizationVS> page,
			Map<String, Object> param) {
		Integer c = page.getCurrent();
		Integer s = page.getSize();
		param.put("page", (c-1)*s);
		param.put("pageSize", s);
		List<TErpHotelAuthorizationVS> list = TErpHotelAuthorizationDAO.selectAuthorPage(param);
		Integer total = TErpHotelAuthorizationDAO.selectAuthorPageCount(param);
		page.setTotal(total);
		page.setRecords(list);
		return page;
	}
	
}
