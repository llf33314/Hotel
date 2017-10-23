package com.gt.hotel.controller.back;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
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

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.EmployeeList;
import com.gt.hotel.param.ERPParameter;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.param.RoomParameter;
import com.gt.hotel.param.WXMPParameter;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.AuthorizationVo;
import com.gt.hotel.vo.HotelVo;
import com.gt.hotel.vo.RoomPermanentVo;
import com.gt.hotel.vo.SysDictionaryVo;
import com.gt.hotel.web.service.SysDictionaryService;
import com.gt.hotel.web.service.TAuthorizationService;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.TRoomCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "酒店后台-ERP设置")
@RestController
@RequestMapping("/back/erp")
public class HotelErpSetController extends BaseController {
	
	@Autowired
	private THotelService tHotelService;
	
	@Autowired
	TRoomCategoryService tRoomCategoryService;
	
	@Autowired
	SysDictionaryService sysDictionaryService;
	
	@Autowired
	TAuthorizationService tAuthorizationService;
	
	@Autowired
	WXMPApiUtil WXMPApiUtil;

	@ApiOperation( value = "查询 酒店ERP对象", notes = "查询酒店ERP对象" )
	@GetMapping( value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseDTO<HotelVo> erpSettingR(@Param("酒店ID") @PathVariable("hotelId") Integer hotelId) {
		HotelVo h = tHotelService.queryHotelERP(hotelId);
		return ResponseDTO.createBySuccess(h);
	}
	
	@ApiOperation(value = "保存 ERP前台设置", notes = "保存 ERP前台设置")
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO erpSettingCU(@Validated @Param("参数") @RequestBody ERPParameter.ERPSave save, BindingResult bindingResult, HttpSession session) {
		InvalidParameter(bindingResult);
		Integer busid = getLoginUserId(session);
		tHotelService.SaveHotelERP(busid, save);
		return ResponseDTO.createBySuccess();
	}
	
	//////////////////////////////////////////////////////////// 长包房 //////////////////////////////////////////////////////////
	
	@ApiOperation(value = "保存 长包房设置", notes = "保存 长包房设置")
	@PostMapping(value = "roomPermanent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO roomPermanentCU(@Validated @Param("参数") @RequestBody RoomParameter.RoomPermanent per, BindingResult bindingResult, HttpSession session) {
		InvalidParameter(bindingResult);
		Integer busId = getLoginUserId(session);
		tRoomCategoryService.SaveRoomPermanent(busId, per);
		return ResponseDTO.createBySuccess();
	}
	
	@ApiOperation( value = "查询 长包房设置", notes = "查询 长包房设置" )
	@GetMapping( value = "roomPermanent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseDTO<Page<RoomPermanentVo>> roomPermanentR(@Validated @Param("参数") @ModelAttribute RoomParameter.RoomPermanentQuery param) {
		Page<RoomPermanentVo> page = tRoomCategoryService.queryRoomPermanent(param);
		return ResponseDTO.createBySuccess(page);
	}
	
	@ApiOperation(value = "删除 长包房设置", notes = "删除 长包房设置")
	@DeleteMapping(value = "roomPermanent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO roomPermanentD(@RequestBody @ApiParam("长包房设置ID 数组") List<Integer> ids, HttpSession session) {
		Integer busId = getLoginUserId(session);
		tRoomCategoryService.delRoomPermanent(busId, ids);
		return ResponseDTO.createBySuccess();
	}
	
	////////////////////////////////////////////////////////////权限设置 //////////////////////////////////////////////////////////
	
	@ApiOperation( value = "查询 权限功能列表", notes = "查询 权限功能列表" )
	@GetMapping(value = "function", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<Page<SysDictionaryVo>> functionR(HotelPage param) {
		Page<SysDictionaryVo> page = sysDictionaryService.queryDictionary(CommonConst.DICT_FUNCTION, param);
		return ResponseDTO.createBySuccess(page);
	}
	
	@ApiOperation( value = "查询 员工列表", notes = "查询 员工列表" )
	@GetMapping(value = "employee/{shopId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<EmployeeList> employeeR(@PathVariable("shopId") Integer shopId, @Param("门店ID") @ModelAttribute WXMPParameter.queryEmployee qe) {
		EmployeeList e = null;
		JSONObject result = WXMPApiUtil.getAllStaffShopId(shopId, qe.getName(), qe.getPhone());
		if("0".equals(result.getString("code"))) {
			e = JSONObject.parseObject(result.getString("data"), EmployeeList.class);
		}else throw new ResponseEntityException(result.getString("msg"));
		return ResponseDTO.createBySuccess(e);
	}
	
	@ApiOperation( value = "查询 授权管理列表", notes = "查询 授权管理列表" )
	@GetMapping(value = "author", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<Page<AuthorizationVo>> authorR(HotelPage param) {
		Page<AuthorizationVo> page = tAuthorizationService.queryAuthor(param);
		return ResponseDTO.createBySuccess(page);
	}
	
	@ApiOperation(value = "新增 授权管理", notes = "新增 授权管理")
	@PostMapping(value = "author", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO roomCategoryCU(@RequestBody @ApiParam("授权管理ID 数组") List<ERPParameter.AuthorSave> authors, HttpSession session) {
		Integer busid = getLoginUserId(session);
		tAuthorizationService.saveAuthor(busid, authors);
		return ResponseDTO.createBySuccess();
	}
	
	@ApiOperation(value = "删除 授权管理", notes = "删除 授权管理")
	@DeleteMapping(value = "author", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO authorD(@RequestBody @ApiParam("授权管理ID 数组") List<Integer> ids, HttpSession session) {
		Integer busId = getLoginUserId(session);
		tAuthorizationService.delAuthor(busId, ids);
		return ResponseDTO.createBySuccess();
	}
	
}
