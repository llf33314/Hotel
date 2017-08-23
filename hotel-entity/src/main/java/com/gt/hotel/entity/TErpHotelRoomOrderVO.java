package com.gt.hotel.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * ERP酒店-房间-订单
 * </p>
 *
 * @author 
 * @since 2017-08-17
 */
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_room_order")
public class TErpHotelRoomOrderVO extends TErpHotelRoomOrder {

	private static final long serialVersionUID = 2267126693214188388L;
	
	public TErpHotelRoomOrderVO() {
		
	}
	
	public TErpHotelRoomOrderVO(TErpHotelRoomOrder order) {
		super(order.getId(), order.getHotelId(), order.getBusId(), order.getCheckInMode(), order.getCheckInStandard(), order.getPrice(), 
				order.getQuantity(), order.getIfCashPledge(), order.getCashPledge(), order.getOrderNumber(),
				order.getCheckInTime(), order.getCheckOutTime(), order.getCreateTime(), order.getPayType(), order.getPayTime(), order.getPayStatus(), 
				order.getOrderStatus(), order.getRemark(), order.getSource());
	}
	
	public TErpHotelRoomOrderVO(Integer id, Integer hotelId, Integer busId, Integer checkInMode,
			Integer checkInStandard, Integer price, Integer quantity, Integer ifCashPledge, Integer cashPledge,
			String orderNumber, Date checkInTime, Date checkOutTime, Date createTime, Integer payType, Date payTime,
			Integer payStatus, Integer orderStatus, String remark, String source,
			List<TErpHotelRoomOrderGuest> guests) {
		super(id, hotelId, busId, checkInMode, checkInStandard, price, quantity, ifCashPledge, cashPledge, orderNumber,
				checkInTime, checkOutTime, createTime, payType, payTime, payStatus, orderStatus, remark, source);
		this.guests = guests;
	}


	private List<TErpHotelRoomOrderGuest> guests;

	public List<TErpHotelRoomOrderGuest> getGuests() {
		return guests;
	}

	public void setGuests(List<TErpHotelRoomOrderGuest> guests) {
		this.guests = guests;
	}
}
