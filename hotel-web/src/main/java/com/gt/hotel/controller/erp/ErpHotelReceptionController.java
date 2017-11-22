package com.gt.hotel.controller.erp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gt.hotel.base.BaseController;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.WXMPApiUtil;

import io.swagger.annotations.Api;

/**
 * 酒店ERP - 前台
 * @author Reverien9@gmail.com
 * 2017年11月21日 上午10:52:50
 */
@Api(tags = "酒店ERP 前台")
@RestController
@RequestMapping("/erp/reception")
public class ErpHotelReceptionController extends BaseController {
	
	@Autowired
    private WXMPApiUtil WXMPApiUtil;
	
	@Autowired
    private WebServerConfigurationProperties properties;

    private static final Logger logger = LoggerFactory.getLogger(ErpHotelReceptionController.class);

    
    
    
}
