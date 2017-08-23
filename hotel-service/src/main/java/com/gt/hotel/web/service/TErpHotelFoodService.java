package com.gt.hotel.web.service;

import java.util.List;

import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TErpHotelFood;
import com.gt.hotel.entity.TErpHotelFoodVO;
import com.gt.hotel.entity.TErpHotelImage;

/**
 * <p>
 * ERP酒店菜品 服务类
 * </p>
 *
 * @author 
 * @since 2017-08-16
 */
public interface TErpHotelFoodService extends BaseService<TErpHotelFood> {

	boolean insertOrUpdate(TErpHotelFood food, List<TErpHotelImage> images);

	boolean deleteBatchIdsANDImage(List<Integer> idList);

	TErpHotelFoodVO selectPageFoodVO(Integer id);

	List<Integer> selectFoodIdsByHotelIds(List<Integer> idList);
	
}
