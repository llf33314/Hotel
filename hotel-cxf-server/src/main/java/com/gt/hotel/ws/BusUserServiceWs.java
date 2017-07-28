package com.gt.hotel.ws;

import com.gt.hotel.entity.BusUser;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/07/28
 */
@WebService
public interface BusUserServiceWs {

    @WebMethod
    @WebResult( name = "BusUser" )
    BusUser findByUid( @WebParam( name = "uid" ) Integer uid );

}
