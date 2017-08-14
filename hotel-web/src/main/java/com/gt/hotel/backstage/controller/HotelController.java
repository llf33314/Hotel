package com.gt.hotel.backstage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gt.hotel.base.BaseController;
import com.gt.hotel.web.service.TErpHotelService;

@RestController
@RequestMapping("/backstage")
public class HotelController extends BaseController{
	
	@Autowired
	TErpHotelService tErpHotelService;
	
	

}
