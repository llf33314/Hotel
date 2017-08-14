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
import com.gt.hotel.entity.TErpHotel;
import com.gt.hotel.entity.TErpHotelAuthorization;
import com.gt.hotel.entity.TErpHotelERPSet;
import com.gt.hotel.entity.TErpHotelImage;
import com.gt.hotel.entity.TErpHotelLongTimeRoom;
import com.gt.hotel.entity.TErpHotelMemberCheckOutRelation;
import com.gt.hotel.entity.TErpHotelMemberDepositRelation;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.web.service.TErpHotelAuthorizationService;
import com.gt.hotel.web.service.TErpHotelLongTimeRoomService;
import com.gt.hotel.web.service.TErpHotelService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/backstage")
public class HotelErpSetController extends BaseController{
	
	@Autowired
	TErpHotelService tErpHotelService;
	
	@Autowired
	TErpHotelAuthorizationService tErpHotelAuthorizationService;
	
	@Autowired
	TErpHotelLongTimeRoomService tErpHotelLongTimeRoomService;

	@ApiOperation(value = "酒店-ERP设置-ERP前台设置-查询", notes = "ERP设置")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "酒店ID", paramType = "query", required = true, dataType = "int", defaultValue = "0")})
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel/erpSet")
	public ServerResponse hotelErpSetOne(@RequestParam(name = "id", required = false) Integer id){
		boolean flag = false;
		TErpHotelERPSet hotel = new TErpHotelERPSet();
		try {
			Wrapper<TErpHotel> wrapper = new EntityWrapper<TErpHotel>();
			if(id != null) wrapper.eq("id", id);
			hotel = tErpHotelService.selectERPSetById(id);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel erpSet get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess(hotel);
		else return ServerResponse.createByError();
	}
	
	@ApiOperation(value = "酒店-ERP设置-ERP前台设置-保存 or 更新", notes = "ERP设置")
	@ApiImplicitParams({@ApiImplicitParam(name = "hotelId", value = "酒店ID", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "logo", value = "酒店LOGO URL", required = true, dataType = "String"), 
		@ApiImplicitParam(name = "ifBreakfast", value = "是否开启早餐券", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "breakfastQuantity", value = "默认早餐券数量", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "ifFreeDeposit", value = "是否开启会员免押金", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "ifLastCheckOut", value = "是否开启会员最晚退房时间", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "deposits", value = "免押金数组(形如: '[{hotelId:1, vipLevel:1, ifFreeDeposit:1}, {hotelId:1, vipLevel:2, ifFreeDeposit:1}]')", required = true, dataType = "String"), 
		@ApiImplicitParam(name = "checkOuts", value = "最晚退房数组(形如: '[{hotelId:1, vipLevel:1, last_check_out:\"2017-01-01 01:01:01\"}, {hotelId:1, vipLevel:2, last_check_out:\"2017-01-01 01:01:01\"}]')", required = true, dataType = "String")})
	@SuppressWarnings("rawtypes")
	@PostMapping("/hotel/erpSet")
	public ServerResponse hotelErpSetInsert(Integer hotelId, String logo, Integer ifBreakfast, Integer breakfastQuantity, Integer ifFreeDeposit, Integer ifLastCheckOut, 
			String deposits, String checkOuts){
		boolean flag = false;
		try {
			TErpHotel hotel = new TErpHotel();
			hotel.setId(hotelId);
			hotel.setIfBreakfast(ifBreakfast);
			hotel.setBreakfastQuantity(breakfastQuantity);
			hotel.setIfFreeDeposit(ifFreeDeposit);
			hotel.setIfLastCheckOut(ifLastCheckOut);
			TErpHotelImage hotelImage = new TErpHotelImage();
			hotelImage.setSubjectionId(hotelId);
			hotelImage.setName(logo);
			hotelImage.setUrl(logo);
			hotelImage.setType("logo");
			hotelImage.setSubjection(0);
			List<TErpHotelMemberDepositRelation> depositList = JSON.parseArray(deposits, TErpHotelMemberDepositRelation.class);
			List<TErpHotelMemberCheckOutRelation> checkOutList = JSON.parseArray(checkOuts, TErpHotelMemberCheckOutRelation.class);
//			System.err.println(hotel);
//			System.err.println(hotelImage);
//			System.err.println(depositList);
//			System.err.println(checkOutList);
			flag = tErpHotelService.hotelErpSet(hotel, hotelImage, depositList, checkOutList);	
			
		} catch (Exception e) {
			logger.error("backstage hotel erpset post error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess();
		else return ServerResponse.createByError();
	}
	
	@ApiOperation(value = "酒店-ERP设置-长包房-添加 or 更新", notes = "ERP设置")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID(更新时需输入)", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "hotelId", value = "酒店ID", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "roomId", value = "房型ID", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "type", value = "房型类型(名)", required = false, dataType = "String"),
		@ApiImplicitParam(name = "ruleName", value = "规则名称", required = false, dataType = "String"),
		@ApiImplicitParam(name = "minimumDay", value = "最少入住天数", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "price", value = "价格", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "deposit", value = "押金", required = false, dataType = "Integer")})
	@SuppressWarnings("rawtypes")
	@PostMapping("/hotel/longtimeroom")
	public ServerResponse hotelLTRoomInsert(TErpHotelLongTimeRoom ltRoom, HttpSession session){
		boolean flag = false;
		try {
			ltRoom.setCreator(getUser(session).getName());
			ltRoom.setCreateTime(new Date());
			System.err.println(ltRoom);
			flag = tErpHotelLongTimeRoomService.insertOrUpdate(ltRoom);	
		} catch (Exception e) {
			logger.error("backstage hotel longtimeroom post error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess();
		else return ServerResponse.createByError();
	}
	
	@ApiOperation(value = "酒店-ERP设置-长包房-列表", notes = "ERP设置")
	@ApiImplicitParams({@ApiImplicitParam(name = "hotelId", value = "酒店ID", paramType = "query", required = true, dataType = "int", defaultValue = "0")})
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel/longtimeroom")
	public ServerResponse hotelLTRoomList(@RequestParam(name = "hotelId", required = true) Integer hotelId){
		boolean flag = false;
		List<TErpHotelLongTimeRoom> ltr = null;
		try {
			Wrapper<TErpHotelLongTimeRoom> wrapper = new EntityWrapper<TErpHotelLongTimeRoom>();
			wrapper.eq(hotelId != null, "hotel_id", hotelId);
			ltr = tErpHotelLongTimeRoomService.selectList(wrapper);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel longtimeroom get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess(ltr);
		else return ServerResponse.createByError();
	}
	
	@ApiOperation(value = "酒店-ERP设置-长包房-删除", notes = "ERP设置")
	@ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "ID(数组)", required = true, dataType = "Integer[]")})
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/hotel/longtimeroom")
	public ServerResponse hotelLTRoomDel(Integer[] ids, HttpSession session){
		boolean flag = false;
		try {
			List<Integer> idList = Arrays.asList(ids);
			flag = tErpHotelLongTimeRoomService.deleteBatchIds(idList);
		} catch (Exception e) {
			logger.error("backstage hotel longtimeroom delete error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess();
		else return ServerResponse.createByError();
	}
	
	@ApiOperation(value = "酒店-ERP设置-授权管理-添加", notes = "ERP设置")
	@ApiImplicitParams({@ApiImplicitParam(name = "authorizations", value = "员工数组(形如: '[{hotelId: 1, shopId:1, accountId:1, functionId:1}, {hotelId: 1, shopId:1, accountId:2, functionId:1}]')", required = true, dataType = "String")})
	@SuppressWarnings("rawtypes")
	@PostMapping("/hotel/author")
	public ServerResponse hotelAuthorInsert(Integer hotelId, String authorizations, HttpSession session){
		boolean flag = false;
		try {
//			System.err.println(hotelId);
			List<TErpHotelAuthorization> authorizationList = JSON.parseArray(authorizations, TErpHotelAuthorization.class);
			for(TErpHotelAuthorization a : authorizationList){
				a.setCreator(getUser(session).getName());
				a.setCreateTime(new Date());
			}
			flag = tErpHotelAuthorizationService.insertBatch(authorizationList);	
		} catch (Exception e) {
			logger.error("backstage hotel author post error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess();
		else return ServerResponse.createByError();
	}
	
	@ApiOperation(value = "酒店-ERP设置-授权管理-重置授权&取消资格", notes = "ERP设置")
	@ApiImplicitParams({@ApiImplicitParam(name = "accountIds", value = "用户ID(数组)", required = true, dataType = "Integer[]")})
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/hotel/author")
	public ServerResponse hotelAuthorDel(Integer[] accountIds, HttpSession session){
		boolean flag = false;
		try {
//			for(int i : accountIds){
//				System.err.println(i);
//			}
			List<Integer> idList = Arrays.asList(accountIds);
			Wrapper<TErpHotelAuthorization> wrapper = new EntityWrapper<TErpHotelAuthorization>();
			wrapper.in("account_id", idList);
			flag = tErpHotelAuthorizationService.delete(wrapper);
		} catch (Exception e) {
			logger.error("backstage hotel author delete error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess();
		else return ServerResponse.createByError();
	}
	
	@ApiOperation(value = "酒店-ERP设置-授权管理-列表(未完)", notes = "ERP设置")
	@ApiImplicitParams({@ApiImplicitParam(name = "shopId", value = "门店ID", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "pageSize", value = "每页显示多少条数据", paramType = "query", required = false, dataType = "int", defaultValue = "10"),
		@ApiImplicitParam(name = "pageIndex", value = "当前页码", paramType = "query", required = false, dataType = "int", defaultValue = "1")})
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel/author")
	public ServerResponse hotelAuthorList(@RequestParam(name = "shopId", required = false) String shopId, 
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer pageIndex){
		boolean flag = false;
		// TODO 酒店-ERP设置-授权管理-列表(未完)
		Page<TErpHotelAuthorization> page = new Page<>(pageIndex, pageSize);
		try {
			Wrapper<TErpHotelAuthorization> wrapper = new EntityWrapper<>();
//			System.err.println(shopId);
			wrapper.eq(shopId != null, "shopId", shopId);
			page = tErpHotelAuthorizationService.selectPage(page, wrapper);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel hotel get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess(page);
		else return ServerResponse.createByError();
	}
	
	
}
