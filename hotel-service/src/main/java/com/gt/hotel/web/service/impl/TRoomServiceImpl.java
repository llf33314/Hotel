package com.gt.hotel.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.constant.RoomStatusConst;
import com.gt.hotel.dao.TOrderRoomDAO;
import com.gt.hotel.dao.TRoomDAO;
import com.gt.hotel.entity.TRoom;
import com.gt.hotel.vo.erp.ErpRoomStatusVo;
import com.gt.hotel.web.service.TRoomService;

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
	
	@Autowired
	private TOrderRoomDAO orderRoomDAO;

	@Override
	public List<TRoom> queryOrderRooms(Integer orderId) {
		return roomDAO.queryOrderRooms(orderId);
	}

    @Override
    public ErpRoomStatusVo findRoomsGroupStatistics(Integer hotelId) {
        ErpRoomStatusVo erpRoomStatusVo = new ErpRoomStatusVo();
        // 获取客房状态各个数量
        erpRoomStatusVo.setVacantRoomCount(this.roomDAO.roomStatusStatistics(hotelId, RoomStatusConst.VACANT_ROOM));
        erpRoomStatusVo.setLockCount(this.roomDAO.roomStatusStatistics(hotelId, RoomStatusConst.LOCK));
        erpRoomStatusVo.setCheckInCount(this.roomDAO.roomStatusStatistics(hotelId, RoomStatusConst.CHECK_IN));
        erpRoomStatusVo.setCleanCount(this.roomDAO.roomStatusStatistics(hotelId, RoomStatusConst.CLEAN));
        erpRoomStatusVo.setMaintain(this.roomDAO.roomStatusStatistics(hotelId, RoomStatusConst.MAINTAIN));
        // TODO: 因客房状态不包含预约状态，所以，获取客房的预约状态 需要从客房里获取状态
        return erpRoomStatusVo;
    }


    @Override
    public List<Integer> getFloorList(Integer hotelId){
        return this.roomDAO.getFloorList(hotelId);
    }


}
