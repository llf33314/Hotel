package com.gt.hotel.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.TErpHotel;
import com.gt.hotel.entity.TErpHotelRoom;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.web.service.TErpHotelRoomService;
import com.gt.hotel.web.service.TErpHotelService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mobile")
public class MoblieHotelController extends BaseController{
	
	@Autowired
	TErpHotelService TErpHotelService;
	
	@Autowired
	TErpHotelRoomService TErpHotelRoomService;
	
	@ApiOperation(value = "移动端首页-酒店信息", notes = "首页数据")
	@ApiImplicitParams({@ApiImplicitParam(name = "hotelId", value = "酒店ID", paramType = "query", required = true, dataType = "int", defaultValue = "null") })
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel/info")
	public ResponseDTO hotelInfo(@RequestParam(name = "hotelId", required = true) String id){
		boolean flag = false;
		Page<TErpHotel> page = new Page<>(1, 1);
		try {
			Wrapper<TErpHotel> wrapper = new EntityWrapper<TErpHotel>();
			wrapper.eq("id", id);
			page = TErpHotelService.selectPage(page, wrapper);
			flag = true;
		} catch (Exception e) {
			logger.error("mobile hotel get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess(page);
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "移动端首页-房间信息", notes = "首页数据")
	@ApiImplicitParams({@ApiImplicitParam(name = "hotelId", value = "酒店ID", paramType = "query", required = true, dataType = "int", defaultValue = "null"), 
		@ApiImplicitParam(name = "pageSize", value = "每页显示多少条数据", paramType = "query", required = false, dataType = "Integer", defaultValue = "10"),
		@ApiImplicitParam(name = "pageIndex", value = "当前页码", paramType = "query", required = false, dataType = "Integer", defaultValue = "1") })
	@SuppressWarnings("rawtypes")
	@GetMapping("/room/info")
	public ResponseDTO roomInfo(@RequestParam(name = "hotelId", required = true) String id, Integer pageSize, Integer pageIndex){
		boolean flag = false;
		Page<TErpHotelRoom> page = new Page<>(pageIndex, pageSize);
		try {
			Wrapper<TErpHotelRoom> wrapper = new EntityWrapper<TErpHotelRoom>();
			wrapper.eq("hotel_id", id);
			page = TErpHotelRoomService.selectPage(page, wrapper);
			flag = true;
		} catch (Exception e) {
			logger.error("mobile room get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess(page);
		else return ResponseDTO.createByError();
	}
	
	
	
	
	
	
	
}
