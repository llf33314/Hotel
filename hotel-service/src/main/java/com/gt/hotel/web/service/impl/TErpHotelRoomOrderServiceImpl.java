package com.gt.hotel.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TErpHotelRoomOrderDAO;
import com.gt.hotel.entity.TErpHotelRoomOrder;
import com.gt.hotel.entity.TErpHotelRoomOrderEx;
import com.gt.hotel.entity.TErpHotelRoomOrderGuest;
import com.gt.hotel.entity.TErpHotelRoomOrderVO;
import com.gt.hotel.web.service.TErpHotelRoomOrderGuestService;
import com.gt.hotel.web.service.TErpHotelRoomOrderService;

/**
 * <p>
 * ERP酒店-房间-订单 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-08-17
 */
@Service
public class TErpHotelRoomOrderServiceImpl extends BaseServiceImpl<TErpHotelRoomOrderDAO, TErpHotelRoomOrder> implements TErpHotelRoomOrderService {

	@Autowired
	TErpHotelRoomOrderGuestService TErpHotelRoomOrderGuestService;
	
	@Autowired
	TErpHotelRoomOrderDAO TErpHotelRoomOrderDAO;
	
	@Override
	public Page<TErpHotelRoomOrderVO> selectRoomOrderPage(Page<TErpHotelRoomOrderVO> page,
			Wrapper<TErpHotelRoomOrder> wrapper, Wrapper<TErpHotelRoomOrderGuest> wrapperg) {
		SqlHelper.fillWrapper(page, wrapper);
		List<TErpHotelRoomOrderVO> o_da = new ArrayList<TErpHotelRoomOrderVO>();
		List<TErpHotelRoomOrder> l = this.selectList(wrapper);
		List<Integer> ids = new ArrayList<Integer>();
		for(TErpHotelRoomOrder r : l){
			ids.add(r.getId());
		}
		wrapperg.in("order_id", ids);
		wrapperg.eq("guest_type", 0);
		List<TErpHotelRoomOrderGuest> ll = TErpHotelRoomOrderGuestService.selectList(wrapperg);
		for(TErpHotelRoomOrder r : l){
			for(TErpHotelRoomOrderGuest g : ll){
				if(r.getId().equals(g.getOrderId())){
					TErpHotelRoomOrderVO v = new TErpHotelRoomOrderVO(r);
					List<TErpHotelRoomOrderGuest> guests = new ArrayList<TErpHotelRoomOrderGuest>();
					guests.add(g);
					v.setGuests(guests);
					o_da.add(v);
				}
			}
		}
		page.setRecords(o_da);
		return page;
	}

	@Override
	public List<TErpHotelRoomOrderEx> selectRoomOrderExport(Map<String, Object> p) {
		return TErpHotelRoomOrderDAO.selectRoomOrderExport(p);
	}

	@Transactional
	@Override
	public boolean deleteRoomOrder(List<Integer> idList) {
		boolean flag = false;
		TErpHotelRoomOrderDAO.updateOrderDel(idList);
		flag = true;
		return flag;
	}

	@Override
	public boolean updateStatus(TErpHotelRoomOrder po) {
		boolean flag = false;
		TErpHotelRoomOrderDAO.updateStatus(po);
		flag = true;
		return flag;
	}
	
}
