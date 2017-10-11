package com.gt.hotel.controller.back;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.requestEntity.RoomCategoryParameter;
import com.gt.hotel.responseEntity.RoomCategoryList;
import com.gt.hotel.web.service.TRoomCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api( tags = "酒店后台-房间管理" )
@RestController
@RequestMapping( "/back/room" )
public class HotelRoomController extends BaseController {
	
	@Autowired
	TRoomCategoryService tRoomCategoryService;
	
	@ApiOperation( value = "房型列表", notes = "房型列表" )
	@ApiResponses( {@ApiResponse( code = 0, message = "分页对象", response = ResponseDTO.class ), 
		@ApiResponse( code = 1, message = "房型列表对象", response = RoomCategoryList.class )} )
	@GetMapping( value = "queryRoomCategory", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@SuppressWarnings( "rawtypes" )
	public ResponseDTO roomCategoryR(RoomCategoryParameter.queryRoomCategory param) {
		Page< RoomCategoryList > page = new Page<>(param.getPage(), param.getPageSize());
		page = tRoomCategoryService.queryRoomCategory(param, page);
		return ResponseDTO.createBySuccess(page);
	}
	
	@ApiOperation( value = "新增 或 更新 房型", notes = "新增 或 更新 房型" )
	@ApiResponses( {@ApiResponse( code = 0, message = "", response = ResponseDTO.class )} )
	@PostMapping( value = "insertHotel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@SuppressWarnings( "rawtypes" )
	public ResponseDTO roomCategoryCU(@Validated RoomCategoryParameter.SaveOrUpdate roomCategory, BindingResult bindingResult, HttpSession session) {
//		for(String s : roomCategory.getImages())
//			System.err.println(s);
		InvalidParameter(bindingResult);
		Integer busid = getLoginUserId(session);
		boolean flag = tRoomCategoryService.roomCategoryCU(busid, roomCategory);
		if(flag) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	
}
