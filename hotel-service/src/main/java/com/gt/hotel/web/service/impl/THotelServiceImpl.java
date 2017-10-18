package com.gt.hotel.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.THotelDAO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.entity.THotelMemberSetting;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.param.ERPParameter.Save;
import com.gt.hotel.param.HotelParameter;
import com.gt.hotel.vo.HotelMemberSettingVo;
import com.gt.hotel.vo.HotelVo;
import com.gt.hotel.web.service.THotelMemberSettingService;
import com.gt.hotel.web.service.THotelService;

/**
 * <p>
 * 酒店主表 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Service
public class THotelServiceImpl extends BaseServiceImpl< THotelDAO, THotel > implements THotelService {

    @Autowired
    THotelDAO tHotelDAO;
    
    @Autowired
    THotelMemberSettingService tHotelMemberSettingService;

	@Override
	public Page<HotelVo> queryHotelHome(Integer busid, HotelParameter.Query hpage, Page<HotelVo> page) {
		page.setRecords(tHotelDAO.queryHotelHome(page, hpage, busid));
		return page;
	}

	@Override
	public HotelVo queryHotelERP(Integer hotelId) {
		THotel th = tHotelDAO.selectById(hotelId);
		HotelVo h = new HotelVo();
		BeanUtils.copyProperties(th, h);
		
		Wrapper<THotelMemberSetting> wrapper = new EntityWrapper<>();
		wrapper.eq("hotel_id", hotelId);
		List<THotelMemberSetting> hmss = tHotelMemberSettingService.selectList(wrapper);
		List<HotelMemberSettingVo> hmsv = new ArrayList<>();
		for(THotelMemberSetting hms : hmss){
			HotelMemberSettingVo v = new HotelMemberSettingVo();
			BeanUtils.copyProperties(hms, v);
			hmsv.add(v);
		}
		h.setMemberSetting(hmsv);
		return h;
	}

	@Transactional
	@Override
	public void SaveHotelERP(Integer busid, Save save) {
		THotel h = new THotel();
		h.setId(save.getHotelId());
		h.setLogo(save.getLogo());
		h.setUpdatedAt(new Date());
		h.setUpdatedBy(busid);
		if(this.updateById(h)) throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
		
		List<THotelMemberSetting> mss = new ArrayList<>();
		for(HotelMemberSettingVo v : save.getMemberSetting()){
			THotelMemberSetting _v = new THotelMemberSetting();
			BeanUtils.copyProperties(v, _v);
			mss.add(_v);
		}
		if(tHotelMemberSettingService.insertOrUpdateBatch(mss)) throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
	}
	
}
