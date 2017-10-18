package com.gt.hotel.controller.back;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.param.ERPParameter;
import com.gt.hotel.param.RoomParameter;
import com.gt.hotel.vo.HotelVo;
import com.gt.hotel.vo.RoomPermanentVo;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.TRoomCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "酒店后台-ERP设置")
@RestController
@RequestMapping("/back/erp")
public class HotelErpSetController extends BaseController {
	
	@Autowired
	private THotelService tHotelService;
	
	@Autowired
	TRoomCategoryService tRoomCategoryService;

	@ApiOperation( value = "查询 酒店ERP对象", notes = "查询酒店ERP对象" )
	@ApiResponses( {@ApiResponse( code = 0, message = "分页对象", response = ResponseDTO.class ), 
		@ApiResponse( code = 1, message = "酒店ERP对象", response = HotelVo.class )} )
	@GetMapping( value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@SuppressWarnings( "rawtypes" )
	public ResponseDTO erpSettingR(@PathVariable("hotelId") Integer hotelId) {
		HotelVo h = tHotelService.queryHotelERP(hotelId);
		return ResponseDTO.createBySuccess(h);
	}
	
	@ApiOperation(value = "保存 ERP前台设置", notes = "保存 ERP前台设置")
	@ApiResponses({ @ApiResponse(code = 0, message = "", response = ResponseDTO.class) })
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO erpSettingCU(@Validated @ModelAttribute ERPParameter.Save save, BindingResult bindingResult, HttpSession session) {
		InvalidParameter(bindingResult);
		Integer busid = getLoginUserId(session);
		tHotelService.SaveHotelERP(busid, save);
		return ResponseDTO.createBySuccess();
	}
	
	//////////////////////////////////////////////////////////// 长包房 //////////////////////////////////////////////////////////
	
	@ApiOperation(value = "保存 长包房设置", notes = "保存 长包房设置")
	@ApiResponses({ @ApiResponse(code = 0, message = "", response = ResponseDTO.class) })
	@PostMapping(value = "roomPermanent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO roomPermanentCU(@Validated @ModelAttribute RoomParameter.RoomPermanent per, BindingResult bindingResult, HttpSession session) {
		InvalidParameter(bindingResult);
		Integer busId = getLoginUserId(session);
		tRoomCategoryService.SaveRoomPermanent(busId, per);
		return ResponseDTO.createBySuccess();
	}
	
	@ApiOperation( value = "查询 长包房设置", notes = "查询 长包房设置" )
	@ApiResponses( {@ApiResponse( code = 0, message = "分页对象", response = ResponseDTO.class ), 
		@ApiResponse( code = 1, message = "", response = RoomPermanentVo.class )} )
	@GetMapping( value = "roomPermanent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@SuppressWarnings( { "rawtypes" } )
	public ResponseDTO roomPermanentR(@Validated @ModelAttribute RoomParameter.RoomPermanentQuery param) {
		Page<RoomPermanentVo> page = tRoomCategoryService.queryRoomPermanent(param);
		return ResponseDTO.createBySuccess(page);
	}
	
	@ApiOperation(value = "删除 长包房设置", notes = "删除 长包房设置")
	@ApiResponses({ @ApiResponse(code = 0, message = "", response = ResponseDTO.class) })
	@DeleteMapping(value = "roomPermanent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO roomPermanentD(@RequestBody @ApiParam("长包房设置ID 数组") List<Integer> ids, HttpSession session) {
		Integer busId = getLoginUserId(session);
		tRoomCategoryService.delRoomPermanent(busId, ids);
		return ResponseDTO.createBySuccess();
	}
	
}
