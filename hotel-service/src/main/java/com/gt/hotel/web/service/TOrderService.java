package com.gt.hotel.web.service;

import com.gt.hotel.entity.TOrder;
import com.gt.hotel.param.HotelOrderParameter.OrderQuery;
import com.gt.hotel.vo.HotelBackRoomOrderVo;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;

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
	Page<HotelBackRoomOrderVo> queryRoomOrder(Integer busid, OrderQuery param);
	
}
