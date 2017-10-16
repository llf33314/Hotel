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
import com.gt.hotel.param.ActivityParamter;
import com.gt.hotel.vo.ActivityVo;
import com.gt.hotel.web.service.TActivityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "酒店后台-活动设置")
@RestController
@RequestMapping("/back/activity")
public class HotelActivityController extends BaseController {
	
	@Autowired
	TActivityService tActivityService;
	
	@ApiOperation( value = "查询 活动 列表", notes = "查询 活动 列表" )
	@ApiResponses( {@ApiResponse( code = 0, message = "分页对象", response = ResponseDTO.class ), 
		@ApiResponse( code = 1, message = "", response = ActivityVo.class )} )
	@GetMapping( value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@SuppressWarnings( { "rawtypes", "unchecked" } )
	public ResponseDTO activityR(ActivityParamter.Query param) {
		Page<ActivityVo> page = param.initPage();
		page = tActivityService.queryActivity(param, page);
		return ResponseDTO.createBySuccess(page);
	}
	
	@ApiOperation(value = "编辑 活动", notes = "编辑 活动")
	@ApiResponses({ @ApiResponse(code = 0, message = "", response = ResponseDTO.class) })
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO activityCU(@RequestBody ActivityParamter.SaveOrUpdate arooms, HttpSession session) {
		Integer busid = getLoginUserId(session);
		tActivityService.editActivity(busid, arooms);
		return ResponseDTO.createBySuccess();
	}
	
	@ApiOperation( value = "查询 活动 对象", notes = "查询 活动 对象" )
	@ApiResponses( {@ApiResponse( code = 0, message = "响应对象", response = ResponseDTO.class ), 
		@ApiResponse( code = 1, message = "", response = ActivityVo.class )} )
	@GetMapping( value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@SuppressWarnings( { "rawtypes", "unchecked" } )
	public ResponseDTO activityR(@PathVariable("id") @ApiParam("活动ID") Integer id) {
		ActivityVo a = tActivityService.queryActivityOne(id);
		return ResponseDTO.createBySuccess(a);
	}
	
}
