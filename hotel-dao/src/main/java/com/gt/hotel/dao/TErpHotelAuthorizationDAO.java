package com.gt.hotel.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TCommonStaff;
import com.gt.hotel.entity.TErpHotelAuthorization;

/**
 * <p>
  * 授权人-功能-关系 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-08-10
 */
public interface TErpHotelAuthorizationDAO extends BaseMapper<TErpHotelAuthorization> {

	List<TCommonStaff> selectShopAccountPage(Map<String, Object> param);

	Integer selectShopAccountPageCount(Map<String, Object> param);

}