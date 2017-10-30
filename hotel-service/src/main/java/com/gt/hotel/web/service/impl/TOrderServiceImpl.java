package com.gt.hotel.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TOrderDAO;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.param.HotelOrderParameter.FoodOrderQuery;
import com.gt.hotel.param.HotelOrderParameter.RoomOrderQuery;
import com.gt.hotel.vo.HotelBackFoodOrderVo;
import com.gt.hotel.vo.HotelBackRoomOrderVo;
import com.gt.hotel.web.service.TOrderService;

/**
 * <p>
 * 算客户实际 预约(到店办理)->入住->消费(酒店内消费)->退房->结算 的所有消费(房费+其他费) 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
@Service
public class TOrderServiceImpl extends BaseServiceImpl<TOrderDAO, TOrder> implements TOrderService {

	@Autowired
	TOrderDAO tOrderDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<HotelBackRoomOrderVo> queryRoomOrder(Integer busid, RoomOrderQuery param) {
		Page<HotelBackRoomOrderVo> page = param.initPage();
		page.setRecords(tOrderDAO.queryRoomOrder(busid, param, page));
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<HotelBackFoodOrderVo> queryFoodOrder(Integer busid, FoodOrderQuery param) {
		Page<HotelBackFoodOrderVo> page = param.initPage();
		page.setRecords(tOrderDAO.queryFoodOrder(busid, param, page));
		return page;
	}
	
}
