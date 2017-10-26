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
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dao.THotelSettingDAO;
import com.gt.hotel.dao.TInfrastructureDAO;
import com.gt.hotel.entity.TFileRecord;
import com.gt.hotel.entity.THotelSetting;
import com.gt.hotel.entity.TInfrastructure;
import com.gt.hotel.entity.TInfrastructureRelation;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.param.HotelMobileParameter.MobileSaveOrUpdate;
import com.gt.hotel.param.InfrastructureRelationParamter;
import com.gt.hotel.vo.FileRecordVo;
import com.gt.hotel.vo.HotelSettingVo;
import com.gt.hotel.vo.InfrastructureRelationVo;
import com.gt.hotel.vo.InfrastructureVo;
import com.gt.hotel.web.service.TFileRecordService;
import com.gt.hotel.web.service.THotelSettingService;
import com.gt.hotel.web.service.TInfrastructureRelationService;

/**
 * <p>
 * 酒店移动端设置 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Service
public class THotelSettingServiceImpl extends BaseServiceImpl< THotelSettingDAO, THotelSetting > implements THotelSettingService {
	
	@Autowired
	TFileRecordService tFileRecordService;

	@Autowired
	TInfrastructureRelationService tInfrastructureRelationService;
	
	@Autowired
	TInfrastructureDAO tInfrastructureDAO;

	@Override
	public HotelSettingVo queryHotelSettingOne(Integer hotelId) {
		HotelSettingVo s = new HotelSettingVo();
		Wrapper<THotelSetting> hsw = new EntityWrapper<>();
		hsw.eq("hotel_id", hotelId);
		THotelSetting hs = this.selectOne(hsw);
		if(hs == null) {
			return s;
		}
		BeanUtils.copyProperties(hs, s);
		
		Wrapper<TFileRecord> rsw = new EntityWrapper<>();
		rsw.eq("module", CommonConst.MODULE_HOTEL);
		rsw.eq("reference_id", hotelId);
		rsw.eq("mark_modified", CommonConst.ENABLED);
		List<TFileRecord> frs = tFileRecordService.selectList(rsw);
		List<FileRecordVo> imageurls = new ArrayList<>();
		for(TFileRecord fr : frs){
			FileRecordVo frv = new FileRecordVo();
			BeanUtils.copyProperties(fr, frv);
			imageurls.add(frv);
		}
		s.setImageurls(imageurls);
		
		Wrapper<TInfrastructureRelation> irw = new EntityWrapper<>();
		irw.eq("module", CommonConst.MODULE_HOTEL);
		irw.eq("reference_id", hotelId);
		List<TInfrastructureRelation> irs = tInfrastructureRelationService.selectList(irw);
		List<InfrastructureRelationVo> installations = new ArrayList<>();
		for(TInfrastructureRelation ir : irs){
			InfrastructureRelationVo irv = new InfrastructureRelationVo();
			BeanUtils.copyProperties(ir, irv);
			installations.add(irv);
		}
		s.setInstallations(installations);
		
		return s;
	}

	@Transactional
	@Override
	public void saveSetting(Integer busid, MobileSaveOrUpdate setting) {
		Date date = new Date();
		//保存酒店手机设置信息
		THotelSetting hs = new THotelSetting();
		BeanUtils.copyProperties(setting, hs);
		hs.setUpdatedAt(date);
		hs.setUpdatedBy(busid);
		Wrapper<THotelSetting> sw = new EntityWrapper<>();
		sw.eq("hotel_id", hs.getHotelId());
		if(!this.update(hs, sw)) {
			throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
		}
		//保存图片
		Wrapper<TFileRecord> filewrapper = new EntityWrapper<>();
		filewrapper.eq("reference_id", hs.getHotelId());
		filewrapper.eq("module", CommonConst.MODULE_HOTEL);
		tFileRecordService.delete(filewrapper);
		List<TFileRecord> frs = new ArrayList<>();
		for(String img : setting.getImageurls()){
			TFileRecord fr = new TFileRecord();
			fr.setCreatedAt(date);
			fr.setCreatedBy(busid);
			fr.setUpdatedAt(date);
			fr.setUpdatedBy(busid);
			fr.setModule(CommonConst.MODULE_HOTEL);
			fr.setReferenceId(hs.getHotelId());
			fr.setPath(img);
			int index = img.lastIndexOf("/");
			if(index == -1) index = 0;
			String name = img.substring(index);
			fr.setName(name);
			fr.setOriginalName(name);
			frs.add(fr);
		}
		if(frs.size() > 0) {
			if(tFileRecordService.insertBatch(frs)) {
				throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
			}
		}
		//保存设施关系
		Wrapper<TInfrastructureRelation> rwrapper = new EntityWrapper<>();
		rwrapper.eq("reference_id", hs.getHotelId());
		rwrapper.eq("module", CommonConst.MODULE_HOTEL);
		tInfrastructureRelationService.delete(rwrapper);
		List<TInfrastructureRelation> irs = new ArrayList<>();
		for(InfrastructureRelationParamter ir : setting.getInstallations()){
			TInfrastructureRelation _ir = new TInfrastructureRelation();
			_ir.setCreatedAt(date);
			_ir.setCreatedBy(busid);
			_ir.setUpdatedAt(date);
			_ir.setUpdatedBy(busid);
			_ir.setModule(CommonConst.MODULE_HOTEL);
			_ir.setReferenceId(hs.getHotelId());
			_ir.setDisplayValue(ir.getDisplayValue());
			_ir.setInfrastructureId(ir.getInfrastructureId());
			irs.add(_ir);
		}
		if(irs.size() > 0) {
			if(!tInfrastructureRelationService.insertBatch(irs)) {
				throw new ResponseEntityException(ResponseEnums.INFRASTRUCTRUE_ERROR);
			}
		}
	}

	@Override
	public List<InfrastructureVo> queryHotelSettingInfrastructure() {
		List<InfrastructureVo> l = new ArrayList<>();
		Wrapper<TInfrastructure> wrapper = new EntityWrapper<>();
		wrapper.eq("module", CommonConst.MODULE_HOTEL);
		List<TInfrastructure> _l = tInfrastructureDAO.selectList(wrapper);
		for(TInfrastructure i : _l) {
			InfrastructureVo iv = new InfrastructureVo();
			BeanUtils.copyProperties(i, iv);
			l.add(iv);
		}
		return l;
	}

}
