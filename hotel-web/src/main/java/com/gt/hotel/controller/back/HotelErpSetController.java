package com.gt.hotel.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.vo.HotelVo;
import com.gt.hotel.web.service.THotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api( tags = "酒店后台-ERP设置" )
@RestController
@RequestMapping( "/back/erp" )
public class HotelErpSetController extends BaseController {
	
	@Autowired
	private THotelService tHotelService;

	@ApiOperation( value = "查询酒店ERP对象", notes = "查询酒店ERP对象" )
	@ApiResponses( {@ApiResponse( code = 0, message = "分页对象", response = ResponseDTO.class ), 
		@ApiResponse( code = 1, message = "酒店ERP对象", response = HotelVo.class )} )
	@GetMapping( value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@SuppressWarnings( "rawtypes" )
	public ResponseDTO hotelR(@PathVariable("hotelId") Integer hotelId) {
		HotelVo h = tHotelService.queryERPHotel(hotelId);
		return ResponseDTO.createBySuccess(h);
	}
	
}
