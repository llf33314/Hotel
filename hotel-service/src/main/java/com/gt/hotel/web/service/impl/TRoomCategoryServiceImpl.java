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
import com.gt.hotel.dao.TRoomCategoryDAO;
import com.gt.hotel.entity.TFileRecord;
import com.gt.hotel.entity.TInfrastructureRelation;
import com.gt.hotel.entity.TRoomCategory;
import com.gt.hotel.param.RoomCategoryParameter.InfrastructureRelation;
import com.gt.hotel.param.RoomCategoryParameter.QueryRoomCategory;
import com.gt.hotel.param.RoomCategoryParameter.SaveOrUpdate;
import com.gt.hotel.vo.FileRecordVo;
import com.gt.hotel.vo.InfrastructureRelationVo;
import com.gt.hotel.vo.RoomCategoryVo;
import com.gt.hotel.web.service.TFileRecordService;
import com.gt.hotel.web.service.TInfrastructureRelationService;
import com.gt.hotel.web.service.TRoomCategoryService;

/**
 * <p>
 * 房型 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Service
public class TRoomCategoryServiceImpl extends BaseServiceImpl< TRoomCategoryDAO, TRoomCategory > implements TRoomCategoryService {

	@Autowired
	TRoomCategoryDAO tRoomCategoryDAO;
	
	@Autowired
	TFileRecordService tFileRecordService;
	
	@Autowired
	TInfrastructureRelationService tInfrastructureRelationService;

	@Override
	public Page<RoomCategoryVo> queryRoomCategory(QueryRoomCategory param, Page<RoomCategoryVo> page) {
		page.setRecords(tRoomCategoryDAO.queryRoomCategory(param, page));
		return page;
	}

	@Transactional
	@Override
	public boolean roomCategoryCU(Integer busid, SaveOrUpdate roomCategory) {
		boolean flag = false;
		Date date = new Date();
		String module = "roomCategory";
		TRoomCategory tRoomCategory = new TRoomCategory();
		BeanUtils.copyProperties(roomCategory, tRoomCategory);
		tRoomCategory.setId(roomCategory.getRoomCategoryId());
		tRoomCategory.setStoreId(roomCategory.getShopId());
		tRoomCategory.setCreatedAt(date);
		tRoomCategory.setCreatedBy(busid);
		tRoomCategory.setUpdatedAt(date);
		tRoomCategory.setUpdatedBy(busid);
		flag = tRoomCategory.insertOrUpdate();
		
		Wrapper<TFileRecord> filewrapper = new EntityWrapper<>();
		filewrapper.eq("reference_id", tRoomCategory.getId());
		filewrapper.eq("module", module);
//		TFileRecord _file = new TFileRecord();
//		_file.setMarkModified(2);
//		tFileRecordService.update(_file , filewrapper);
		tFileRecordService.delete(filewrapper);
		
		List<TFileRecord> imgs = new ArrayList<>();
		for(String imgurl : roomCategory.getImages()){
			TFileRecord file = new TFileRecord();
			file.setCreatedAt(date);
			file.setCreatedBy(busid);
			file.setUpdatedAt(date);
			file.setUpdatedBy(busid);
			file.setModule(module);
			file.setReferenceId(tRoomCategory.getId());
			file.setPath(imgurl);
			int index = imgurl.lastIndexOf("/");
			if(index == -1) index = 0;
			String name = imgurl.substring(index);
			file.setName(name);
			file.setOriginalName(name);
			imgs.add(file);
		}
		flag = flag && tFileRecordService.insertBatch(imgs);
		
		Wrapper<TInfrastructureRelation> rwrapper = new EntityWrapper<>();
		rwrapper.eq("reference_id", tRoomCategory.getId());
		rwrapper.eq("module", module);
		tInfrastructureRelationService.delete(rwrapper);
		
		List<TInfrastructureRelation> irs = new ArrayList<>();
		for(InfrastructureRelation ir : roomCategory.getInfrastructureRelations()){
			TInfrastructureRelation _ir = new TInfrastructureRelation();
			_ir.setCreatedAt(date);
			_ir.setCreatedBy(busid);
			_ir.setUpdatedAt(date);
			_ir.setUpdatedBy(busid);
			_ir.setModule(module);
			_ir.setReferenceId(tRoomCategory.getId());
			_ir.setDisplayValue(ir.getDisplayValue());
			_ir.setInfrastructureId(ir.getInfrastructureId());
			irs.add(_ir);
		}
		flag = flag && tInfrastructureRelationService.insertBatch(irs);
		
		return flag;
	}

	@Override
	public RoomCategoryVo queryRoomCategoryOne(Integer roomCategoryId) {
		RoomCategoryVo roomCategoryVo = new RoomCategoryVo();
		List<FileRecordVo> fileRecordVos = new ArrayList<>();
		List<InfrastructureRelationVo> infrastructureRelationVos = new ArrayList<>();
		String module = "roomCategory";
		
		TRoomCategory tRoomCategory = tRoomCategoryDAO.selectById(roomCategoryId);
		Wrapper<TFileRecord> fwrapper = new EntityWrapper<>();
		fwrapper.eq("reference_id", roomCategoryId);
		fwrapper.eq("module", module);
		List<TFileRecord> tFileRecords = tFileRecordService.selectList(fwrapper);
		Wrapper<TInfrastructureRelation> iwrapper = new EntityWrapper<>();
		iwrapper.eq("reference_id", roomCategoryId);
		iwrapper.eq("module", module);
		List<TInfrastructureRelation> tInfrastructureRelations = tInfrastructureRelationService.selectList(iwrapper);
		
		BeanUtils.copyProperties(tRoomCategory, roomCategoryVo);
		for(TFileRecord _a : tFileRecords){
			FileRecordVo a = new FileRecordVo();
			BeanUtils.copyProperties(_a, a);
			fileRecordVos.add(a);
		}
		for(TInfrastructureRelation _a : tInfrastructureRelations){
			InfrastructureRelationVo a = new InfrastructureRelationVo();
			BeanUtils.copyProperties(_a, a);
			infrastructureRelationVos.add(a);
		}
		roomCategoryVo.setImages(fileRecordVos);
		roomCategoryVo.setInfrastructureRelations(infrastructureRelationVos);
		return roomCategoryVo;
	}

}
