package com.gt.hotel.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TRoomCalendar;
import com.gt.hotel.param.RoomCalendarParamter.Query;
import com.gt.hotel.vo.RoomCalendarVo;

/**
 * <p>
 * 客房日历价 — 优先级 第三方协议 > 活动价 >  服务类
 * </p>
 *
 * @author 
 * @since 2017-10-16
 */
public interface TRoomCalendarService extends BaseService<TRoomCalendar> {

}
