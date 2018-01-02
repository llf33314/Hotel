package com.gt.hotel.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TPackageRoom;
import com.gt.hotel.vo.PackageRoomVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

}