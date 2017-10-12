package com.gt.hotel.web.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TRoomCategoryDAO;
import com.gt.hotel.entity.TFileRecord;
import com.gt.hotel.entity.TRoomCategory;
import com.gt.hotel.param.RoomCategoryParameter.QueryRoomCategory;
import com.gt.hotel.param.RoomCategoryParameter.SaveOrUpdate;
import com.gt.hotel.vo.RoomCategoryVo;
import com.gt.hotel.web.service.TFileRecordService;
import com.gt.hotel.web.service.TRoomCategoryService;
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
public class TRoomCategoryServiceImpl extends BaseServiceImpl< TRoomCategoryDAO, TRoomCategory > implements TRoomCategoryService {

    @Autowired
    TRoomCategoryDAO tRoomCategoryDAO;

    @Autowired
    TFileRecordService tFileRecordService;

    @Override
    public Page< RoomCategoryVo > queryRoomCategory(QueryRoomCategory param, Page< RoomCategoryVo > page) {
	page.setRecords(tRoomCategoryDAO.queryRoomCategory(param, page));
	return page;
    }

    @Transactional
    @Override
    public boolean roomCategoryCU(Integer busid, SaveOrUpdate roomCategory) {
	boolean flag = false;
	Date date = new Date();
	TRoomCategory tRoomCategory = new TRoomCategory();
	BeanUtils.copyProperties(roomCategory, tRoomCategory);
	tRoomCategory.setId(roomCategory.getRoomCategoryId());
	tRoomCategory.setStoreId(roomCategory.getShopId());
	tRoomCategory.setCreatedAt(date);
	tRoomCategory.setCreatedBy(busid);
	tRoomCategory.setUpdatedAt(date);
	tRoomCategory.setUpdatedBy(busid);
	flag = tRoomCategory.insertOrUpdate();

	Wrapper< TFileRecord > filewrapper = new EntityWrapper<>();
	filewrapper.eq("reference_id", tRoomCategory.getId());
	filewrapper.eq("module", "roomCategory");
	TFileRecord _file = new TFileRecord();
	_file.setMarkModified(2);
	tFileRecordService.update(_file, filewrapper);

	List< TFileRecord > imgs = new ArrayList<>();
	for (String imgurl : roomCategory.getImages()) {
	    TFileRecord file = new TFileRecord();
	    file.setCreatedAt(date);
	    file.setCreatedBy(busid);
	    file.setUpdatedAt(date);
	    file.setUpdatedBy(busid);
	    file.setModule("roomCategory");
	    file.setReferenceId(tRoomCategory.getId());
	    file.setPath(imgurl);
	    int index = imgurl.lastIndexOf("/");
	    if (index == -1) index = 0;
	    String name = imgurl.substring(index);
	    file.setName(name);
	    file.setOriginalName(name);
	    imgs.add(file);
	}
	flag = flag && tFileRecordService.insertBatch(imgs);

	//TODO 未完
	return flag;
    }

}
