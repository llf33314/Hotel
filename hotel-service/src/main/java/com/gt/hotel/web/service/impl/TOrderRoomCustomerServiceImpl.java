package com.gt.hotel.web.service.impl;

import org.springframework.stereotype.Service;

import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TOrderRoomCustomerDAO;
import com.gt.hotel.entity.TOrderRoomCustomer;
import com.gt.hotel.web.service.TOrderRoomCustomerService;

/**
 * <p>
 * 登记入住客户 与 订单记录 一间客房 1 : n 入住客户身份信息(类似客户入住历史记录) 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
@Service
public class TOrderRoomCustomerServiceImpl extends BaseServiceImpl<TOrderRoomCustomerDAO, TOrderRoomCustomer> implements TOrderRoomCustomerService {
	
}
