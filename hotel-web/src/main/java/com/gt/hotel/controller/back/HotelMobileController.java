package com.gt.hotel.controller.back;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.param.HotelMobileParameter;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.vo.HotelSettingVo;
import com.gt.hotel.vo.SysDictionaryVo;
import com.gt.hotel.web.service.SysDictionaryService;
import com.gt.hotel.web.service.THotelSettingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api( tags = "酒店后台-移动端设置" )
@RestController
@RequestMapping( "/back/mobile" )
public class HotelMobileController extends BaseController {
	
	@Autowired
	THotelSettingService tHotelSettingService;

	@Autowired
	SysDictionaryService sysDictionaryService;

	@ApiOperation( value = "查询 移动端设置 对象", notes = "查询 移动端设置 对象" )
	@ApiResponses( {@ApiResponse( code = 0, message = "响应对象", response = ResponseDTO.class ), 
		@ApiResponse( code = 1, message = "", response = HotelSettingVo.class )} )
	@GetMapping( value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@SuppressWarnings( { "rawtypes" } )
	public ResponseDTO phoneSettingR(@PathVariable("hotelId") @ApiParam("酒店ID") Integer hotelId) {
		HotelSettingVo setting = tHotelSettingService.queryHotelSettingOne(hotelId);
		return ResponseDTO.createBySuccess(setting);
	}
	
	@ApiOperation(value = "保存 移动端设置", notes = "保存 移动端设置")
	@ApiResponses({ @ApiResponse(code = 0, message = "", response = ResponseDTO.class) })
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO phoneSettingCU(@RequestBody HotelMobileParameter.SaveOrUpdate setting, HttpSession session) {
		Integer busid = getLoginUserId(session);
		tHotelSettingService.saveSetting(busid, setting);
		return ResponseDTO.createBySuccess();
	}
	
	@ApiOperation( value = "查询 发票列表", notes = "查询 发票列表" )
	@ApiResponses( {@ApiResponse( code = 0, message = "响应对象", response = ResponseDTO.class ), 
		@ApiResponse( code = 1, message = "", response = SysDictionaryVo.class )} )
	@GetMapping( value = "invoice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@SuppressWarnings( { "rawtypes" } )
	public ResponseDTO invoiceR(HotelPage param) {
		Page<SysDictionaryVo> page = sysDictionaryService.queryInvoice(param);
		return ResponseDTO.createBySuccess(page);
	}
	
	//////////////////////////////////////////////////////// 客房订餐 ////////////////////////////////////////////////////////////////
	
	@ApiOperation(value = "保存 订餐设置", notes = "保存 订餐设置")
	@ApiResponses({ @ApiResponse(code = 0, message = "", response = ResponseDTO.class) })
	@PostMapping(value = "food", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO foodCU(@RequestBody HotelMobileParameter.SaveOrUpdate setting, HttpSession session) {
		//TODO 尚未有表
		Integer busid = getLoginUserId(session);
		tHotelSettingService.saveSetting(busid, setting);
		return ResponseDTO.createBySuccess();
	}
	
	
}
