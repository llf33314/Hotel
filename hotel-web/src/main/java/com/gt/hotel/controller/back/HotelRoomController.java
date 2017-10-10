package com.gt.hotel.controller.back;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gt.hotel.base.BaseController;

import io.swagger.annotations.Api;

@Api( description = "酒店后台-房间管理" )
@RestController
@RequestMapping( "/back/room" )
public class HotelRoomController extends BaseController {
	
}
