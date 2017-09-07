package com.gt.hotel.web.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TCommonStaff;
import com.gt.hotel.entity.TErpHotelAuthorization;
import com.gt.hotel.entity.TErpHotelAuthorizationVS;
import com.gt.hotel.entity.TErpHotelFunction;

/**
 * <p>
 * 授权人-功能-关系 服务类
 * </p>
 *
 * @author 
 * @since 2017-08-10
 */
public interface TErpHotelAuthorizationService extends BaseService<TErpHotelAuthorization> {

	Page<TCommonStaff> selectShopAccountPage(Page<TCommonStaff> page, Integer shopId);

	List<TErpHotelFunction> selectAuthorFunction();

	Page<TErpHotelAuthorizationVS> selectAuthorPage(Page<TErpHotelAuthorizationVS> page, Map<String, Object> param);
	
}
