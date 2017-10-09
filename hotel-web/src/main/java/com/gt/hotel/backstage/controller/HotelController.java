package com.gt.hotel.backstage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.requestEntity.HotelPage;
import com.gt.hotel.responseEntity.HotelList;
import com.gt.hotel.web.serviceVO.HotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description = "酒店后台-新增酒店")
@RestController
@RequestMapping("/backstage/hotel")
public class HotelController extends BaseController{
	
	@Autowired
	private HotelService hotelService;
	
	@ApiOperation(value = "第一页门店列表", notes = "第一页门店列表")
	@ApiResponses({@ApiResponse(code = 0, message = "分页对象", response = ResponseDTO.class),
		@ApiResponse(code = 1, message = "门店列表", response = HotelList.class)})
	@PostMapping("queryShop")
	@SuppressWarnings("rawtypes")
	public ResponseDTO shopR(@RequestBody @ApiParam(value = "分页请求对象") HotelPage hpage, HttpSession session) {
		Page<HotelList> page = new Page<>(hpage.getPage(), hpage.getPageSize());
		try {
			Integer busid = getUser(session).getId();
			//TODO 55555555555555555555555555555555555555555555555555555
			return ResponseDTO.createBySuccess(page);
		} catch (Exception e) {
			logger.error("backstage hotel queryShop error", e);
		    throw new ResponseEntityException(ResponseEnums.ERROR);
		}
	}
	
	@ApiOperation(value = "酒店列表", notes = "酒店列表")
	@ApiResponses({@ApiResponse(code = 0, message = "分页对象", response = ResponseDTO.class),
		@ApiResponse(code = 1, message = "酒店列表", response = HotelList.class)})
	@PostMapping("queryHotel")
	@SuppressWarnings("rawtypes")
	public ResponseDTO hotelR(@RequestBody @ApiParam(value = "分页请求对象") HotelPage hpage, HttpSession session) {
		Page<HotelList> page = new Page<>(hpage.getPage(), hpage.getPageSize());
		try {
			Integer busid = getUser(session).getId();
			page = hotelService.queryHotelHome(busid, page);
			return ResponseDTO.createBySuccess(page);
		} catch (Exception e) {
			logger.error("backstage hotel queryHotel error", e);
		    throw new ResponseEntityException(ResponseEnums.ERROR);
		}
	}
	
	
	
	
	
}
