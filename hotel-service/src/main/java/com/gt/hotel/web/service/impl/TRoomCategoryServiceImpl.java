package com.gt.hotel.web.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dao.*;
import com.gt.hotel.entity.*;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.param.InfrastructureRelationParamter;
import com.gt.hotel.param.RoomCalendarParamter.CalendarQuery;
import com.gt.hotel.param.RoomCategoryParameter.CategorySaveOrUpdate;
import com.gt.hotel.param.RoomCategoryParameter.MobileQueryRoomCategory;
import com.gt.hotel.param.RoomCategoryParameter.QueryRoomCategory;
import com.gt.hotel.param.RoomParameter.RoomPermanent;
import com.gt.hotel.param.RoomParameter.RoomPermanentQuery;
import com.gt.hotel.vo.*;
import com.gt.hotel.web.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 房型 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Service
public class TRoomCategoryServiceImpl extends BaseServiceImpl<TRoomCategoryDAO, TRoomCategory> implements TRoomCategoryService {

    @Autowired
    TRoomCategoryDAO tRoomCategoryDAO;

    @Autowired
    TRoomDAO tRoomDAO;

    @Autowired
    TInfrastructureDAO tInfrastructureDAO;

    @Autowired
    TFileRecordService tFileRecordService;

    @Autowired
    TInfrastructureRelationService tInfrastructureRelationService;

    @Autowired
    TRoomService tRoomService;

    @Autowired
    TRoomCalendarDAO tRoomCalendarDAO;

    @Autowired
    TRoomPermanentDAO tRoomPermanentDAO;

    @Autowired
    TRoomPermanentService tRoomPermanentService;

    @SuppressWarnings("unchecked")
    @Override
    public Page<RoomCategoryVo> queryRoomCategory(QueryRoomCategory param) {
        Page<RoomCategoryVo> page = param.initPage();
        page.setRecords(tRoomCategoryDAO.queryRoomCategory(param, page));
        return page;
    }

