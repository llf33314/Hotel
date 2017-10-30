package com.gt.hotel.controller.back;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
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
import com.gt.hotel.entity.TRoomCalendar;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.param.RoomCalendarParamter;
import com.gt.hotel.param.RoomCategoryParameter;
import com.gt.hotel.param.RoomCategoryParameter.CategorySaveOrUpdate;
import com.gt.hotel.param.RoomCategoryParameter.QueryRoomCategoryOne;
import com.gt.hotel.param.RoomParameter;
import com.gt.hotel.vo.InfrastructureVo;
import com.gt.hotel.vo.RoomCalendarVo;
import com.gt.hotel.vo.RoomCategoryVo;
import com.gt.hotel.vo.RoomVo;
import com.gt.hotel.web.service.TRoomCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 酒店后台-房型管理
 *
 * @author Reverien9@gmail.com 
 * 2017年10月25日 下午12:04:12
 */
@Api(tags = "酒店后台-房型管理")
@RestController
@RequestMapping("/back/roomCategory")
public class HotelRoomController extends BaseController {

	@Autowired
	TRoomCategoryService tRoomCategoryService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "房型列表", notes = "房型列表")
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<Page<RoomCategoryVo>> roomCategoryR(
			@Validated @ModelAttribute RoomCategoryParameter.QueryRoomCategory param, BindingResult bindingResult) {
		ResponseDTO msg = InvalidParameterII(bindingResult);
        if(msg != null) {
        	return msg;
        }
		Page<RoomCategoryVo> page = tRoomCategoryService.queryRoomCategory(param);
		return ResponseDTO.createBySuccess(page);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "新增 或 更新 房型", notes = "新增 或 更新 房型")
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<QueryRoomCategoryOne> roomCategoryCU(
			@Validated @RequestBody @ApiParam("参数") CategorySaveOrUpdate roomCategory, BindingResult bindingResult,
			HttpSession session) {
		ResponseDTO msg = InvalidParameterII(bindingResult);
        if(msg != null) {
        	return msg;
        }
		Integer busid = getLoginUserId(session);
		Integer id = tRoomCategoryService.roomCategoryCU(busid, roomCategory);
		QueryRoomCategoryOne q = new QueryRoomCategoryOne();
		q.setCategoryId(id);
		return ResponseDTO.createBySuccess(q);
	}

	@ApiOperation(value = "房型更新对象", notes = "房型更新对象")
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<RoomCategoryVo> roomCategoryROne(@PathVariable("id") @ApiParam("房型ID") Integer id) {
		RoomCategoryVo roomCategoryVo = null;
		roomCategoryVo = tRoomCategoryService.queryRoomCategoryOne(id);
		return ResponseDTO.createBySuccess(roomCategoryVo);
	}

	@ApiOperation(value = "删除 房型", notes = "删除 房型")
	@DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO roomCategoryD(@RequestBody @ApiParam("房型ID 数组") List<Integer> ids, HttpSession session) {
		Integer busid = getLoginUserId(session);
		tRoomCategoryService.delRoomCategory(busid, ids);
		return ResponseDTO.createBySuccess();
	}

	////////////////////////////////////////// ↓房型设备↓
	////////////////////////////////////////// ////////////////////////////////////////

	@ApiOperation(value = "房型设备列表", notes = "房型设备房型列表")
	@GetMapping(value = "infrastructure", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<List<InfrastructureVo>> InfrastructureR() {
		List<InfrastructureVo> page = tRoomCategoryService.queryRoomCategoryInfrastructure();
		return ResponseDTO.createBySuccess(page);
	}

	////////////////////////////////////////// ↓房间↓
	////////////////////////////////////////// ////////////////////////////////////////

	@ApiOperation(value = "房间 集合", notes = "房间 集合")
	@GetMapping(value = "{categoryId}/room", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings({ "unchecked" })
	public ResponseDTO<Page<RoomVo>> roomRList(@PathVariable("categoryId") @ApiParam("房型ID") Integer categoryId,
			HotelPage hpage) {
		Page<RoomVo> page = hpage.initPage();
		page = tRoomCategoryService.queryRoomList(categoryId, page);
		return ResponseDTO.createBySuccess(page);
	}

	@ApiOperation(value = "编辑 房间", notes = "编辑 房间")
	@PostMapping(value = "{categoryId}/room", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO roomCU(@RequestBody List<RoomParameter.RoomSaveOrUpdate> rooms,
			@PathVariable("categoryId") Integer categoryId, HttpSession session) {
		Integer busid = getLoginUserId(session);
		tRoomCategoryService.editRooms(busid, categoryId, rooms);
		return ResponseDTO.createBySuccess();
	}

	////////////////////////////////////////// ↓日历↓
	////////////////////////////////////////// ////////////////////////////////////////

	@ApiOperation(value = "查询日历-房型 价格信息", notes = "查询日历-房型 价格信息")
	@GetMapping(value = "{categoryId}/calendar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<Page<RoomCalendarVo>> calendarRList(
			@PathVariable("categoryId") @ApiParam("房型ID") Integer categoryId,
			RoomCalendarParamter.CalendarQuery param) {
		Page<RoomCalendarVo> page = tRoomCategoryService.queryRoomCalendarList(categoryId, param);
		return ResponseDTO.createBySuccess(page);
	}

	@ApiOperation(value = "编辑 房型 价格信息", notes = "编辑 房型 价格信息")
	@PostMapping(value = "{categoryId}/calendar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO roomCalendarCU(@PathVariable("categoryId") Integer categoryId,
			@RequestBody @ApiParam("参数") RoomCalendarParamter.CalendarSaveOrUpdate cal, HttpSession session) {
		Integer busid = getLoginUserId(session);
		Date date = new Date();
		TRoomCalendar calendar = new TRoomCalendar();
		BeanUtils.copyProperties(cal, calendar);
		calendar.setCategoryId(categoryId);
		if (cal.getId() == null) {
			calendar.setCreatedBy(busid);
			calendar.setCreatedAt(date);
		}
		calendar.setUpdatedAt(date);
		calendar.setUpdatedBy(busid);
		if (calendar.insertOrUpdate())
			return ResponseDTO.createBySuccess();
		else
			return ResponseDTO.createByError();
	}

}
