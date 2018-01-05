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
import com.gt.hotel.entity.THotelSetting;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.param.HotelParameter.HotelQuery;
import com.gt.hotel.param.HotelParameter.HotelSaveOrUpdate;
import com.gt.hotel.param.erp.ERPParameter.ERPSave;
import com.gt.hotel.vo.HotelMemberSettingVo;
import com.gt.hotel.vo.HotelVo;
import com.gt.hotel.web.service.THotelMemberSettingService;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.THotelSettingService;

/**
 * <p>
 * 酒店主表 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Service
public class THotelServiceImpl extends BaseServiceImpl<THotelDAO, THotel> implements THotelService {

    @Autowired
    THotelDAO tHotelDAO;

    @Autowired
    THotelSettingService tHotelSettingService;
    
    @Autowired
    THotelMemberSettingService tHotelMemberSettingService;

    @SuppressWarnings("unchecked")
    @Override
    public Page<HotelVo> queryHotelHome(Integer busid, HotelQuery hpage) {
        Page<HotelVo> page = hpage.initPage();
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
        for (THotelMemberSetting hms : hmss) {
            HotelMemberSettingVo v = new HotelMemberSettingVo();
            BeanUtils.copyProperties(hms, v);
            hmsv.add(v);
        }
        h.setMemberSetting(hmsv);
        return h;
    }

    @Transactional
    @Override
    public void SaveHotelERP(Integer busid, ERPSave save) {
        THotel h = new THotel();
        h.setId(save.getHotelId());
        h.setLogo(save.getLogo());
        h.setUpdatedAt(new Date());
        h.setUpdatedBy(busid);
        if (!this.updateById(h)) {
        	throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
        }

        for (HotelMemberSettingVo v : save.getMemberSetting()) {
            THotelMemberSetting hotelMemberSetting = new THotelMemberSetting();
            BeanUtils.copyProperties(v, hotelMemberSetting);
            Wrapper<THotelMemberSetting> wrapper = new EntityWrapper<>();
            wrapper.eq("hotel_id", hotelMemberSetting.getHotelId());
            wrapper.eq("vip_level", hotelMemberSetting.getVipLevel());
            THotelMemberSetting selectOne = tHotelMemberSettingService.selectOne(wrapper);
            if(selectOne == null) {
            	tHotelMemberSettingService.insert(hotelMemberSetting);
            }else {
            	tHotelMemberSettingService.update(hotelMemberSetting, wrapper);
            }
        }
    }

	@Override
	public void backHotelCU(Integer busid, HotelSaveOrUpdate hotel) {
		Date date = new Date();
        THotel e = new THotel();
        BeanUtils.copyProperties(hotel, e);
        e.setBusId(busid);
        e.setId(hotel.getHotelId());
        e.setPhone(hotel.getTel());
        e.setAddress(hotel.getAddr());
        e.setShopId(hotel.getShopId());
        if (e.getId() == null) {
            e.setCreatedBy(busid);
            e.setCreatedAt(date);
        }
        e.setUpdatedBy(busid);
        e.setUpdatedAt(date);
        if(!e.insertOrUpdate()) {
        	throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
        }

        if (e.getId() == null) {
            THotelSetting hs = new THotelSetting();
            hs.setHotelId(e.getId());
            if(!tHotelSettingService.insert(hs)) {
            	throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
            }
            List<THotelMemberSetting> hotelMemberSettings = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                THotelMemberSetting hotelMemberSetting = new THotelMemberSetting();
                hotelMemberSetting.setHotelId(e.getId());
                hotelMemberSetting.setVipLevel(i + 1);
                hotelMemberSettings.add(hotelMemberSetting);
            }
        	if(!tHotelMemberSettingService.insertBatch(hotelMemberSettings)) {
        		throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
            }
        }
	}

}
