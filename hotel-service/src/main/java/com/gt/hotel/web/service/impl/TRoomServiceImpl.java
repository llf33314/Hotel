package com.gt.hotel.web.service.impl;

import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TRoomDAO;
import com.gt.hotel.entity.TRoom;
import com.gt.hotel.web.service.TRoomService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 房型关联 1 - n 客房信息
 * 房型的客房数量 对应 客房数 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Service
public class TRoomServiceImpl extends BaseServiceImpl<TRoomDAO, TRoom> implements TRoomService {
	
	@Autowired
	private TRoomDAO roomDAO;

	@Override
	public List<TRoom> queryOrderRooms(Integer orderId) {
		return roomDAO.queryOrderRooms(orderId);
	}

}
