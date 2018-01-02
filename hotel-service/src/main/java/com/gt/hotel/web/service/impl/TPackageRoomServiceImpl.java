package com.gt.hotel.web.service.impl;

import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TPackageRoomDAO;
import com.gt.hotel.entity.TPackageRoom;
import com.gt.hotel.vo.PackageRoomVo;
import com.gt.hotel.web.service.TPackageRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 套餐内的房型与价格 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-11-24
 */
@Service
public class TPackageRoomServiceImpl extends BaseServiceImpl<TPackageRoomDAO, TPackageRoom> implements TPackageRoomService {

	@Autowired
	TPackageRoomDAO packageRoomDAO;
	
	@Override
	public List<PackageRoomVo> erpQueryPackageRoom(Integer hotelId, Integer packageId) {
		return packageRoomDAO.erpQueryPackageRoom(hotelId, packageId);
	}
	
}
