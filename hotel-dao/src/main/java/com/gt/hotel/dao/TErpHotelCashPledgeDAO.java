package com.gt.hotel.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TErpHotelCashPledge;
import com.gt.hotel.entity.TErpHotelCashPledgeVB;

/**
 * <p>
  * ERP酒店-押金 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-09-05
 */
public interface TErpHotelCashPledgeDAO extends BaseMapper<TErpHotelCashPledge> {

	List<TErpHotelCashPledgeVB> selectCPVBPage(Map<String, Object> param);

	Integer selectCPVBPageCount(Map<String, Object> param);

	Integer refundsUpdate(Map<String, Object> param);

}