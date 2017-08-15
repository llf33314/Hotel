package com.gt.hotel.backstage.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ServerResponse;
import com.gt.hotel.entity.TErpHotelActivity;
import com.gt.hotel.entity.TErpHotelActivityRoomSuite;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.web.service.TErpHotelActivityService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/backstage")
public class HotelActivityController extends BaseController{
	
	@Autowired
	TErpHotelActivityService TErpHotelActivityService;
	
	@ApiOperation(value = "活动设置", notes = "query (特价、钟点、秒杀 房)")
	@ApiImplicitParams({@ApiImplicitParam(name = "pageSize", value = "每页显示多少条数据", paramType = "query", required = false, dataType = "int", defaultValue = "10"),
			@ApiImplicitParam(name = "pageIndex", value = "当前页码", paramType = "query", required = false, dataType = "int", defaultValue = "1"),
			@ApiImplicitParam(name = "hotelId", value = "酒店ID", paramType = "query", required = true, dataType = "int", defaultValue = "0"), 
			@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "query", required = false, dataType = "String", defaultValue = ""), 
			@ApiImplicitParam(name = "type", value = "类型（0=秒杀, 1=团购, 2=特价房, 3=时租房）", paramType = "query", required = true, dataType = "Integer", defaultValue = "0"), 
			@ApiImplicitParam(name = "status", value = "活动状态(0=未开始, 1=进行中, 2=已结束)", paramType = "query", required = false, dataType = "Integer", defaultValue = "-1") })
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel/activity")
	public ServerResponse hotelActivityQuery(@RequestParam(name = "hotelId", required = true) String hotelId, 
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer pageIndex, 
			@RequestParam(required = true, defaultValue = "0") Integer type, 
			@RequestParam(defaultValue = "-1") Integer status, String keyword){
		boolean flag = false;
		Page<TErpHotelActivity> page = new Page<>(pageIndex, pageSize);
		try {
			Wrapper<TErpHotelActivity> wrapper = new EntityWrapper<TErpHotelActivity>();
			wrapper.eq("hotel_id", hotelId);
			wrapper.eq("type", type);
			wrapper.eq(status != null && status != -1, "status", status);
			wrapper.like(keyword != null, "activity_name", keyword);
			page = TErpHotelActivityService.selectPage(page, wrapper);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess(page);
		else return ServerResponse.createByError();
	}
	
	@ApiOperation(value = "活动设置", notes = "新增 or 更新")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID(更新时需要)", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "hotelId", value = "酒店ID", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "activityName", value = "活动名称", required = true, dataType = "String"), 
		@ApiImplicitParam(name = "activityStime", value = "生效时间 前", required = true, dataType = "Date"), 
		@ApiImplicitParam(name = "activityEtime", value = "生效时间 后", required = true, dataType = "Date"), 
		@ApiImplicitParam(name = "type", value = "类型（0=秒杀, 1=团购, 2=特价房, 3=时租房）", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "isRoomLimit", value = "是否开启房间限购", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "roomLimit", value = "房间限购数（间/人）", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "isVipcard", value = "是否关联会员卡", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "isCardvolume", value = "是否关联卡券", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "rule", value = "规则", required = false, dataType = "String"), 
		@ApiImplicitParam(name = "isRoomCount", value = "是否显示房间剩余数", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "roomCount", value = "当房剩余 room_count  间时显", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "liveStime", value = "居住开始时间", required = false, dataType = "Date"), 
		@ApiImplicitParam(name = "liveEtime", value = "居住结束时间", required = false, dataType = "Date"), 
		@ApiImplicitParam(name = "grouplowest", value = "团购最低间数", required = false, dataType = "String"), 
		@ApiImplicitParam(name = "grouplowesttip", value = "当达不到最低间数提示", required = false, dataType = "String"), 
		@ApiImplicitParam(name = "limitHour", value = "时租房使用时间", required = false, dataType = "String"), 
		@ApiImplicitParam(name = "whenrentstime", value = "时租房可预订起始时间", required = false, dataType = "Date"), 
		@ApiImplicitParam(name = "whenrentetime", value = "时租房可预订结束时间", required = false, dataType = "Date"), 
		@ApiImplicitParam(name = "activitySuites", value = "房间信息数组(形如: '[{roomId:1, suiteId:2, price:666}, {roomId:1, suiteId:3, price:666}]')", required = false, dataType = "String")})
	@SuppressWarnings("rawtypes")
	@PostMapping("/hotel/activity")
	public ServerResponse hotelActivityInsert(TErpHotelActivity activity, String activitySuites, HttpSession session){
		boolean flag = false;
		try {
			activity.setBusId(getUser(session).getId());
			List<TErpHotelActivityRoomSuite> activitySuiteList = JSON.parseArray(activitySuites, TErpHotelActivityRoomSuite.class);
			flag = TErpHotelActivityService.insertOrUpdate(activity, activitySuiteList);
		} catch (Exception e) {
			logger.error("backstage hotel erpset post error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess();
		else return ServerResponse.createByError();
	}
	
	@ApiOperation(value = "活动设置", notes = "删除")
	@ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "ID(数组)", required = true, dataType = "Integer[]")})
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/hotel/activity")
	public ServerResponse hotelActivityDel(Integer[] ids, HttpSession session){
		boolean flag = false;
		try {
			List<Integer> idList = Arrays.asList(ids);
			flag = TErpHotelActivityService.delHotelActivity(idList);
		} catch (Exception e) {
			logger.error("backstage hotel longtimeroom delete error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess();
		else return ServerResponse.createByError();
	}
	
	
}
