package com.gt.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TPackageRoom;
import com.gt.hotel.vo.erp.PackageRoomVo;

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
	 * @param hotelId
	 * @param packageId
	 * @return
	 */
	List<PackageRoomVo> erpQueryPackageRoom(@Param("hotelId") Integer hotelId, @Param("packageId") Integer packageId);

	void erpUpdateBatch(@Param("packageRoom") TPackageRoom pr, @Param("ids") List<Integer> ids, @Param("mark") Integer mark);

}