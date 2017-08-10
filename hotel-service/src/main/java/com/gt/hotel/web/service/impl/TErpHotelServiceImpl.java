package com.gt.hotel.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TErpHotelDAO;
import com.gt.hotel.dao.TErpHotelImageDAO;
import com.gt.hotel.dao.TErpHotelInstallationRelationDAO;
import com.gt.hotel.entity.TErpHotel;
import com.gt.hotel.entity.TErpHotelImage;
import com.gt.hotel.entity.TErpHotelInstallationRelation;
import com.gt.hotel.entity.TErpHotelMemberCheckOutRelation;
import com.gt.hotel.entity.TErpHotelMemberDepositRelation;
import com.gt.hotel.web.service.TErpHotelMemberCheckOutRelationService;
import com.gt.hotel.web.service.TErpHotelMemberDepositRelationService;
import com.gt.hotel.web.service.TErpHotelService;

/**
 * <p>
 * ERP酒店主表 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-08-07
 */
@Service
public class TErpHotelServiceImpl extends BaseServiceImpl<TErpHotelDAO, TErpHotel> implements TErpHotelService {
	
	@Autowired
	TErpHotelMemberDepositRelationService tErpHotelMemberDepositRelationService;
	@Autowired
	TErpHotelMemberCheckOutRelationService tErpHotelMemberCheckOutRelationService; 
	@Autowired
	TErpHotelImageDAO tErpHotelImageDAO;
	@Autowired
	TErpHotelInstallationRelationDAO tErpHotelInstallationRelationDAO;

	@Transactional
	@Override
	public boolean hotelErpSet(TErpHotel hotel, TErpHotelImage hotelImage,
			List<TErpHotelMemberDepositRelation> depositList, List<TErpHotelMemberCheckOutRelation> checkOutList) {
		boolean flag = false;
		this.updateById(hotel);
		tErpHotelImageDAO.insert(hotelImage);
		tErpHotelMemberDepositRelationService.insertBatch(depositList);
		tErpHotelMemberCheckOutRelationService.insertBatch(checkOutList);
		flag = true;
		return flag;
	}

	@Transactional
	@Override
	public boolean deleteHotel(List<Integer> idList) {
		boolean flag = false;
		
		this.deleteBatchIds(idList);
		Wrapper<TErpHotelImage> wrapper = new EntityWrapper<TErpHotelImage>();
		Wrapper<TErpHotelMemberDepositRelation> wrapperI = new EntityWrapper<TErpHotelMemberDepositRelation>();
		Wrapper<TErpHotelMemberCheckOutRelation> wrapperII = new EntityWrapper<TErpHotelMemberCheckOutRelation>();
		Wrapper<TErpHotelInstallationRelation> wrapperIII = new EntityWrapper<TErpHotelInstallationRelation>();
		wrapper.eq("subjection", 0);
		wrapper.in("subjection_id", idList);
		wrapperI.in("hotel_id", idList);
		wrapperII.in("hotel_id", idList);
		wrapperIII.in("hotel_id", idList);
		tErpHotelImageDAO.delete(wrapper);
		tErpHotelMemberDepositRelationService.delete(wrapperI);
		tErpHotelMemberCheckOutRelationService.delete(wrapperII);
		tErpHotelInstallationRelationDAO.delete(wrapperIII);
		
		flag = true;
		return flag;
	}
	
}
