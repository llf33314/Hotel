package com.gt.hotel.web.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TAgreementOrganization;
import com.gt.hotel.param.erp.AgreementParamter.AgreementInsert;
import com.gt.hotel.param.erp.AgreementParamter.AgreementQuery;
import com.gt.hotel.param.erp.AgreementParamter.ReceivablesQuery;
import com.gt.hotel.param.erp.HandingParam.HandingQuery;
import com.gt.hotel.vo.erp.AgreementOrganizationVo;
import com.gt.hotel.vo.erp.HandingStatisticsVo;
import com.gt.hotel.vo.erp.HandingVo;
import com.gt.hotel.vo.erp.ReceivablesVo;

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

	/**
	 * 单位or中介 收款查询 列表
	 * @param hotelId
	 * @param query
	 * @return
	 */
	Page<ReceivablesVo> erpQueryReceivables(Integer hotelId, ReceivablesQuery query);

	/**
	 * 总收款
	 * @param hotelId
	 * @return
	 */
	Long erpQueryReceivablesTotalPrice(Integer hotelId);

	/**
	 * 挂账管理 - 挂账列表
	 * @param hotelId
	 * @param param
	 * @return
	 */
	Page<HandingVo> erpQueryHandingList(Integer hotelId, HandingQuery param);

	/**
	 * 挂账 - 统计
	 * @param hotelId
	 * @return
	 */
	HandingStatisticsVo erpQueryHandingStatistics(Integer hotelId);

}
