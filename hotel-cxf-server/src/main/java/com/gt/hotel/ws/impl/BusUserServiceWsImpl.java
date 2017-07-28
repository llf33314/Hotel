package com.gt.hotel.ws.impl;

import com.gt.hotel.dao.BusUserDAO;
import com.gt.hotel.entity.BusUser;
import com.gt.hotel.ws.BusUserServiceWs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/07/28
 */
@WebService( serviceName = "busUserServiceWs", portName = "busUserServiceWsPort", targetNamespace = "http://ws.hotel.gt.com/", endpointInterface = "com.gt.hotel.ws.BusUserServiceWs" )
@Component
public class BusUserServiceWsImpl implements BusUserServiceWs {

    @Autowired
    private BusUserDAO busUserDAO;

    @Override
    public BusUser findByUid( Integer uid ) {
	return this.busUserDAO.selectById( uid );
    }
}
