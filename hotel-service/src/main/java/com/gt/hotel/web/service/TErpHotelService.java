package com.gt.hotel.web.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TErpHotel;
import com.gt.hotel.entity.TErpHotelAndImage;
import com.gt.hotel.entity.TErpHotelERPSet;
import com.gt.hotel.entity.TErpHotelImage;
import com.gt.hotel.entity.TErpHotelInvoiceRelation;
import com.gt.hotel.entity.TErpHotelMemberCheckOutRelation;
import com.gt.hotel.entity.TErpHotelMemberDepositRelation;
import com.gt.hotel.entity.TErpHotelMobileSet;
import com.gt.hotel.entity.TErpHotelShop;

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

	@Deprecated
	boolean deleteHotel(List<Integer> idList);

	TErpHotelERPSet selectERPSetById(Integer id);

	TErpHotelMobileSet selectMobileOne(Wrapper<TErpHotel> wrapper);

	boolean mobileInfoUpdate(TErpHotelMobileSet erpHotelMobileSet, List<TErpHotelImage> imageList,
			List<Integer> idList, List<TErpHotelInvoiceRelation> invoices);

	boolean deleteHotelBatchIds(List<Integer> idList);

	Page<TErpHotelAndImage> selectHotelAndImagePage(Page<TErpHotelAndImage> page, Map<String, Object> param);

	Page<TErpHotelShop> selectHotelShop(Page<TErpHotelShop> page,
			Map<String, Object> param);

	
}
