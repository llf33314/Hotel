package com.gt.hotel.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TErpHotelFoodOrder;
import com.gt.hotel.entity.TErpHotelFoodOrderVO;

/**
 * <p>
  * ERP酒店-餐饮-订单 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-08-17
 */
public interface TErpHotelFoodOrderDAO extends BaseMapper<TErpHotelFoodOrder> {

	List<TErpHotelFoodOrderVO> selectFoodOrderVOList(Map<String, Object> param);
	
	Integer selectFoodOrderVOListCount(Map<String, Object> param);

	void updateOrderDel(List<Integer> idList);

	Integer updateStatus(TErpHotelFoodOrder po);
}