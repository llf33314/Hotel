package com.gt.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.param.HotelOrderParameter.FoodOrderQuery;
import com.gt.hotel.param.HotelOrderParameter.RoomOrderQuery;
import com.gt.hotel.vo.HotelBackFoodOrderVo;
import com.gt.hotel.vo.HotelBackRoomOrderVo;

/**
 * <p>
  * 算客户实际 预约(到店办理)->入住->消费(酒店内消费)->退房->结算 的所有消费(房费+其他费) Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
public interface TOrderDAO extends BaseMapper<TOrder> {

	/**
	 * 房间订单列表 
	 * @param busid 用户ID
	 * @param param
	 * @param page 分页对象
	 * @return
	 */
	List<HotelBackRoomOrderVo> queryRoomOrder(@Param("busid") Integer busid, @Param("param") RoomOrderQuery param, @Param("page") Pagination page);

	/**
	 * 餐饮订单列表 
	 * @param busid 用户ID
	 * @param param
	 * @param page 分页对象
	 * @return
	 */
	List<HotelBackFoodOrderVo> queryFoodOrder(@Param("busid") Integer busid, @Param("param") FoodOrderQuery param, @Param("page") Pagination page);

}