    @Transactional
    @Override
    public Integer roomCategoryCU(Integer busid, CategorySaveOrUpdate roomCategory) {
        Date date = new Date();
        TRoomCategory tRoomCategory = new TRoomCategory();
        BeanUtils.copyProperties(roomCategory, tRoomCategory);
        tRoomCategory.setId(roomCategory.getCategoryId());
//        tRoomCategory.setShopId(roomCategory.getShopId());
        if (tRoomCategory.getId() == null) {
            tRoomCategory.setCreatedAt(date);
            tRoomCategory.setCreatedBy(busid);
        }
        tRoomCategory.setUpdatedAt(date);
        tRoomCategory.setUpdatedBy(busid);
        if (!tRoomCategory.insertOrUpdate()) {
            throw new ResponseEntityException("房型保存失败");
        }

        //删除图片
        Wrapper<TFileRecord> filewrapper = new EntityWrapper<>();
        filewrapper.eq("reference_id", tRoomCategory.getId());
        filewrapper.eq("module", CommonConst.MODULE_ROOM_CATEGORY);
//		TFileRecord _file = new TFileRecord();
//		_file.setMarkModified(CommonConst.DELETED);
//		tFileRecordService.update(_file , filewrapper);
        tFileRecordService.delete(filewrapper);
        //保存图片
        List<TFileRecord> imgs = new ArrayList<>();
        for (FileRecordVo imgurl : roomCategory.getImages()) {
            TFileRecord file = new TFileRecord();
            BeanUtils.copyProperties(imgurl, file);
            file.setCreatedAt(date);
            file.setCreatedBy(busid);
            file.setUpdatedAt(date);
            file.setUpdatedBy(busid);
            file.setModule(CommonConst.MODULE_ROOM_CATEGORY);
            file.setReferenceId(tRoomCategory.getId());
            file.setOriginalName(imgurl.getName());
            imgs.add(file);
        }
        if (imgs.size() > 0) if (!tFileRecordService.insertBatch(imgs)) throw new ResponseEntityException(ResponseEnums.IMAGE_ERROR);
        //删除设施关系
        Wrapper<TInfrastructureRelation> rwrapper = new EntityWrapper<>();
        rwrapper.eq("reference_id", tRoomCategory.getId());
        rwrapper.eq("module", CommonConst.MODULE_ROOM_CATEGORY);
        tInfrastructureRelationService.delete(rwrapper);
        //保存设施关系
        List<TInfrastructureRelation> irs = new ArrayList<>();
        for (InfrastructureRelationParamter ir : roomCategory.getInfrastructureRelations()) {
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
        if (irs.size() > 0) if (!tInfrastructureRelationService.insertBatch(irs)) throw new ResponseEntityException(ResponseEnums.INFRASTRUCTRUE_ERROR);
        return tRoomCategory.getId();
    }

    @Override
    public RoomCategoryVo queryRoomCategoryOne(Integer categoryId) {
        RoomCategoryVo roomCategoryVo = new RoomCategoryVo();
        List<FileRecordVo> fileRecordVos = new ArrayList<>();
        List<InfrastructureRelationVo> infrastructureRelationVos = new ArrayList<>();

        TRoomCategory tRoomCategory = tRoomCategoryDAO.selectById(categoryId);
        Wrapper<TFileRecord> fwrapper = new EntityWrapper<>();
        fwrapper.eq("reference_id", categoryId);
        fwrapper.eq("module", CommonConst.MODULE_ROOM_CATEGORY);
        List<TFileRecord> tFileRecords = tFileRecordService.selectList(fwrapper);
        Wrapper<TInfrastructureRelation> iwrapper = new EntityWrapper<>();
        iwrapper.eq("reference_id", categoryId);
        iwrapper.eq("module", CommonConst.MODULE_ROOM_CATEGORY);
        List<TInfrastructureRelation> tInfrastructureRelations = tInfrastructureRelationService.selectList(iwrapper);

        BeanUtils.copyProperties(tRoomCategory, roomCategoryVo);
        for (TFileRecord _a : tFileRecords) {
            FileRecordVo a = new FileRecordVo();
            BeanUtils.copyProperties(_a, a);
            fileRecordVos.add(a);
        }
        for (TInfrastructureRelation _a : tInfrastructureRelations) {
            InfrastructureRelationVo a = new InfrastructureRelationVo();
            BeanUtils.copyProperties(_a, a);
            infrastructureRelationVos.add(a);
        }
        roomCategoryVo.setImages(fileRecordVos);
        roomCategoryVo.setInfrastructureRelations(infrastructureRelationVos);
        return roomCategoryVo;
    }

    @Override
    public void delRoomCategory(Integer busId, List<Integer> ids) {
        Date date = new Date();
        Wrapper<TRoomCategory> wrapper = new EntityWrapper<>();
        wrapper.in("id", ids);
        TRoomCategory entity = new TRoomCategory();
        entity.setMarkModified(CommonConst.DELETED);
        entity.setUpdatedAt(date);
        entity.setUpdatedBy(busId);
        if (!this.update(entity, wrapper)) {
            throw new ResponseEntityException(ResponseEnums.DELETE_ERROR);
        }
    }

    @Transactional
    @Override
    public void editRooms(Integer busid, Integer categoryId, List<com.gt.hotel.param.RoomParameter.RoomSaveOrUpdate> rooms) {
        Date date = new Date();
        List<TRoom> entityList = new ArrayList<>();
        Wrapper<TRoom> wrapper = new EntityWrapper<>();
        TRoom room = new TRoom();
        room.setMarkModified(CommonConst.DELETED);
        room.setUpdatedAt(date);
        room.setUpdatedBy(busid);
        wrapper.eq("category_id", categoryId);
        tRoomService.update(room, wrapper);

        for (com.gt.hotel.param.RoomParameter.RoomSaveOrUpdate r : rooms) {
            TRoom _r = new TRoom();
            BeanUtils.copyProperties(r, _r);
            _r.setCreatedAt(date);
            _r.setCreatedBy(busid);
            _r.setUpdatedBy(busid);
            entityList.add(_r);
        }

        List<TRoom> r1 = new ArrayList<>();
        List<TRoom> r2 = new ArrayList<>();
        for (TRoom r : entityList) {
            if (r.getId() == null) r1.add(r);
            else {
                r.setMarkModified(CommonConst.ENABLED);
                r2.add(r);
            }
        }

        if (r1.size() > 0) if (!tRoomService.insertBatch(r1)) {
            throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
        }
        if (r2.size() > 0) if (!tRoomService.updateBatchById(r2)) {
            throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
        }

        if (entityList.size() > 0) {
            TRoomCategory category = new TRoomCategory();
            category.setRoomCount(entityList.size());
            category.setUpdatedAt(date);
            category.setUpdatedBy(busid);
            Wrapper<TRoomCategory> cw = new EntityWrapper<>();
            cw.eq("id", categoryId);
            tRoomCategoryDAO.update(category, cw);
        }

    }

    @Override
    public Page<RoomVo> queryRoomList(Integer hotelId, Integer categoryId, Page<RoomVo> page) {
        page.setRecords(tRoomDAO.queryRoomList(hotelId, categoryId, page));
        return page;
    }


    @Override
    public List<RoomVo> queryRoomList(Integer hotelId, Integer categoryId, String roomNumber) {
        return tRoomDAO.queryRoomListAll(hotelId, categoryId, roomNumber);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<RoomCalendarVo> queryRoomCalendarList(Integer categoryId, CalendarQuery param) {
        Page<RoomCalendarVo> page = param.initPage();
        List<RoomCalendarVo> l = tRoomCalendarDAO.queryRoomCalendarList(categoryId, param, page);
        TRoomCategory rc = tRoomCategoryDAO.selectById(categoryId);
        for (RoomCalendarVo r : l) {
            r.setWeekendFareEnable(rc.getWeekendFareEnable());
            r.setWeekendFare(rc.getWeekendFare());
        }
        page.setRecords(l);
        return page;
    }

    @Override
    public void SaveRoomPermanent(Integer busId, RoomPermanent per) {
        TRoomPermanent rp = new TRoomPermanent();
        Date date = new Date();
        BeanUtils.copyProperties(per, rp);
        if (rp.getId() == null) {
            rp.setCreatedAt(date);
            rp.setCreatedBy(busId);
        }
        rp.setUpdatedAt(date);
        rp.setUpdatedBy(busId);
        rp.setHotelId(per.getHotelId());
        if (!rp.insertOrUpdate()) {
        	throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<RoomPermanentVo> queryRoomPermanent(Integer hotelId, RoomPermanentQuery param) {
        Page<RoomPermanentVo> page = param.initPage();
        page.setRecords(tRoomPermanentDAO.queryRoomPermanent(hotelId, param, page));
        return page;
    }

    @Override
    public void delRoomPermanent(Integer busId, List<Integer> ids) {
        Date date = new Date();
        TRoomPermanent rp = new TRoomPermanent();
        rp.setMarkModified(CommonConst.DELETED);
        rp.setUpdatedBy(busId);
        rp.setUpdatedAt(date);
        Wrapper<TRoomPermanent> wrapper = new EntityWrapper<>();
        wrapper.in("id", ids);
        if (!tRoomPermanentService.update(rp, wrapper)) {
            throw new ResponseEntityException(ResponseEnums.DELETE_ERROR);
        }
    }

    @Override
    public List<InfrastructureVo> queryRoomCategoryInfrastructure() {
        List<InfrastructureVo> l = new ArrayList<>();
        Wrapper<TInfrastructure> wrapper = new EntityWrapper<>();
        wrapper.eq("module", CommonConst.MODULE_ROOM_CATEGORY);
        List<TInfrastructure> _l = tInfrastructureDAO.selectList(wrapper);
        for (TInfrastructure i : _l) {
            InfrastructureVo iv = new InfrastructureVo();
            BeanUtils.copyProperties(i, iv);
            l.add(iv);
        }
        return l;

    }

	@SuppressWarnings("unchecked")
	@Override
	public Page<MobileRoomCategoryVo> queryMobileRoomCategory(Integer hotelId, MobileQueryRoomCategory req) {
		Page<MobileRoomCategoryVo> page = req.initPage();
		List<MobileRoomCategoryVo> l = tRoomCategoryDAO.queryMobileRoomCategory(hotelId, req, page);
		Wrapper<TFileRecord> fw = new EntityWrapper<>();
		fw.eq("module", CommonConst.MODULE_ROOM_CATEGORY);
		fw.eq("mark_modified", CommonConst.ENABLED);
		List<TFileRecord> fl = tFileRecordService.selectList(fw);
		
		Wrapper<TInfrastructureRelation> iw = new EntityWrapper<>();
		iw.eq("module", CommonConst.MODULE_ROOM_CATEGORY);
		List<TInfrastructureRelation> il = tInfrastructureRelationService.selectList(iw);
		
		for(MobileRoomCategoryVo r : l) {
			List<FileRecordVo> images = new ArrayList<>();
			for(TFileRecord f : fl) {
				if(f.getReferenceId().equals(r.getId())) {
					FileRecordVo v = new FileRecordVo();
					BeanUtils.copyProperties(f, v);;
					images.add(v);
				}
			}
			r.setImages(images);
			List<InfrastructureRelationVo> infrastructureRelations = new ArrayList<>();
			for(TInfrastructureRelation i : il) {
				if(i.getReferenceId().equals(r.getId())) {
					InfrastructureRelationVo v = new InfrastructureRelationVo();
					BeanUtils.copyProperties(i, v);;
					infrastructureRelations.add(v);
				}
			}
			r.setInfrastructureRelations(infrastructureRelations);
		}
		
		page.setRecords(l);
		return page;
	}

    /**
     * 获取某日期范围内的客房列表
     *
     * @param hotelId    酒店ID
     * @param queryParam 参数列表
     * @param pageSize   条数
     * @param pageIndex  页码
     * @return page
     */
    public List<MobileRoomBookableVo> findMobileRoomCategoryVoListByDate(Integer hotelId, MobileQueryRoomCategory queryParam, Integer pageSize, Integer pageIndex) {
        // 查找 房型列表 详情资料
        // 获取酒店下的房型分组统计客房列表

        // 获取客房订单
        Page<MobileRoomCategoryVo> categoryVoPage = new Page<>(pageIndex, pageSize);

        return this.tRoomCategoryDAO.findMobileRoomCategoryVoList(categoryVoPage, hotelId, queryParam);
    }


}
