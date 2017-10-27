package com.gt.hotel.web.service.impl;

import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TRoomCalendarDAO;
import com.gt.hotel.entity.TRoomCalendar;
import com.gt.hotel.web.service.TRoomCalendarService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客房日历价 — 优先级 第三方协议 > 活动价 >  服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-16
 */
@Service
public class TRoomCalendarServiceImpl extends BaseServiceImpl<TRoomCalendarDAO, TRoomCalendar> implements TRoomCalendarService {

}
