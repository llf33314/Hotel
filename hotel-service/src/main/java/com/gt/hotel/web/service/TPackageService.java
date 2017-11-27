package com.gt.hotel.web.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TPackage;
import com.gt.hotel.param.PackageParamter.PackageQuery;
import com.gt.hotel.vo.PackageVo;

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
	 * @param shopId
	 * @param query
	 * @return
	 */
	Page<PackageVo> erpQueryPackage(Integer shopId, PackageQuery query);
	
}
