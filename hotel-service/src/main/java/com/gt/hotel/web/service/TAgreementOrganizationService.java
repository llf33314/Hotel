package com.gt.hotel.web.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TAgreementOrganization;
import com.gt.hotel.param.AgreementParamter.AgreementInsert;
import com.gt.hotel.param.AgreementParamter.AgreementQuery;
import com.gt.hotel.vo.AgreementOrganizationVo;

/**
 * <p>
 * 针对协议组织(单位/中介 使用哪种方式的套餐 服务类
 * </p>
 *
 * @author 
 * @since 2017-11-24
 */
public interface TAgreementOrganizationService extends BaseService<TAgreementOrganization> {

	/**
	 * 协议单位or中介列表
	 * @param hotelId
	 * @param query
	 * @return
	 */
	Page<AgreementOrganizationVo> erpQueryAgreementOrganization(Integer hotelId, AgreementQuery query);

	/**
	 * 编辑 协议单位or中介
	 * @param busId
	 * @param hotelId
	 * @param insert
	 */
	void erpInsertAgreementOrganization(Integer busId, Integer hotelId, AgreementInsert insert);

	/**
	 * 协议单位or中介 详情
	 * @param id
	 * @return
	 */
	AgreementOrganizationVo erpQueryAgreementOrganizationDetail(Integer id);
	
}
