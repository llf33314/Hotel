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
import com.gt.hotel.entity.TErpHotelERPSet;
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
		if(hotelImage != null){
			Wrapper<TErpHotelImage> wrapper = new EntityWrapper<TErpHotelImage>();
			wrapper.eq("subjection", hotelImage.getSubjection());
			wrapper.eq("subjection_id", hotelImage.getSubjectionId());
			wrapper.eq("type", hotelImage.getType());
			tErpHotelImageDAO.delete(wrapper);
			tErpHotelImageDAO.insert(hotelImage);
		}
		if(depositList != null){
			Wrapper<TErpHotelMemberDepositRelation> wrapperI = new EntityWrapper<TErpHotelMemberDepositRelation>();
			wrapperI.eq("hotel_id", hotel.getId());
			tErpHotelMemberDepositRelationService.delete(wrapperI);
			tErpHotelMemberDepositRelationService.insertBatch(depositList);
		}
		if(checkOutList != null){
			Wrapper<TErpHotelMemberCheckOutRelation> wrapperII = new EntityWrapper<TErpHotelMemberCheckOutRelation>();
			wrapperII.eq("hotel_id", hotel.getId());
			tErpHotelMemberCheckOutRelationService.delete(wrapperII);
			tErpHotelMemberCheckOutRelationService.insertBatch(checkOutList);
		}
//		Wrapper<TErpHotelInstallationRelation> wrapperIII = new EntityWrapper<TErpHotelInstallationRelation>();
//		wrapperIII.eq("hotel_id", hotel.getId());
		flag = true;
		return flag;
	}

	@Transactional
	@Override
	public boolean deleteHotel(List<Integer> idList) {
		boolean flag = false;
		if(idList != null){
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
		}
		return flag;
	}

	@Transactional
	@Override
	public TErpHotelERPSet selectERPSetById(Integer id) {
		TErpHotelERPSet hotel = new TErpHotelERPSet();
		TErpHotel h = selectById(id);
		TErpHotelImage i = new TErpHotelImage();
		i.setSubjection(0);
		i.setSubjectionId(id);
		i.setType("logo");
		i = tErpHotelImageDAO.selectOne(i);
		Wrapper<TErpHotelMemberDepositRelation> wrapper = new EntityWrapper<TErpHotelMemberDepositRelation>();
		Wrapper<TErpHotelMemberCheckOutRelation> wrapper2 = new EntityWrapper<TErpHotelMemberCheckOutRelation>();
		wrapper.eq("hotel_id", id);
		wrapper2.eq("hotel_id", id);
		List<TErpHotelMemberDepositRelation> d = tErpHotelMemberDepositRelationService.selectList(wrapper);
		List<TErpHotelMemberCheckOutRelation> c = tErpHotelMemberCheckOutRelationService.selectList(wrapper2);
		hotel.setHotelId(h.getId());
		hotel.setIfBreakfast(h.getIfBreakfast());
		hotel.setIfFreeDeposit(h.getIfFreeDeposit());
		hotel.setIfLastCheckOut(h.getIfLastCheckOut());
		hotel.setBreakfastQuantity(h.getBreakfastQuantity());
		hotel.setLogo(i.getUrl());
		hotel.setDeposits(d);
		hotel.setCheckOuts(c);
		return hotel;
	}
	
}
