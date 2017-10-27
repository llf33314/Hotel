package com.gt.hotel.web.service.impl;

import org.springframework.stereotype.Service;

import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TOrderDAO;
import com.gt.hotel.entity.TOrder;
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
	
}