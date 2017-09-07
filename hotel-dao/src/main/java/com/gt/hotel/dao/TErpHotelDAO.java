package com.gt.hotel.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TErpHotel;
import com.gt.hotel.entity.TErpHotelAndImage;
import com.gt.hotel.entity.TErpHotelShop;

/**
 * <p>
  * ERP酒店主表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-08-07
 */
public interface TErpHotelDAO extends BaseMapper<TErpHotel> {

	List<TErpHotelAndImage> selectHotelAndImagePage(Map<String, Object> param);

	Integer selectHotelAndImagePageCount(Map<String, Object> param);

	List<TErpHotelShop> selectHotelShop(Map<String, Object> param);

	Integer selectHotelShopCount(Map<String, Object> param);
}