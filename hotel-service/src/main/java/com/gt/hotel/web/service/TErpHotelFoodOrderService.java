package com.gt.hotel.web.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TErpHotelFoodOrder;
import com.gt.hotel.entity.TErpHotelFoodOrderVO;

/**
 * <p>
 * ERP酒店-餐饮-订单 服务类
 * </p>
 *
 * @author 
 * @since 2017-08-17
 */
public interface TErpHotelFoodOrderService extends BaseService<TErpHotelFoodOrder> {

	Page<TErpHotelFoodOrderVO> selectFoodOrderPage(Page<TErpHotelFoodOrderVO> page, Map<String, Object> param);

	List<TErpHotelFoodOrderVO> selectFoodOrderExport(Map<String, Object> param);

	boolean deleteFoodOrder(List<Integer> idList);

	boolean updateStatus(TErpHotelFoodOrder po);
	
}
