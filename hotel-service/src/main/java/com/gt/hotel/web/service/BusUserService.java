package com.gt.hotel.web.service;

import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.BusUser;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhangmz
 * @since 2017-07-10
 */
public interface BusUserService extends BaseService< BusUser > {

    BusUser findUser( Integer uid );

    List< BusUser > findUsername( String username );
}
