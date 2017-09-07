package com.gt.hotel.web.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TErpHotelRoomOrder;
import com.gt.hotel.entity.TErpHotelRoomOrderEx;
import com.gt.hotel.entity.TErpHotelRoomOrderGuest;
import com.gt.hotel.entity.TErpHotelRoomOrderVO;

/**
 * <p>
 * ERP酒店-房间-订单 服务类
 * </p>
 *
 * @author 
 * @since 2017-08-17
 */
public interface TErpHotelRoomOrderService extends BaseService<TErpHotelRoomOrder> {

	Page<TErpHotelRoomOrderVO> selectRoomOrderPage(Page<TErpHotelRoomOrderVO> page, Wrapper<TErpHotelRoomOrder> wrapper,
			Wrapper<TErpHotelRoomOrderGuest> wrapperg);

	List<TErpHotelRoomOrderEx> selectRoomOrderExport(Map<String, Object> p);

	boolean deleteRoomOrder(List<Integer> idList);

	boolean updateStatus(TErpHotelRoomOrder po);

	Page<TErpHotelRoomOrderEx> selectRoomOrderPageEx(Page<TErpHotelRoomOrderEx> page, Map<String, Object> param);
	
}
