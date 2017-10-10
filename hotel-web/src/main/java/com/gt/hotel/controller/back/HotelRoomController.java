package com.gt.hotel.controller.back;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.requestEntity.RoomCategoryParameter;
import com.gt.hotel.responseEntity.ResRoomCatepory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api( tags = "酒店后台-房间管理" )
@RestController
@RequestMapping( "/back/room" )
public class HotelRoomController extends BaseController {
	
	@ApiOperation( value = "房型列表", notes = "房型列表" )
	@ApiResponses( {@ApiResponse( code = 0, message = "分页对象", response = ResponseDTO.class ), 
		@ApiResponse( code = 1, message = "房型列表对象", response = ResRoomCatepory.RoomCategoryList.class )} )
	@GetMapping( value = "queryRoomCategory", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@SuppressWarnings( "rawtypes" )
	public ResponseDTO roomCategoryR(RoomCategoryParameter.queryRoomCategory param, HttpSession session) {
		Page< ResRoomCatepory.RoomCategoryList > page = new Page<>(param.getPage(), param.getPageSize());
		Integer busid = getLoginUserId(session);
//		page = tHotelService.queryHotelHome(busid, page);
		return ResponseDTO.createBySuccess(page);
	}
	
}
