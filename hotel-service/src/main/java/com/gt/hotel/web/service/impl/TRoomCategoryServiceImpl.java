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
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dao.TRoomCalendarDAO;
import com.gt.hotel.dao.TRoomCategoryDAO;
import com.gt.hotel.dao.TRoomDAO;
import com.gt.hotel.entity.TFileRecord;
import com.gt.hotel.entity.TInfrastructureRelation;
import com.gt.hotel.entity.TRoom;
import com.gt.hotel.entity.TRoomCategory;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.param.InfrastructureRelationParamter;
import com.gt.hotel.param.RoomCalendarParamter.Query;
import com.gt.hotel.param.RoomCategoryParameter.QueryRoomCategory;
import com.gt.hotel.param.RoomCategoryParameter.SaveOrUpdate;
import com.gt.hotel.vo.FileRecordVo;
import com.gt.hotel.vo.InfrastructureRelationVo;
import com.gt.hotel.vo.RoomCalendarVo;
import com.gt.hotel.vo.RoomCategoryVo;
import com.gt.hotel.vo.RoomVo;
import com.gt.hotel.web.service.TFileRecordService;
import com.gt.hotel.web.service.TInfrastructureRelationService;
import com.gt.hotel.web.service.TRoomCategoryService;
import com.gt.hotel.web.service.TRoomService;

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
	TRoomDAO tRoomDAO;
	
	@Autowired
	TFileRecordService tFileRecordService;
	
	@Autowired
	TInfrastructureRelationService tInfrastructureRelationService;

	@Autowired
	TRoomService tRoomService;
	
	@Autowired
	TRoomCalendarDAO tRoomCalendarDAO;
	
	@Override
	public Page<RoomCategoryVo> queryRoomCategory(QueryRoomCategory param, Page<RoomCategoryVo> page) {
		page.setRecords(tRoomCategoryDAO.queryRoomCategory(param, page));
		return page;
	}

	@Transactional
	@Override
	public void roomCategoryCU(Integer busid, SaveOrUpdate roomCategory) {
		Date date = new Date();
		TRoomCategory tRoomCategory = new TRoomCategory();
		BeanUtils.copyProperties(roomCategory, tRoomCategory);
		tRoomCategory.setId(roomCategory.getRoomCategoryId());
		tRoomCategory.setStoreId(roomCategory.getShopId());
		if(tRoomCategory.getId() == null){
			tRoomCategory.setCreatedAt(date);
			tRoomCategory.setCreatedBy(busid);
		}
		tRoomCategory.setUpdatedAt(date);
		tRoomCategory.setUpdatedBy(busid);
		if(!tRoomCategory.insertOrUpdate()){
			throw new ResponseEntityException("房型保存失败");
		}
		
		//删除图片
		Wrapper<TFileRecord> filewrapper = new EntityWrapper<>();
		filewrapper.eq("reference_id", tRoomCategory.getId());
		filewrapper.eq("module", CommonConst.MODULE_ROOM_CATEGORY);
//		TFileRecord _file = new TFileRecord();
//		_file.setMarkModified(CommonConst.DELETED);
//		tFileRecordService.update(_file , filewrapper);
		if(!tFileRecordService.delete(filewrapper)){
			throw new ResponseEntityException(ResponseEnums.IMAGE_ERROR);
		}
		//保存图片
		List<TFileRecord> imgs = new ArrayList<>();
		for(String imgurl : roomCategory.getImages()){
			TFileRecord file = new TFileRecord();
			file.setCreatedAt(date);
			file.setCreatedBy(busid);
			file.setUpdatedAt(date);
			file.setUpdatedBy(busid);
			file.setModule(CommonConst.MODULE_ROOM_CATEGORY);
			file.setReferenceId(tRoomCategory.getId());
			file.setPath(imgurl);
			int index = imgurl.lastIndexOf("/");
			if(index == -1) index = 0;
			String name = imgurl.substring(index);
			file.setName(name);
			file.setOriginalName(name);
			imgs.add(file);
		}
		if(!tFileRecordService.insertBatch(imgs)){
			throw new ResponseEntityException(ResponseEnums.IMAGE_ERROR);
		}
		//删除设施关系
		Wrapper<TInfrastructureRelation> rwrapper = new EntityWrapper<>();
		rwrapper.eq("reference_id", tRoomCategory.getId());
		rwrapper.eq("module", CommonConst.MODULE_ROOM_CATEGORY);
		if(!tInfrastructureRelationService.delete(rwrapper)){
			throw new ResponseEntityException(ResponseEnums.INFRASTRUCTRUE_ERROR);
		}
		//保存设施关系
		List<TInfrastructureRelation> irs = new ArrayList<>();
		for(InfrastructureRelationParamter ir : roomCategory.getInfrastructureRelations()){
			TInfrastructureRelation _ir = new TInfrastructureRelation();
			_ir.setCreatedAt(date);
			_ir.setCreatedBy(busid);
			_ir.setUpdatedAt(date);
			_ir.setUpdatedBy(busid);
			_ir.setModule(CommonConst.MODULE_ROOM_CATEGORY);
			_ir.setReferenceId(tRoomCategory.getId());
			_ir.setDisplayValue(ir.getDisplayValue());
			_ir.setInfrastructureId(ir.getInfrastructureId());
			irs.add(_ir);
		}
		if(!tInfrastructureRelationService.insertBatch(irs)){
			throw new ResponseEntityException(ResponseEnums.INFRASTRUCTRUE_ERROR);
		}
		
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
	
	@Override
	public void delRoomCategory(Integer busid, List<Integer> ids) {
		Date date = new Date();
		Wrapper<TRoomCategory> wrapper = new EntityWrapper<>();
		wrapper.in("id", ids);
		TRoomCategory entity = new TRoomCategory();
		entity.setMarkModified(CommonConst.DELETED);
		entity.setUpdatedAt(date);
		entity.setUpdatedBy(busid);
		if(!this.update(entity, wrapper)){
			throw new ResponseEntityException(999, "删除失败");
		}
	}

	@Override
	public void editRooms(Integer busid, List<com.gt.hotel.param.RoomParameter.SaveOrUpdate> rooms) {
		Date date = new Date();
		List<TRoom> entityList = new ArrayList<>();
		for(com.gt.hotel.param.RoomParameter.SaveOrUpdate r : rooms){
			TRoom _r = new TRoom();
			BeanUtils.copyProperties(r, _r);
			_r.setCreatedAt(date);
			_r.setCreatedBy(busid);
			_r.setUpdatedBy(busid);
		}
		if(!tRoomService.insertOrUpdateBatch(entityList)){
			throw new ResponseEntityException(999, "保存失败");
		}
	}

	@Override
	public Page<RoomVo> queryRoomList(Integer roomCategoryId, Page<RoomVo> page) {
		page.setRecords(tRoomDAO.queryRoomList(roomCategoryId, page));
		return page;
	}

	@Override
	public Page<RoomCalendarVo> queryRoomCalendarList(Integer roomCategoryId, Query param, Page<RoomCalendarVo> page) {
		page.setRecords(tRoomCalendarDAO.queryRoomCalendarList(roomCategoryId, param, page));
		return null;
	}

}
