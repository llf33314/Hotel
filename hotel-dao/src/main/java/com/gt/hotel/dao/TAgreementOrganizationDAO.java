package com.gt.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.hotel.entity.TAgreementOrganization;
import com.gt.hotel.param.AgreementParamter.AgreementQuery;
import com.gt.hotel.vo.AgreementOrganizationVo;

/**
 * <p>
  * 针对协议组织(单位/中介 使用哪种方式的套餐 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-11-24
 */
public interface TAgreementOrganizationDAO extends BaseMapper<TAgreementOrganization> {

	/**
	 * 协议单位or中介列表
	 * @param shopId
	 * @param query
	 * @param page
	 * @return
	 */
	List<AgreementOrganizationVo> erpQueryAgreementOrganization(@Param("shopId") Integer shopId, @Param("param") AgreementQuery query,
			@Param("page") Pagination page);

}