package com.gt.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.hotel.entity.TRoomCalendar;
import com.gt.hotel.param.RoomCalendarParamter.Query;
import com.gt.hotel.vo.RoomCalendarVo;

/**
 * <p>
  * 客房日历价 — 优先级 第三方协议 > 活动价 >  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-10-16
 */
public interface TRoomCalendarDAO extends BaseMapper<TRoomCalendar> {

	/**
	 * 查询房型 日历价格 
	 * @param roomCategoryId 房型ID
	 * @param param 请求对象 
	 * @param page 分页对象
	 * @return
	 */
	List<RoomCalendarVo> queryRoomCalendarList(@Param("roomCategoryId") Integer roomCategoryId, @Param("param") Query param, @Param("page") Pagination page);

}