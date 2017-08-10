package com.gt.hotel.web.service;

import java.util.List;

import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TErpHotel;
import com.gt.hotel.entity.TErpHotelImage;
import com.gt.hotel.entity.TErpHotelMemberCheckOutRelation;
import com.gt.hotel.entity.TErpHotelMemberDepositRelation;

/**
 * <p>
 * ERP酒店主表 服务类
 * </p>
 *
 * @author 
 * @since 2017-08-07
 */
public interface TErpHotelService extends BaseService<TErpHotel> {

	boolean hotelErpSet(TErpHotel hotel, TErpHotelImage hotelImage, List<TErpHotelMemberDepositRelation> depositList,
			List<TErpHotelMemberCheckOutRelation> checkOutList);

	boolean deleteHotel(List<Integer> idList);

	
}
