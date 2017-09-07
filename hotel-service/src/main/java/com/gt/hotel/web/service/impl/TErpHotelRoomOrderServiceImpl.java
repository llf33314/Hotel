package com.gt.hotel.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TErpHotelDAO;
import com.gt.hotel.dao.TErpHotelRoomOrderDAO;
import com.gt.hotel.entity.TErpHotel;
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
	
	@Autowired
	TErpHotelDAO TErpHotelDAO;
	
	@Override
	public Page<TErpHotelRoomOrderVO> selectRoomOrderPage(Page<TErpHotelRoomOrderVO> page,
			Wrapper<TErpHotelRoomOrder> wrapper, Wrapper<TErpHotelRoomOrderGuest> wrapperg) {
		SqlHelper.fillWrapper(page, wrapper);
		List<TErpHotelRoomOrderVO> o_da = new ArrayList<TErpHotelRoomOrderVO>();
		List<TErpHotelRoomOrder> l = this.selectList(wrapper);
		Integer li = this.selectCount(wrapper);
		page.setTotal(li);
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("bus_id", wrapper.getParamNameValuePairs().get("MPGENVAL1"));
		List<TErpHotel> h = TErpHotelDAO.selectByMap(columnMap);
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
					for(TErpHotel _h : h) if(r.getHotelId().equals(_h.getId())) v.setHotelName(_h.getName());
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

	@Override
	public Page<TErpHotelRoomOrderEx> selectRoomOrderPageEx(Page<TErpHotelRoomOrderEx> page,
			Map<String, Object> param) {
		Integer c = page.getCurrent();
		Integer s = page.getSize();
		param.put("page", (c-1)*s);
		param.put("pageSize", s);
		List<TErpHotelRoomOrderEx> l = TErpHotelRoomOrderDAO.selectRoomOrderExport(param);
		Integer total = TErpHotelRoomOrderDAO.selectRoomOrderExportCount(param);
		page.setTotal(total);
		page.setRecords(l);
		return page;
	}
	
}
