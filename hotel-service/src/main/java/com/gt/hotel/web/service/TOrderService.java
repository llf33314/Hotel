package com.gt.hotel.web.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.param.HotelOrderParameter.FoodOrderQuery;
import com.gt.hotel.param.HotelOrderParameter.OffLineOrder;
import com.gt.hotel.param.HotelOrderParameter.RoomOrderQuery;
import com.gt.hotel.vo.HotelBackFoodOrderVo;
import com.gt.hotel.vo.HotelBackRoomOrderVo;

/**
 * <p>
 * 算客户实际 预约(到店办理)->入住->消费(酒店内消费)->退房->结算 的所有消费(房费+其他费) 服务类
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
public interface TOrderService extends BaseService<TOrder> {

	/**
	 * 房间订单列表 
	 * @param busid 用户ID
	 * @param param
	 * @return
	 */
	Page<HotelBackRoomOrderVo> queryRoomOrder(Integer busid, RoomOrderQuery param);

	/**
	 * 餐饮订单列表
	 * @param busid 用户ID
	 * @param param
	 * @return
	 */
	Page<HotelBackFoodOrderVo> queryFoodOrder(Integer busid, FoodOrderQuery param);

	/**
	 * 添加线下订单
	 * @param busid 
	 * @param order 
	 */
	void AddOffLineOrder(Integer busid, OffLineOrder order);
	
}
