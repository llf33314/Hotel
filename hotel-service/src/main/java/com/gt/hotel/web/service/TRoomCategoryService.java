package com.gt.hotel.web.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TRoomCategory;
import com.gt.hotel.param.RoomCalendarParamter.CalendarQuery;
import com.gt.hotel.param.RoomCategoryParameter.CategorySaveOrUpdate;
import com.gt.hotel.param.RoomCategoryParameter.QueryRoomCategory;
import com.gt.hotel.param.RoomParameter.RoomPermanent;
import com.gt.hotel.param.RoomParameter.RoomPermanentQuery;
import com.gt.hotel.param.RoomParameter.RoomSaveOrUpdate;
import com.gt.hotel.vo.InfrastructureVo;
import com.gt.hotel.vo.RoomCalendarVo;
import com.gt.hotel.vo.RoomCategoryVo;
import com.gt.hotel.vo.RoomPermanentVo;
import com.gt.hotel.vo.RoomVo;

/**
 * <p>
 * 房型 服务类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface TRoomCategoryService extends BaseService< TRoomCategory > {

	/**
	 * 查询 房型列表
	 * @param hotelId 酒店ID
	 * @param page
	 * @return
	 */
	Page<RoomCategoryVo> queryRoomCategory(QueryRoomCategory param);

	/**
	 * 保存 or 更新 房型
	 * @param busid 用户ID
	 * @param roomCategory 房型请求对象
	 * @return
	 */
	Integer roomCategoryCU(Integer busid, CategorySaveOrUpdate roomCategory);

	/**
	 * 查询 房型对象
	 * @param roomCategoryId 房型ID
	 * @param page
	 * @return
	 */
	RoomCategoryVo queryRoomCategoryOne(Integer roomCategoryId);

	/**
	 * 保存 or 更新 房间
	 * @param busid 用户ID
	 * @param rooms 房间对象数组
	 * @return
	 */
	void editRooms(Integer busid, Integer categoryId, List<RoomSaveOrUpdate> rooms);

	/**
	 * 查询 房间列表
	 * @param param 房间请求对象
	 * @param page 
	 * @return
	 */
	Page<RoomVo> queryRoomList(Integer roomCategoryId, Page<RoomVo> page);

	/**
	 * 删除 房型(伪)
	 * @param busid 用户ID
	 * @param ids 房型ID 数组
	 * @return
	 */
	void delRoomCategory(Integer busid, List<Integer> ids);

	/**
	 * 查询房型 日历价格 
	 * @param roomCategoryId 房型ID
	 * @param param 请求对象 
	 * @param page 分页对象
	 * @return
	 */
	Page<RoomCalendarVo> queryRoomCalendarList(Integer roomCategoryId, CalendarQuery param);

	/**
	 * 保存 长包房设置
	 * @param busId 用户ID
	 * @param per
	 */
	void SaveRoomPermanent(Integer busId, RoomPermanent per);

	/**
	 * 查询 长包房设置
	 * @param param
	 * @return
	 */
	Page<RoomPermanentVo> queryRoomPermanent(RoomPermanentQuery param);

	/**
	 * 删除 长包房设置
	 * @param busId
	 * @param id 长包房ID
	 */
	void delRoomPermanent(Integer busId, List<Integer> ids);

	/**
	 * 房型设备房型列表
	 * @return
	 */
	List<InfrastructureVo> queryRoomCategoryInfrastructure();


}
