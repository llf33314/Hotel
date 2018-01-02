package com.gt.hotel.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TPackageDAO;
import com.gt.hotel.entity.TPackage;
import com.gt.hotel.param.PackageParamter.PackageQuery;
import com.gt.hotel.vo.PackageVo;
import com.gt.hotel.web.service.TPackageService;

/**
 * <p>
 * 套餐(包含中介/单位) 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-11-24
 */
@Service
public class TPackageServiceImpl extends BaseServiceImpl<TPackageDAO, TPackage> implements TPackageService {

	@Autowired
	TPackageDAO packageDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<PackageVo> erpQueryPackage(Integer hotelId, PackageQuery query) {
		Page<PackageVo> page = query.initPage();
		page.setRecords(packageDAO.erpQueryPackage(hotelId, query, page));
		return page;
	}
	
}
