package com.gt.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TPackageRoom;
import com.gt.hotel.vo.PackageRoomVo;

/**
 * <p>
  * 套餐内的房型与价格 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-11-24
 */
public interface TPackageRoomDAO extends BaseMapper<TPackageRoom> {

	/**
	 * 套餐房间 列表
	 * @param shopId
	 * @param packageId
	 * @return
	 */
	List<PackageRoomVo> erpQueryPackageRoom(@Param("shopId") Integer shopId, @Param("packageId") Integer packageId);

}