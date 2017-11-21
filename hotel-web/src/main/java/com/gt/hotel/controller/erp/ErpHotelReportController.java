package com.gt.hotel.controller.erp;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.BusinessConditionsVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 酒店ERP - 统计报表
 * @author Reverien9@gmail.com
 * 2017年11月21日 上午10:52:56
 */
@Api(tags = "酒店ERP 统计报表")
@RestController
@RequestMapping("/erp/report")
public class ErpHotelReportController extends BaseController {
	
	@Autowired
    private WXMPApiUtil WXMPApiUtil;
	
	@Autowired
    private WebServerConfigurationProperties properties;

    private static final Logger logger = LoggerFactory.getLogger(ErpHotelReportController.class);

    @ApiOperation(value = "酒店营业状况", notes = "酒店营业状况")
    @GetMapping(value = "businessConditions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<BusinessConditionsVo> businessConditions(
    		HttpServletRequest request) {
    	Integer busid = getLoginUser(request).getId();
    	BusinessConditionsVo b = new BusinessConditionsVo();
    	return ResponseDTO.createBySuccess(b);
    }
    
}
