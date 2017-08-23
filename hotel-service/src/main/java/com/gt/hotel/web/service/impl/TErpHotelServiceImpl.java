package com.gt.hotel.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TErpHotelDAO;
import com.gt.hotel.dao.TErpHotelImageDAO;
import com.gt.hotel.dao.TErpHotelInstallationDAO;
import com.gt.hotel.entity.TErpHotel;
import com.gt.hotel.entity.TErpHotelERPSet;
import com.gt.hotel.entity.TErpHotelImage;
import com.gt.hotel.entity.TErpHotelInstallation;
import com.gt.hotel.entity.TErpHotelInstallationRelation;
import com.gt.hotel.entity.TErpHotelMemberCheckOutRelation;
import com.gt.hotel.entity.TErpHotelMemberDepositRelation;
import com.gt.hotel.entity.TErpHotelMobileSet;
import com.gt.hotel.web.service.TErpHotelImageService;
import com.gt.hotel.web.service.TErpHotelInstallationRelationService;
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
	TErpHotelImageService TErpHotelImageService;
	@Autowired
	TErpHotelInstallationRelationService tErpHotelInstallationRelationDAO;
	@Autowired
	TErpHotelInstallationDAO TErpHotelInstallationDAO;
	
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

	@Override
	public TErpHotelMobileSet selectMobileOne(Wrapper<TErpHotel> wrapper) {
		TErpHotelMobileSet ms = new TErpHotelMobileSet();
		TErpHotel h = this.selectOne(wrapper);
		ms.setId(h.getId());
		ms.setIfReserveMan(h.getIfReserveMan());
		ms.setIfReservePhone(h.getIfReservePhone());
		ms.setIfRemark(h.getIfRemark());
		ms.setPayMode(h.getPayMode());
		ms.setIfSms(h.getIfSms());
		ms.setSmsPhone(h.getSmsPhone());
		ms.setIfCheckOut(h.getIfCheckOut());
		ms.setIfFood(h.getIfFood());
		ms.setIfBulletin(h.getIfBulletin());
		ms.setBulletin(h.getBulletin());
		ms.setIfRemnantRoom(h.getIfRemnantRoom());
		ms.setIfContinue(h.getIfContinue());
		ms.setIfConfirmInfo(h.getIfConfirmInfo());
		Wrapper<TErpHotelImage> i_wrapper = new EntityWrapper<TErpHotelImage>();
		i_wrapper.eq("subjection_id", h.getId());
		i_wrapper.eq("subjection", 0);
		i_wrapper.eq("type", "酒店图片");
		List<TErpHotelImage> images = tErpHotelImageDAO.selectList(i_wrapper);
		ms.setImages(images);
		List<TErpHotelInstallation> installations = TErpHotelInstallationDAO.selectByHotelId(h.getId());
		ms.setInstallations(installations);
		return ms;
	}

	@Transactional
	@Override
	public boolean mobileInfoUpdate(TErpHotelMobileSet es, List<TErpHotelImage> imageList,
			List<Integer> idList) {
		boolean flag = false;
		TErpHotel h = this.selectById(es.getId());
		h.setId(es.getId());
		h.setIfReserveMan(es.getIfReserveMan());
		h.setIfReservePhone(es.getIfReservePhone());
		h.setIfRemark(es.getIfRemark());
		h.setPayMode(es.getPayMode());
		h.setIfSms(es.getIfSms());
		h.setSmsPhone(es.getSmsPhone());
		h.setIfCheckOut(es.getIfCheckOut());
		h.setIfFood(es.getIfFood());
		h.setIfBulletin(es.getIfBulletin());
		h.setBulletin(es.getBulletin());
		h.setIfRemnantRoom(es.getIfRemnantRoom());
		h.setIfContinue(es.getIfContinue());
		h.setIfConfirmInfo(es.getIfConfirmInfo());
		this.insertOrUpdate(h);
		if(imageList.size() > 0){
			Wrapper<TErpHotelImage> iw = new EntityWrapper<TErpHotelImage>();
			iw.eq("subjection_id", h.getId());
			iw.eq("subjection", 0);
			iw.eq("type", "酒店图片");
			tErpHotelImageDAO.delete(iw);
			for(TErpHotelImage i : imageList){
				i.setSubjectionId(h.getId());
				i.setSubjection(0);
				i.setType("酒店图片");
			}
			TErpHotelImageService.insertBatch(imageList);
		}
		if(idList.size() > 0){
			Wrapper<TErpHotelInstallationRelation> hi = new EntityWrapper<TErpHotelInstallationRelation>();
			hi.eq("hotel_id", h.getId());
			tErpHotelInstallationRelationDAO.delete(hi);
			List<TErpHotelInstallationRelation> ir = new ArrayList<TErpHotelInstallationRelation>();
			for(Integer i : idList){
				TErpHotelInstallationRelation t = new TErpHotelInstallationRelation();
				t.setHotelId(h.getId());
				t.setInstallationId(i);
				ir.add(t);
			}
			tErpHotelInstallationRelationDAO.insertBatch(ir);
		}
		flag = true;
		return flag;
	}
	
	
	
	
	
	
}
