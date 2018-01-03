package com.gt.hotel.web.service;

import java.util.List;

import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TRoom;
import com.gt.hotel.vo.erp.ErpRoomStatusVo;

/**
 * <p>
 * 房型关联 1 - n 客房信息
 * 房型的客房数量 对应 客房数 服务类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface TRoomService extends BaseService<TRoom> {
    /**
     * <pre>
     *     客房状态统计
     * </pre>
     *
     * @param hotelId 酒店ID
     * @return ErpRoomStatusVo
     */
    ErpRoomStatusVo findRoomsGroupStatistics(Integer hotelId);

	/**
	 * 订单入住房间
	 * @param orderId
	 * @return
	 */
	List<TRoom> queryOrderRooms(Integer orderId);

    /**
     * <pre>
     *     获取楼层列表
     * </pre>
     *
     * @param hotelId 酒店ID
     * @return List
     */
    List<Integer> getFloorList(Integer hotelId);
}
