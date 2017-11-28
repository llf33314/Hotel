package com.gt.hotel.web.service;

import java.util.List;

import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TPackageRoom;
import com.gt.hotel.vo.PackageRoomVo;

/**
 * <p>
 * 套餐内的房型与价格 服务类
 * </p>
 *
 * @author 
 * @since 2017-11-24
 */
public interface TPackageRoomService extends BaseService<TPackageRoom> {

	/**
	 * 套餐房间 列表
	 * @param shopId
	 * @param packageId
	 * @return
	 */
	List<PackageRoomVo> erpQueryPackageRoom(Integer shopId, Integer packageId);
	
}
