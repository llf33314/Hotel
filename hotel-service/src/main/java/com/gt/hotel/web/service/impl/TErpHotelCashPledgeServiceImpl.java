package com.gt.hotel.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TErpHotelCashPledgeDAO;
import com.gt.hotel.entity.TErpHotelCashPledge;
import com.gt.hotel.entity.TErpHotelCashPledgeVB;
import com.gt.hotel.web.service.TErpHotelCashPledgeService;

/**
 * <p>
 * ERP酒店-押金 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-09-05
 */
@Service
public class TErpHotelCashPledgeServiceImpl extends BaseServiceImpl<TErpHotelCashPledgeDAO, TErpHotelCashPledge> implements TErpHotelCashPledgeService {
	
	@Autowired
	TErpHotelCashPledgeDAO TErpHotelCashPledgeDAO;

	@Override
	public Page<TErpHotelCashPledgeVB> selectCPVBPage(Page<TErpHotelCashPledgeVB> page, Map<String, Object> param) {
		Integer c = page.getCurrent();
		Integer s = page.getSize();
		param.put("page", (c-1)*s);
		param.put("pageSize", s);
		List<TErpHotelCashPledgeVB> list = TErpHotelCashPledgeDAO.selectCPVBPage(param);
		Integer total = TErpHotelCashPledgeDAO.selectCPVBPageCount(param);
		page.setTotal(total);
		page.setRecords(list);
		return page;
	}

	@Override
	public boolean refundsUpdate(Map<String, Object> param) {
		boolean flag = false;
		int i = TErpHotelCashPledgeDAO.refundsUpdate(param);
		flag = i == 0 ? false : true;
		return flag;
	}
	
}
