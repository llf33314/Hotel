package com.gt.hotel.web.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.api.bean.session.BusUser;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TPackage;
import com.gt.hotel.param.erp.PackageParamter.PackageCU;
import com.gt.hotel.param.erp.PackageParamter.PackageQuery;
import com.gt.hotel.vo.erp.PackageVo;

/**
 * <p>
 * 套餐(包含中介/单位) 服务类
 * </p>
 *
 * @author 
 * @since 2017-11-24
 */
public interface TPackageService extends BaseService<TPackage> {

	/**
	 * 套餐 列表
	 * @param hotelId
	 * @param query
	 * @return
	 */
	Page<PackageVo> erpQueryPackage(Integer hotelId, PackageQuery query);

	/**
	 * 套餐 - 新增/编辑
	 * @param user
	 * @param packageCU
	 */
	void erpPackageCU(BusUser user, PackageCU packageCU);
	
}
