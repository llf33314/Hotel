package com.gt.hotel.controller.erp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gt.hotel.base.BaseController;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.WXMPApiUtil;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * 酒店ERP - 财务
 *
 * @author Reverien9@gmail.com
 * 2017年11月21日 上午10:52:29
 */
@Slf4j
@Api(tags = "酒店ERP 财务")
@RestController
@RequestMapping("/erp/finance")
public class ErpHotelFinanceController extends BaseController {

    @Autowired
    private WXMPApiUtil wxmpApiUtil;

    @Autowired
    private WebServerConfigurationProperties properties;


    

}
