package com.gt.hotel.backstage.controller;

import java.util.Arrays;
import java.util.Date;
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
import com.gt.hotel.entity.TErpHotelImage;
import com.gt.hotel.entity.TErpHotelRoom;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.web.service.TErpHotelRoomService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/backstage")
public class HotelRoomController extends BaseController{
	
	@Autowired
	TErpHotelRoomService tErpHotelRoomService;
	
	@ApiOperation(value = "房间管理-列表", notes = "酒店 房间 查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "pageSize", value = "每页显示多少条数据", paramType = "query", required = false, dataType = "int", defaultValue = "10"),
			@ApiImplicitParam(name = "pageIndex", value = "当前页码", paramType = "query", required = false, dataType = "int", defaultValue = "1"),
			@ApiImplicitParam(name = "hotelId", value = "酒店ID", paramType = "query", required = false, dataType = "int", defaultValue = "0"),
			@ApiImplicitParam(name = "id", value = "ID", paramType = "query", required = false, dataType = "int", defaultValue = "0")})
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel/room")
	public ServerResponse queryHotelroom(@RequestParam(name = "id", required = false) Integer id, 
			@RequestParam(name = "hotelId", required = false) Integer hotelId, 
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer pageIndex){
		boolean flag = false;
		Page<TErpHotelRoom> page = new Page<>(pageIndex, pageSize);
		try {
			Wrapper<TErpHotelRoom> wrapper = new EntityWrapper<TErpHotelRoom>();
			wrapper.eq(id != null, "id", id);
			wrapper.eq(hotelId != null, "hotel_id", hotelId);
			page = tErpHotelRoomService.selectPage(page, wrapper);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel room get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess(page);
		else return ServerResponse.createByError();
	}
	
	@ApiOperation(value = "房间管理-添加 or 更新", notes = "酒店 房间 添加")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID(更新时需要)", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "hotelId", value = "酒店ID", required = true, dataType = "Integer", defaultValue = "null"), 
		@ApiImplicitParam(name = "type", value = "房间类型", required = false, dataType = "String", defaultValue = "null"), 
		@ApiImplicitParam(name = "total", value = "房型总数", required = false, dataType = "Integer", defaultValue = "null"), 
		@ApiImplicitParam(name = "introduction", value = "简介", required = false, dataType = "String", defaultValue = "null"), 
		@ApiImplicitParam(name = "price", value = "价格", required = false, dataType = "Integer", defaultValue = "0"), 
		@ApiImplicitParam(name = "deposit", value = "押金", required = false, dataType = "Integer", defaultValue = "0"), 
		@ApiImplicitParam(name = "ifDiscountPrice", value = "是否启用折扣价", required = false, dataType = "Integer", defaultValue = "0"), 
		@ApiImplicitParam(name = "discountPrice", value = "折扣价", required = false, dataType = "Integer", defaultValue = "0"), 
		@ApiImplicitParam(name = "ifWeekendPrice", value = "是否启用周末价", required = false, dataType = "Integer", defaultValue = "0"), 
		@ApiImplicitParam(name = "weekendPrice", value = "周末价(其他以此类推)", required = false, dataType = "Integer", defaultValue = "0"), 
		@ApiImplicitParam(name = "teamPrice", value = "团队价", required = false, dataType = "Integer", defaultValue = "0"), 
		@ApiImplicitParam(name = "teamPriceEffective", value = "团队价生效数量", required = false, dataType = "Integer", defaultValue = "0"), 
		@ApiImplicitParam(name = "images", value = "图片数组(形如: '[{name:'啊', url:'a.jpg'}, {name:'吧', url:'b.jpg'}]')", required = false, dataType = "String", defaultValue = "null")})
	@SuppressWarnings("rawtypes")
	@PostMapping("/hotel/room")
	public ServerResponse insertHotelRoom(TErpHotelRoom room, String images, HttpSession session){
		boolean flag = false;
		try {
			room.setCreator(getUser(session).getName());
			room.setCreateTime(new Date());
			List<TErpHotelImage> imageList = JSON.parseArray(images, TErpHotelImage.class);
			flag = tErpHotelRoomService.roomInsertOrUpdate(room, imageList);
		} catch (Exception e) {
			logger.error("backstage hotel room post error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess();
		else return ServerResponse.createByError();
	}

	@ApiOperation(value = "房间管理-删除", notes = "酒店 房间 del")
	@ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "ID(数组)", required = true, dataType = "Integer[]")})
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/hotel/room")
	public ServerResponse hotelRoomDel(Integer[] ids, HttpSession session){
		boolean flag = false;
		try {
			List<Integer> idList = Arrays.asList(ids);
			flag = tErpHotelRoomService.delRoom(idList);
		} catch (Exception e) {
			logger.error("backstage hotel room delete error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess();
		else return ServerResponse.createByError();
	}
	
}
