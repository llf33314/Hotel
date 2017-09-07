package com.gt.hotel.web.service;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TErpHotelCashPledge;
import com.gt.hotel.entity.TErpHotelCashPledgeVB;

/**
 * <p>
 * ERP酒店-押金 服务类
 * </p>
 *
 * @author 
 * @since 2017-09-05
 */
public interface TErpHotelCashPledgeService extends BaseService<TErpHotelCashPledge> {

	Page<TErpHotelCashPledgeVB> selectCPVBPage(Page<TErpHotelCashPledgeVB> page, Map<String, Object> param);

	boolean refundsUpdate(Map<String, Object> param);

}
