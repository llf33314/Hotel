package com.gt.hotel.config;

import com.gt.hotel.ws.BusUserServiceWs;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/07/28
 */
@Configuration
public class CXFServerConfig {
    @Autowired
    private Bus bus;

    @Autowired
    private BusUserServiceWs busUserServiceWs;

    @Bean
    public Endpoint busUserServiceWs() {
	Endpoint endpoint = new EndpointImpl( bus, busUserServiceWs );
	endpoint.publish( "/BusUserServiceWs" );
	return endpoint;
    }

}
