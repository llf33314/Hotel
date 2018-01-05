package com.gt.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.hotel.entity.TAgreementOrganization;
import com.gt.hotel.param.erp.AgreementParamter.AgreementQuery;
import com.gt.hotel.param.erp.AgreementParamter.ReceivablesQuery;
import com.gt.hotel.vo.erp.AgreementOrganizationVo;
import com.gt.hotel.vo.erp.ReceivablesCustomersVo;
import com.gt.hotel.vo.erp.ReceivablesRoomVo;
import com.gt.hotel.vo.erp.ReceivablesVo;

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
	 * @param hotelId
	 * @param query
	 * @param page
	 * @return
	 */
	List<AgreementOrganizationVo> erpQueryAgreementOrganization(@Param("hotelId") Integer hotelId, @Param("param") AgreementQuery query,
			@Param("page") Pagination page);

	/**
	 * 单位or中介 收款查询 列表
	 * @param hotelId
	 * @param query
	 * @return
	 */
	List<ReceivablesVo> erpQueryReceivables(@Param("hotelId") Integer hotelId, @Param("param") ReceivablesQuery query, @Param("page") Pagination page);
	
	List<ReceivablesCustomersVo> erpQueryReceivablesCustomers(@Param("orderId") Integer orderId);

	List<ReceivablesRoomVo> erpQueryReceivablesRoom(@Param("orderId") Integer orderId);

	Long erpQueryReceivablesTotalPrice(Integer hotelId);
	
}