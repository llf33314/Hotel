package com.gt.hotel.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dao.TOrderDAO;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.entity.TOrderRoom;
import com.gt.hotel.entity.TOrderRoomCustomer;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.param.HotelOrderParameter.FoodOrderQuery;
import com.gt.hotel.param.HotelOrderParameter.OffLineOrder;
import com.gt.hotel.param.HotelOrderParameter.RoomOrderQuery;
import com.gt.hotel.param.HotelOrderRoomParameter;
import com.gt.hotel.vo.HotelBackFoodOrderVo;
import com.gt.hotel.vo.HotelBackRoomOrderVo;
import com.gt.hotel.web.service.TOrderRoomCustomerService;
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

	@Transactional
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
		}
		if(!tOrderRoomCustomerService.insertBatch(orcs)) {
			throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
		}
	}

	@Override
	public HotelBackRoomOrderVo queryRoomOrderOne(Integer id) {
		HotelBackRoomOrderVo orderVo = new HotelBackRoomOrderVo();
		RoomOrderQuery param = new RoomOrderQuery();
		param.setId(id);
		List<HotelBackRoomOrderVo> l = tOrderDAO.queryRoomOrder(null, param, null);
		if(l.size() == 0) {
			return null;
		}
		orderVo = l.get(0);
		orderVo.setRooms(tOrderDAO.queryRoomOrderOneRooms(id));
		return orderVo;
	}
	
}
