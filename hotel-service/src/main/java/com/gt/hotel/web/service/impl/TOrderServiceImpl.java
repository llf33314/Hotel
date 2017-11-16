package com.gt.hotel.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.api.bean.session.Member;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dao.TOrderDAO;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.entity.TOrderRoom;
import com.gt.hotel.entity.TOrderRoomCustomer;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.param.HotelOrderParameter.CheckInParam;
import com.gt.hotel.param.HotelOrderParameter.FoodOrderQuery;
import com.gt.hotel.param.HotelOrderParameter.OffLineOrder;
import com.gt.hotel.param.HotelOrderParameter.RoomOrderQuery;
import com.gt.hotel.param.HotelOrderRoomParameter;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.vo.DepositVo;
import com.gt.hotel.vo.HotelBackFoodOrderVo;
import com.gt.hotel.vo.HotelBackRoomOrderVo;
import com.gt.hotel.web.service.TOrderRoomCustomerService;
import com.gt.hotel.web.service.TOrderRoomService;
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

	@Autowired
	TOrderRoomCustomerService tOrderRoomCustomerService;
	
	@Autowired
	TOrderRoomService tOrderRoomService;
	
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

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void AddOffLineOrder(Integer busid, OffLineOrder order) {
		Date date = new Date();
		TOrder o = new TOrder();
		BeanUtils.copyProperties(order, o);
		o.setBusId(busid);
		o.setOrderNum("DD" + date.getTime());
		o.setPayStatus(CommonConst.PAY_STATUS_PAID);
		o.setPayTime(date);
		o.setCreateTime(date);
		o.setCreatedAt(date);
		o.setCreatedBy(busid);
		o.setUpdatedAt(date);
		o.setUpdatedBy(busid);
		o.setBillPrice(o.getRealPrice());
		o.setReceivablePrice(o.getRealPrice());
		if(!o.insert()) {
			throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
		}
		
		TOrderRoom or = new TOrderRoom();
		BeanUtils.copyProperties(order, or);
		or.setOrderId(o.getId());
		or.setOrderNum(o.getOrderNum());
		or.setCheckInWay(CommonConst.CHECK_IN_WAY_OFFLINE);
		or.setNumber(order.getRooms().size());
		or.setFrom(CommonConst.SOURCE_BACK);
		or.setPayTime(date);
		or.setPayStatus(CommonConst.PAY_STATUS_PAID);
		or.setCreateTime(date);
		or.setCreatedAt(date);
		or.setCreatedBy(busid);
		or.setUpdatedAt(date);
		or.setUpdatedBy(busid);
		if(!or.insert()) {
			throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
		}
		
		List<TOrderRoomCustomer> orcs = new ArrayList<>();
		for(HotelOrderRoomParameter.OrderRoom hor : order.getRooms()) {
			TOrderRoomCustomer orc = new TOrderRoomCustomer();
			orc.setName(order.getCustomerName());
			orc.setIdType(order.getCustomerIdType());
			orc.setPhone(order.getCustomerPhone());
			orc.setIdCard(order.getCustomerIdCard());
			orc.setOrderId(o.getId());
			orc.setRoomNum(hor.getRoomNum());
			orc.setCustomerGender(order.getCustomerGender());
			orc.setCreatedAt(date);
			orc.setCreatedBy(busid);
			orc.setUpdatedAt(date);
			orc.setUpdatedBy(busid);
			orcs.add(orc);
		}
		if(orcs.size() != 0) {
			if(!tOrderRoomCustomerService.insertBatch(orcs)) {
				throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
			}
		}
	}

	@Override
	public HotelBackRoomOrderVo queryRoomOrderOne(Integer id) {
		HotelBackRoomOrderVo orderVo = new HotelBackRoomOrderVo();
		RoomOrderQuery param = new RoomOrderQuery();
		param.setId(id);
		List<HotelBackRoomOrderVo> l = tOrderDAO.queryRoomOrder(null, param, new Pagination());
		if(l.size() == 0) {
			return null;
		}
		orderVo = l.get(0);
		orderVo.setRooms(tOrderDAO.queryRoomOrderOneRooms(id));
		return orderVo;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void checkIn(Integer busid, Integer orderId, CheckInParam param) {
		Date date = new Date();
		
		Wrapper<TOrder> owrapper = new EntityWrapper<>();
		owrapper.eq("id", orderId);
		TOrder newOrder = new TOrder();
		newOrder.setOrderStatus(CommonConst.ORDER_CHECK_IN);
		newOrder.setUpdatedBy(busid);
		newOrder.setUpdatedAt(new Date());
		if(!this.update(newOrder, owrapper)) {
			throw new ResponseEntityException(ResponseEnums.OPERATING_ERROR);
		}
		
		TOrderRoom orderRoom = new TOrderRoom();
		List<TOrderRoomCustomer> customers = new ArrayList<>();
		orderRoom.setCustomerIdType(param.getCustomerIdType());
		orderRoom.setCustomerIdCard(param.getCustomerIdCard());
		orderRoom.setCustomerGender(param.getCustomerGender());
		orderRoom.setUpdatedAt(date);
		orderRoom.setUpdatedBy(busid);
		Wrapper<TOrderRoom> wrapper = new EntityWrapper<>();
		wrapper.eq("order_id", orderId);
		if(!tOrderRoomService.update(orderRoom, wrapper)) {
			throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
		}
		for(HotelOrderRoomParameter.OrderRoom o : param.getRooms()) {
			TOrderRoomCustomer customer = new TOrderRoomCustomer();
			customer.setIdType(param.getCustomerIdType());
			customer.setIdCard(param.getCustomerIdCard());
			customer.setCustomerGender(param.getCustomerGender());
			customer.setRoomNum(o.getRoomNum());
			customer.setOrderId(orderId);
			customer.setName(param.getCustomerName());
			customer.setPhone(param.getCustomerPhone());
			customer.setCreatedAt(date);
			customer.setCreatedBy(busid);
			customer.setUpdatedAt(date);
			customer.setUpdatedBy(busid);
			customers.add(customer);
		}
		if(!tOrderRoomCustomerService.insertBatch(customers)) {
			throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
		}
	}

	@Override
	public List<HotelBackRoomOrderVo> queryRoomOrderExport(Integer busid, RoomOrderQuery param) {
		return tOrderDAO.queryRoomOrder(busid, param);
	}

	@Override
	public List<HotelBackFoodOrderVo> queryFoodOrderExport(Integer busid, FoodOrderQuery param) {
		return tOrderDAO.queryFoodOrder(busid, param);
	}

	@Override
	public void mobileCheckNewOrder(Integer busId, Integer hid, Integer cid, DateTime startTime, DateTime endTime) {
		//1. 检测当前房型是否已存在预订订单

		//2. 房价信息 当前房型是否存在日历价 客户是否是协议单位

		//2.1 当前房型

		//2.2

	}

	public void mobileCheckOrdeRoom(Integer hid, Integer cid, DateTime startTime, DateTime endTime){
		// 检测当前酒店hid 的 房型cid

	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<HotelBackRoomOrderVo> queryMobileRoomOrder(Member member, HotelPage hotelPage) {
		Page<HotelBackRoomOrderVo> page = hotelPage.initPage();
		RoomOrderQuery roq = new RoomOrderQuery();
		roq.setMemberId(member.getId());
		page.setRecords(tOrderDAO.queryRoomOrder(null, roq, page));
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<HotelBackFoodOrderVo> queryMobileFoodOrder(Member member, HotelPage hotelPage) {
		Page<HotelBackFoodOrderVo> page = hotelPage.initPage();
		FoodOrderQuery param = new FoodOrderQuery();
		param.setMemberId(member.getId());
		page.setRecords(tOrderDAO.queryFoodOrder(null, param, page));
		return page;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Page<DepositVo> queryMobileDeposit(Member member, HotelPage hotelPage) {
		Page<DepositVo> page = hotelPage.initPage();
		page.setRecords(tOrderDAO.queryMobileDeposit(member.getId(), page));
		return null;
	}

}
