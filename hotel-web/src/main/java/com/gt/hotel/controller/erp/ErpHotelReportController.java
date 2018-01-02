package com.gt.hotel.controller.erp;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.vo.BusinessConditionsVo;
import com.gt.hotel.vo.CheackInListRevenueVo;
import com.gt.hotel.vo.IncomeDetailsVo;
import com.gt.hotel.vo.RoomCheackInCountVo;
import com.gt.hotel.web.service.TOrderRoomService;
import com.gt.hotel.web.service.TOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 酒店ERP - 统计报表
 * @author Reverien9@gmail.com
 * 2017年11月21日 上午10:52:56
 */
@Slf4j
@Api(tags = "酒店ERP 统计报表")
@RestController
@RequestMapping("/erp/report")
public class ErpHotelReportController extends BaseController {
	@Autowired
	TOrderService orderService;
	
	@Autowired
	TOrderRoomService orderRoomService;


    @ApiOperation(value = "酒店营业状况(支出需对接进销存后)", notes = "酒店营业状况(支出需对接进销存后)")
    @GetMapping(value = "businessConditions/{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<BusinessConditionsVo> businessConditions(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		HttpServletRequest request) {
    	Integer busId = getLoginUser(request).getId();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	BusinessConditionsVo b = orderService.erpGetBusinessConditions(busId, hotelId, sdf.format(System.currentTimeMillis()));
    	return ResponseDTO.createBySuccess(b);
    }
 
    @ApiOperation(value = "客房入住数", notes = "客房入住数")
    @GetMapping(value = "roomCheckInCount/{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<RoomCheackInCountVo> roomCheckInCount(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		HttpServletRequest request) {
    	Integer busId = getLoginUser(request).getId();
    	return ResponseDTO.createBySuccess(orderRoomService.roomCheckInCount(busId, hotelId));
    }
    
    @ApiOperation(value = "收入明细", notes = "收入明细")
    @GetMapping(value = "incomeDetails/{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<IncomeDetailsVo>> incomeDetails(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		@ModelAttribute HotelPage hpage,
    		HttpServletRequest request) {
    	Integer busId = getLoginUser(request).getId();
    	Page<IncomeDetailsVo> page = orderService.erpGetIncomeDetails(busId, hotelId, hpage);
    	return ResponseDTO.createBySuccess(page);
    }
    
    @ApiOperation(value = "近一周入住率", notes = "近一周入住率")
    @GetMapping(value = "occupancyRate/{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<List<CheackInListRevenueVo>> occupancyRate(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		@ApiParam("当前日期(yyyy-MM-dd)(可空)") String now, 
    		HttpServletRequest request) {
    	Integer busId = getLoginUser(request).getId();
		List<CheackInListRevenueVo> l = orderRoomService.erpGetOccupancyRevenue(now, busId, hotelId);
    	return ResponseDTO.createBySuccess(l);
    }
    
}
