package com.gt.hotel.backstage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.TErpHotel;
import com.gt.hotel.entity.TErpHotelERPSet;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.web.service.TErpHotelService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/backstage")
public class HotelMobileController extends BaseController{
	
	@Autowired
	TErpHotelService TErpHotelService;
	
	@ApiOperation(value = "酒店后台-移动端设置-手机页面设置", notes = "查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "hotelId", value = "酒店ID", paramType = "query", required = true, dataType = "Integer", defaultValue = "0")})
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel/mobile")
	public ResponseDTO hotelErpSetOne(@RequestParam(name = "id", required = true) Integer hotelId){
		boolean flag = false;
		TErpHotelERPSet hotel = new TErpHotelERPSet();
		try {
			Wrapper<TErpHotel> wrapper = new EntityWrapper<TErpHotel>();
//			if(id != null) wrapper.eq("id", id);
//			hotel = TErpHotelService.selectERPSetById(id);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel erpSet get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess(hotel);
		else return ResponseDTO.createByError();
	}
	
}
