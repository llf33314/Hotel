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
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.param.RoomCategoryParameter;
import com.gt.hotel.param.RoomCategoryParameter.SaveOrUpdate;
import com.gt.hotel.param.RoomParameter;
import com.gt.hotel.vo.RoomCategoryVo;
import com.gt.hotel.vo.RoomVo;
import com.gt.hotel.web.service.TRoomCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "酒店后台-房间管理")
@RestController
@RequestMapping("/back/room")
public class HotelRoomController extends BaseController {

	@Autowired
	TRoomCategoryService tRoomCategoryService;

	@ApiOperation(value = "房型列表", notes = "房型列表")
	@ApiResponses({ @ApiResponse(code = 0, message = "分页对象", response = ResponseDTO.class),
			@ApiResponse(code = 1, message = "房型列表对象", response = RoomCategoryVo.class) })
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseDTO roomCategoryR(@Validated @ModelAttribute RoomCategoryParameter.QueryRoomCategory param,
			BindingResult bindingResult) {
		InvalidParameter(bindingResult);
		Page<RoomCategoryVo> page = param.initPage();
		page = tRoomCategoryService.queryRoomCategory(param, page);
		return ResponseDTO.createBySuccess(page);
	}

	@ApiOperation(value = "新增 或 更新 房型", notes = "新增 或 更新 房型")
	@ApiResponses({ @ApiResponse(code = 0, message = "", response = ResponseDTO.class) })
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO roomCategoryCU(@Validated @ModelAttribute SaveOrUpdate roomCategory, BindingResult bindingResult,
			HttpSession session) {
		// for(String s : roomCategory.getImages())
		// System.err.println(s);
		InvalidParameter(bindingResult);
		Integer busid = getLoginUserId(session);
		tRoomCategoryService.roomCategoryCU(busid, roomCategory);
		return ResponseDTO.createBySuccess();
	}

	@ApiOperation(value = "房型更新对象", notes = "房型更新对象")
	@ApiResponses({ @ApiResponse(code = 0, message = "分页对象", response = ResponseDTO.class),
			@ApiResponse(code = 1, message = "房型列表对象", response = RoomCategoryVo.class) })
	@GetMapping(value = "queryOne/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings({ "rawtypes" })
	public ResponseDTO roomCategoryROne(@PathVariable("id") @ApiParam("酒店ID") Integer id) {
		RoomCategoryVo roomCategoryVo = null;
		roomCategoryVo = tRoomCategoryService.queryRoomCategoryOne(id);
		return ResponseDTO.createBySuccess(roomCategoryVo);
	}

	@ApiOperation(value = "删除 房型", notes = "删除 房型")
	@ApiResponses({ @ApiResponse(code = 0, message = "", response = ResponseDTO.class) })
	@DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO roomCategoryD(@RequestBody @ApiParam("房型ID 数组") List<Integer> ids, HttpSession session) {
		Integer busid = getLoginUserId(session);
		tRoomCategoryService.delRoomCategory(busid, ids);
		return ResponseDTO.createBySuccess();
	}

	@ApiOperation(value = "房间 集合", notes = "房间 集合")
	@ApiResponses({ @ApiResponse(code = 0, message = "分页对象", response = ResponseDTO.class),
			@ApiResponse(code = 1, message = "房型列表对象", response = RoomVo.class) })
	@GetMapping(value = "{roomCategoryId}-room", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseDTO roomRList(@PathVariable("roomCategoryId") @ApiParam("房型ID") Integer roomCategoryId,
			HotelPage hpage) {
		Page<RoomVo> page = hpage.initPage();
		page = tRoomCategoryService.queryRoomList(roomCategoryId, page);
		return ResponseDTO.createBySuccess(page);
	}

	@ApiOperation(value = "编辑 房间", notes = "编辑 房间")
	@ApiImplicitParams({ @ApiImplicitParam(value = "房型对象", required = true, paramType = "query") })
	@ApiResponses({ @ApiResponse(code = 0, message = "", response = ResponseDTO.class) })
	@PostMapping(value = "editRoom", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO roomCU(@RequestBody @ApiParam("房型对象集合") List<RoomParameter.SaveOrUpdate> rooms,
			HttpSession session) {
		Integer busid = getLoginUserId(session);
		tRoomCategoryService.editRooms(busid, rooms);
		return ResponseDTO.createBySuccess();
	}

}
