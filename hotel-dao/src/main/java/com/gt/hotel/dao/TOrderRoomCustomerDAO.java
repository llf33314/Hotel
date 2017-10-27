package com.gt.hotel.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TOrderRoomCustomer;

/**
 * <p>
  * 登记入住客户 与 订单记录 一间客房 1 : n 入住客户身份信息(类似客户入住历史记录) Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
public interface TOrderRoomCustomerDAO extends BaseMapper<TOrderRoomCustomer> {

}