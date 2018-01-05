package com.gt.hotel.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.api.bean.session.BusUser;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dao.TPackageDAO;
import com.gt.hotel.entity.TPackage;
import com.gt.hotel.entity.TPackageRoom;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.param.erp.PackageParamter.PackageCU;
import com.gt.hotel.param.erp.PackageParamter.PackageQuery;
import com.gt.hotel.vo.erp.PackageRoomVo;
import com.gt.hotel.vo.erp.PackageVo;
import com.gt.hotel.web.service.TPackageRoomService;
import com.gt.hotel.web.service.TPackageService;

/**
 * <p>
 * 套餐(包含中介/单位) 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-11-24
 */
@Service
public class TPackageServiceImpl extends BaseServiceImpl<TPackageDAO, TPackage> implements TPackageService {

	@Autowired
	TPackageDAO packageDAO;
	
	@Autowired
	TPackageRoomService packageRoomService;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<PackageVo> erpQueryPackage(Integer hotelId, PackageQuery query) {
		Page<PackageVo> page = query.initPage();
		page.setRecords(packageDAO.erpQueryPackage(hotelId, query, page));
		return page;
	}

	@Override
	public void erpPackageCU(BusUser user, PackageCU packageCU) {
		Date date = new Date();
		TPackage p = new TPackage();
		BeanUtils.copyProperties(packageCU, p);
		if(packageCU.getId() == null) {
			p.setCreatedAt(date);
			p.setCreatedBy(user.getId());
		}
		p.setUpdatedBy(user.getId());
		if(!this.insertOrUpdate(p)) {
			throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
		}
		if(packageCU.getId() == null) {
			List<TPackageRoom> prs = new ArrayList<>();
			for(PackageRoomVo r : packageCU.getPackRooms()) {
				TPackageRoom pr = new TPackageRoom();
				BeanUtils.copyProperties(r, pr);
				pr.setPackageId(p.getId());
				pr.setCreatedAt(date);
				pr.setCreatedBy(user.getId());
				pr.setUpdatedBy(user.getId());
				prs.add(pr);
			}
			if(!packageRoomService.insertBatch(prs)) {
				throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
			}
		}else {
			TPackageRoom pr = new TPackageRoom();
			pr.setMarkModified(CommonConst.DELETED);
			Wrapper<TPackageRoom> pw = new EntityWrapper<>();
			pw.eq("package_id", packageCU.getId());
			packageRoomService.update(pr, pw);
			List<TPackageRoom> prsC = new ArrayList<>();
			List<Integer> prsUIds = new ArrayList<>();
			for(PackageRoomVo r : packageCU.getPackRooms()) {
				TPackageRoom pr1 = new TPackageRoom();
				BeanUtils.copyProperties(r, pr1);
				pr1.setUpdatedBy(user.getId());
				if(pr1.getId() == null) {
					pr1.setPackageId(p.getId());
					pr1.setCreatedAt(date);
					pr1.setCreatedBy(user.getId());
					prsC.add(pr1);
				}else {
					prsUIds.add(pr1.getId());
				}
			}
			packageRoomService.insertBatch(prsC);
			TPackageRoom tpr = new TPackageRoom();
			tpr.setMarkModified(CommonConst.ENABLED);
			tpr.setUpdatedBy(user.getId());
			packageRoomService.erpUpdateBatch(tpr, prsUIds, CommonConst.DELETED);
		}
	}
	
}
