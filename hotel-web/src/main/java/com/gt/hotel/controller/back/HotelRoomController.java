package com.gt.hotel.controller.back;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.*;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.web.service.TErpHotelRoomCalendarService;
import com.gt.hotel.web.service.TErpHotelRoomService;
import com.gt.hotel.web.service.TErpHotelRoomSuiteService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Api(description = "酒店后台-房间管理")
@RestController
@RequestMapping("/backstage")
public class HotelRoomController extends BaseController{
	
	@Autowired
	TErpHotelRoomService tErpHotelRoomService;

	@Autowired
	TErpHotelRoomCalendarService tErpHotelRoomCalendarService;
	
	@Autowired
	TErpHotelRoomSuiteService tErpHotelRoomSuiteService;
	
	@ApiOperation(value = "酒店后台-房间管理-列表", notes = "酒店 房间 查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "pageSize", value = "每页显示多少条数据", paramType = "query", required = false, dataType = "Integer", defaultValue = "10"),
			@ApiImplicitParam(name = "pageIndex", value = "当前页码", paramType = "query", required = false, dataType = "Integer", defaultValue = "1"),
			@ApiImplicitParam(name = "hotelId", value = "酒店ID", paramType = "query", required = false, dataType = "Integer"),
			@ApiImplicitParam(name = "id", value = "ID", paramType = "query", required = false, dataType = "Integer")})
	@ApiResponses({@ApiResponse(code = 999, message = "", response = TErpHotelRoomAndCount.class)})
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel/room")
	public ResponseDTO queryHotelroom(@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "hotelId", required = false) Integer hotelId, 
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer pageIndex){
		boolean flag = false;
		Page<TErpHotelRoomAndCount> page = new Page<>(pageIndex, pageSize);
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("hotel_id", hotelId);
			param.put("id", id);
			page = tErpHotelRoomService.selectHotelRoom(page, param);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel room get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess(page);
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-房间管理-添加 or 更新", notes = "酒店 房间 添加")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID(更新时需要)", paramType = "query", required = false, dataType = "Integer"), 
		/*@ApiImplicitParam(name = "hotelId", value = "酒店ID", required = true, dataType = "Integer", defaultValue = "null"), 
		@ApiImplicitParam(name = "type", value = "房间类型", required = false, dataType = "String", defaultValue = "null"), 
		@ApiImplicitParam(name = "total", value = "房型总数", required = false, dataType = "Integer", defaultValue = "null"), 
		@ApiImplicitParam(name = "introduction", value = "简介", required = false, dataType = "String", defaultValue = "null"), 
		@ApiImplicitParam(name = "price", value = "价格", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "deposit", value = "押金", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "ifDiscountPrice", value = "是否启用折扣价", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "discountPrice", value = "折扣价", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "ifWeekendPrice", value = "是否启用周末价", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "weekendPrice", value = "周末价(其他以此类推)", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "teamPrice", value = "团队价", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "teamPriceEffective", value = "团队价生效数量", required = false, dataType = "Integer"),*/ 
		@ApiImplicitParam(name = "images", value = "图片数组(形如: '[{name:'啊', url:'a.jpg'}, {name:'吧', url:'b.jpg'}]')", required = false, dataType = "String", defaultValue = "null")})
	@SuppressWarnings("rawtypes")
	@PostMapping("/hotel/room")
	public ResponseDTO insertHotelRoom(TErpHotelRoom room, String images, HttpSession session){
		boolean flag = false;
		Map<String, Object> p = new HashMap<String, Object>();
		try {
			room.setCreator(getUser(session).getName());
			room.setCreateTime(new Date());
			List<TErpHotelImage> imageList = JSON.parseArray(images, TErpHotelImage.class);
			Integer id = tErpHotelRoomService.roomInsertOrUpdate(room, imageList);
			p.put("roomId", id);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel room post error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess(p);
		else return ResponseDTO.createByError();
	}

	@ApiOperation(value = "酒店后台-房间管理-删除", notes = "酒店 房间 del")
	@ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "ID(数组)", required = true, dataType = "Integer[]")})
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/hotel/room")
	public ResponseDTO hotelRoomDel(Integer[] ids, HttpSession session){
		boolean flag = false;
		try {
			List<Integer> idList = Arrays.asList(ids);
			flag = tErpHotelRoomService.delRoom(idList);
		} catch (Exception e) {
			logger.error("backstage hotel room delete error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-房间管理-日历改价", notes = "查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "roomId", value = "房型ID", paramType = "query", required = true, dataType = "Integer") })
	@ApiResponses({@ApiResponse(code = 999, message = "", response = TErpHotelRoomCalendarVO.class)})
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel/room/calendar")
	public ResponseDTO hotelRoomCalQquery(@RequestParam(name = "roomId") Integer roomId){
		boolean flag = false;
		TErpHotelRoomCalendarVO rc = null;
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("room_id", roomId);
			rc = tErpHotelRoomCalendarService.selectRoomCalendarAll(param);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel room get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess(rc);
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-房间管理-日历改价", notes = "添加 or 更新 or 删除(价格-1, 有ID)")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID(更新或删除时需要)", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "roomId", value = "房间ID", paramType = "query", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "price", value = "价格", paramType = "query", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "time", value = "时间日期", paramType = "query", required = false, dataType = "datetime") })
	@SuppressWarnings("rawtypes")
	@PostMapping("/hotel/room/calendar")
	public ResponseDTO hotelRoomCalEdit(@ApiParam(hidden = true) TErpHotelRoomCalendar roomCal){
		boolean flag = false;
		try {
			if(roomCal.getPrice() != null && roomCal.getPrice() != -1){
				flag = tErpHotelRoomCalendarService.insertOrUpdate(roomCal);
			}else{
				if(roomCal.getId() != null) flag = tErpHotelRoomCalendarService.deleteById(roomCal.getId());
			}
		} catch (Exception e) {
			logger.error("backstage hotel room post error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-房间管理-房间", notes = "查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "roomId", value = "房型ID", paramType = "query", required = true, dataType = "Integer") })
	@ApiResponses({@ApiResponse(code = 999, message = "", response = TErpHotelRoomSuiteFloorVer.class)})
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel/room/suite")
	public ResponseDTO hotelRoomSuiteQquery(@RequestParam(name = "roomId") Integer roomId){
		boolean flag = false;
		List<TErpHotelRoomSuiteFloorVer> list = new ArrayList<TErpHotelRoomSuiteFloorVer>();
		try {
			list = tErpHotelRoomSuiteService.selectFloorVerList(roomId);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel room get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess(list);
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-房间管理-房间ALL", notes = "新增ALL")
	@ApiImplicitParams({@ApiImplicitParam(name = "roomId", value = "房型ID", paramType = "query", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "suites", value = "房间数组字符串([{floor:2, number:3}, {floor:5, number:6}])", paramType = "query", required = true, dataType = "String") })
	@SuppressWarnings("rawtypes")
	@PostMapping("/hotel/room/suiteALL")
	public ResponseDTO hotelRoomSuiteInsertALL(Integer roomId, String suites){
		boolean flag = false;
		try {
			List<TErpHotelRoomSuite> suiteList = JSON.parseArray(suites, TErpHotelRoomSuite.class);
			flag = tErpHotelRoomSuiteService.insertAll(roomId, suiteList);
		} catch (Exception e) {
			logger.error("backstage hotel room post error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-房间管理-房间", notes = "新增")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID(更新时需要)", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "roomId", value = "房型ID", paramType = "query", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "floor", value = "楼层", paramType = "query", required = true, dataType = "String"), 
		@ApiImplicitParam(name = "number", value = "房间号", paramType = "query", required = true, dataType = "String") })
	@SuppressWarnings("rawtypes")
	@PostMapping("/hotel/room/suite")
	public ResponseDTO hotelRoomSuiteInsert(@ApiParam(hidden = true) TErpHotelRoomSuite suite){
		boolean flag = false;
		try {
			flag = tErpHotelRoomSuiteService.insertOrUpdate(suite);
		} catch (Exception e) {
			logger.error("backstage hotel room post error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-房间管理-房间-楼层修改", notes = "楼层修改")
	@ApiImplicitParams({@ApiImplicitParam(name = "roomId", value = "房型ID", paramType = "query", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "oldFloor", value = "旧楼层", paramType = "query", required = true, dataType = "String"), 
		@ApiImplicitParam(name = "newFloor", value = "新楼层", paramType = "query", required = true, dataType = "String") })
	@SuppressWarnings("rawtypes")
	@PostMapping("/hotel/room/suite/floor")
	public ResponseDTO hotelRoomSuiteFloorInsert(Integer roomId, String oldFloor, String newFloor){
		boolean flag = false;
		try {
			Wrapper<TErpHotelRoomSuite> wrapper = new EntityWrapper<TErpHotelRoomSuite>();
			TErpHotelRoomSuite suite = new TErpHotelRoomSuite();
			suite.setFloor(newFloor);
			wrapper.eq("room_id", roomId);
			wrapper.eq("floor", oldFloor);
			flag = tErpHotelRoomSuiteService.update(suite, wrapper);
		} catch (Exception e) {
			logger.error("backstage hotel room post error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-房间管理-房间", notes = "删除(单个房间 or 楼层)(只传其中一个参数, 传两个优先floor)")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "floor", value = "楼层", paramType = "query", required = false, dataType = "String") })
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/hotel/room/suite")
	public ResponseDTO hotelRoomSuiteDel(Integer id, Integer floor, HttpSession session){
		boolean flag = false;
		try {
			Wrapper<TErpHotelRoomSuite> wrapper = new EntityWrapper<TErpHotelRoomSuite>();
			if(floor != null) wrapper.eq("floor", floor);
			if(id != null && floor == null) wrapper.eq("id", id);
			if(id != null && floor != null) wrapper.eq("floor", floor);
			if(id != null || floor != null) flag = tErpHotelRoomSuiteService.delete(wrapper);
		} catch (Exception e) {
			logger.error("backstage hotel room delete error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	
}