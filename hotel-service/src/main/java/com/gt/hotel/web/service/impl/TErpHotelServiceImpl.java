package com.gt.hotel.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TErpHotelDAO;
import com.gt.hotel.dao.TErpHotelImageDAO;
import com.gt.hotel.dao.TErpHotelInstallationDAO;
import com.gt.hotel.entity.TErpHotel;
import com.gt.hotel.entity.TErpHotelAndImage;
import com.gt.hotel.entity.TErpHotelERPSet;
import com.gt.hotel.entity.TErpHotelImage;
import com.gt.hotel.entity.TErpHotelInstallation;
import com.gt.hotel.entity.TErpHotelInstallationRelation;
import com.gt.hotel.entity.TErpHotelInvoiceRelation;
import com.gt.hotel.entity.TErpHotelMemberCheckOutRelation;
import com.gt.hotel.entity.TErpHotelMemberDepositRelation;
import com.gt.hotel.entity.TErpHotelMobileSet;
import com.gt.hotel.entity.TErpHotelShop;
import com.gt.hotel.web.service.TErpHotelActivityService;
import com.gt.hotel.web.service.TErpHotelFoodService;
import com.gt.hotel.web.service.TErpHotelImageService;
import com.gt.hotel.web.service.TErpHotelInstallationRelationService;
import com.gt.hotel.web.service.TErpHotelInvoiceRelationService;
import com.gt.hotel.web.service.TErpHotelMemberCheckOutRelationService;
import com.gt.hotel.web.service.TErpHotelMemberDepositRelationService;
import com.gt.hotel.web.service.TErpHotelRoomService;
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
	TErpHotelDAO TErpHotelDAO;
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
	@Autowired
	TErpHotelRoomService TErpHotelRoomService;
	@Autowired
	TErpHotelFoodService TErpHotelFoodService;
	@Autowired
	TErpHotelActivityService TErpHotelActivityService;
	@Autowired
	TErpHotelInvoiceRelationService TErpHotelInvoiceRelationService;
	
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
//			this.deleteBatchIds(idList);
//			Wrapper<TErpHotelImage> wrapper = new EntityWrapper<TErpHotelImage>();
//			Wrapper<TErpHotelMemberDepositRelation> wrapperI = new EntityWrapper<TErpHotelMemberDepositRelation>();
//			Wrapper<TErpHotelMemberCheckOutRelation> wrapperII = new EntityWrapper<TErpHotelMemberCheckOutRelation>();
//			Wrapper<TErpHotelInstallationRelation> wrapperIII = new EntityWrapper<TErpHotelInstallationRelation>();
//			wrapper.eq("subjection", 0);
//			wrapper.in("subjection_id", idList);
//			wrapperI.in("hotel_id", idList);
//			wrapperII.in("hotel_id", idList);
//			wrapperIII.in("hotel_id", idList);
//			tErpHotelImageDAO.delete(wrapper);
//			tErpHotelMemberDepositRelationService.delete(wrapperI);
//			tErpHotelMemberCheckOutRelationService.delete(wrapperII);
//			tErpHotelInstallationRelationDAO.delete(wrapperIII);
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
		hotel.setLogo(i != null?i.getUrl():null);
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
		i_wrapper.eq("subjection", TErpHotelImage.HOTEL);
		i_wrapper.eq("type", "酒店图片");
		List<TErpHotelImage> images = tErpHotelImageDAO.selectList(i_wrapper);
		ms.setImages(images);
		List<TErpHotelInstallation> installations = TErpHotelInstallationDAO.selectByHotelId(h.getId());
		ms.setInstallations(installations);
		Wrapper<TErpHotelInvoiceRelation> in_wrapper = new EntityWrapper<TErpHotelInvoiceRelation>();
		in_wrapper.eq("hotel_id", h.getId());
		List<TErpHotelInvoiceRelation> invoices = TErpHotelInvoiceRelationService.selectList(in_wrapper);
		ms.setInvoices(invoices);
		return ms;
	}

	@Transactional
	@Override
	public boolean mobileInfoUpdate(TErpHotelMobileSet es, List<TErpHotelImage> imageList,
			List<Integer> idList, List<TErpHotelInvoiceRelation> invoices) {
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
		h.setIfGroupBuy(es.getIfGroupBuy());
		h.setIfSpike(es.getIfSpike());
		h.setIfHour(es.getIfHour());
		h.setIfSpecial(es.getIfSpecial());

		h.setFoodPayMode(es.getFoodPayMode());
		h.setCheckOutPhone(es.getCheckOutPhone());
		h.setIfInvoice(es.getIfInvoice());
		h.setNeekPrompt(es.getNeekPrompt());
		h.setUnneekPrompt(es.getUnneekPrompt());
		
		this.insertOrUpdate(h);
		if(imageList.size() > 0){
			Wrapper<TErpHotelImage> iw = new EntityWrapper<TErpHotelImage>();
			iw.eq("subjection_id", h.getId());
			iw.eq("subjection", TErpHotelImage.HOTEL);
			iw.eq("type", "酒店图片");
			tErpHotelImageDAO.delete(iw);
			for(TErpHotelImage i : imageList){
				i.setSubjectionId(h.getId());
				i.setSubjection(TErpHotelImage.HOTEL);
				i.setType("酒店图片");
			}
			TErpHotelImageService.insertBatch(imageList);
		}
		if(invoices.size() > 0){
			Wrapper<TErpHotelInvoiceRelation> wrapper_inv = new EntityWrapper<TErpHotelInvoiceRelation>();
			wrapper_inv.eq("hotel_id", invoices.get(0).getHotelId());
			TErpHotelInvoiceRelationService.delete(wrapper_inv);
			TErpHotelInvoiceRelationService.insertBatch(invoices);
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

	@Transactional
	@Override
	public boolean deleteHotelBatchIds(List<Integer> idList) {
		boolean flag = false;
		if(idList != null){
			this.deleteBatchIds(idList);
			Wrapper<TErpHotelImage> wrapper = new EntityWrapper<TErpHotelImage>();
			wrapper.in("subjection_id", idList);
			wrapper.eq("subjection", TErpHotelImage.HOTEL);
			TErpHotelImageService.delete(wrapper);
			Wrapper<TErpHotelMemberDepositRelation> wrapperI = new EntityWrapper<TErpHotelMemberDepositRelation>();
			wrapperI.in("hotel_id", idList);
			tErpHotelMemberDepositRelationService.delete(wrapperI);
			Wrapper<TErpHotelMemberCheckOutRelation> wrapperII = new EntityWrapper<TErpHotelMemberCheckOutRelation>();
			wrapperII.in("hotel_id", idList);
			tErpHotelMemberCheckOutRelationService.delete(wrapperII);
			Wrapper<TErpHotelInstallationRelation> wrapperIII = new EntityWrapper<TErpHotelInstallationRelation>();
			wrapperIII.in("hotel_id", idList);
			tErpHotelInstallationRelationDAO.delete(wrapperIII);
			List<Integer> roomIdList = TErpHotelRoomService.selectRoomIdsByHotelIds(idList);
			if(roomIdList != null && roomIdList.size() > 0) TErpHotelRoomService.delRoom(roomIdList);
			List<Integer> foodIdList = TErpHotelFoodService.selectFoodIdsByHotelIds(idList);
			if(foodIdList != null && foodIdList.size() > 0) TErpHotelFoodService.deleteBatchIdsANDImage(foodIdList);
			List<Integer> activityIdList = TErpHotelActivityService.selectActivityIdsByHotelIds(idList);
			if(activityIdList != null && activityIdList.size() > 0) TErpHotelActivityService.delHotelActivity(activityIdList);
			flag = true;
		}
		return flag;
	}

	@Override
	public Page<TErpHotelAndImage> selectHotelAndImagePage(Page<TErpHotelAndImage> page,
			Map<String, Object> param) {
		Integer c = page.getCurrent();
		Integer s = page.getSize();
		param.put("page", (c-1)*s);
		param.put("pageSize", s);
		List<TErpHotelAndImage> list = TErpHotelDAO.selectHotelAndImagePage(param);
		Integer total = TErpHotelDAO.selectHotelAndImagePageCount(param);
		page.setTotal(total);
//		page.setPages((total + s -1) / s);
		page.setRecords(list);
		return page;
	}

	@Override
	public Page<TErpHotelShop> selectHotelShop(Page<TErpHotelShop> page,
			Map<String, Object> param) {
		Integer c = page.getCurrent();
		Integer s = page.getSize();
		param.put("page", (c-1)*s);
		param.put("pageSize", s);
		List<TErpHotelShop> list = TErpHotelDAO.selectHotelShop(param);
		Integer total = TErpHotelDAO.selectHotelShopCount(param);
		page.setTotal(total);
//		page.setPages((total + s -1) / s);
		page.setRecords(list);
		return page;
	}
	
	
	
}
