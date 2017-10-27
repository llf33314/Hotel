package com.gt.hotel.dao;

import com.gt.hotel.entity.TOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 算客户实际 预约(到店办理)->入住->消费(酒店内消费)->退房->结算 的所有消费(房费+其他费) Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
public interface TOrderDAO extends BaseMapper<TOrder> {

}