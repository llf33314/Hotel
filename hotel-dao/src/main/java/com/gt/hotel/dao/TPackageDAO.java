package com.gt.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.hotel.entity.TPackage;
import com.gt.hotel.param.erp.PackageParamter.PackageQuery;
import com.gt.hotel.vo.erp.PackageVo;

/**
 * <p>
  * 套餐(包含中介/单位) Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-11-24
 */
public interface TPackageDAO extends BaseMapper<TPackage> {

	/**
	 * 套餐 列表
	 * @param hotelId
	 * @param query
	 * @param page
	 * @return
	 */
	List<PackageVo> erpQueryPackage(@Param("hotelId") Integer hotelId, @Param("param") PackageQuery query, @Param("page") Pagination page);

}