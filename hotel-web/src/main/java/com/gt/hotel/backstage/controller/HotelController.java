package com.gt.hotel.backstage.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ServerResponse;
import com.gt.hotel.entity.TErpHotel;
import com.gt.hotel.entity.TErpHotelAuthorization;
import com.gt.hotel.entity.TErpHotelImage;
import com.gt.hotel.entity.TErpHotelMemberCheckOutRelation;
import com.gt.hotel.entity.TErpHotelMemberDepositRelation;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.web.service.TErpHotelService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/backstage")
public class HotelController extends BaseController{
	
	@Autowired
	TErpHotelService tErpHotelService;

	@ApiOperation(value = "酒店查询", notes = "酒店查询 参数ID")
	@ApiImplicitParams({@ApiImplicitParam(name = "pageSize", value = "每页显示多少条数据", paramType = "query", required = false, dataType = "int", defaultValue = "10"),
			@ApiImplicitParam(name = "pageIndex", value = "当前页码", paramType = "query", required = false, dataType = "int", defaultValue = "1"),
			@ApiImplicitParam(name = "id", value = "酒店ID", paramType = "query", required = true, dataType = "int", defaultValue = "0")})
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel")
	public ServerResponse queryHotel(@RequestParam(name = "id", required = false) String id, 
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer pageIndex){
		boolean flag = false;
		Page<TErpHotel> page = new Page<>(pageIndex, pageSize);
		try {
			Wrapper<TErpHotel> wrapper = new EntityWrapper<>();
			if(id != null) wrapper.eq("id", id);
			page = tErpHotelService.selectPage(page, wrapper);
		} catch (Exception e) {
			logger.error("backstage hotel get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess(page);
		else return ServerResponse.createByError();
	}
	
	@ApiOperation(value = "新增酒店-新增酒店", notes = "新增酒店")
	@ApiImplicitParams({@ApiImplicitParam(name = "shopId", value = "门店ID", required = true, dataType = "Integer", defaultValue = "null"), 
		@ApiImplicitParam(name = "name", value = "酒店名称", required = true, dataType = "String", defaultValue = "null"), 
		@ApiImplicitParam(name = "phone", value = "酒店电话", required = true, dataType = "String", defaultValue = "null"), 
		@ApiImplicitParam(name = "address", value = "酒店地址", required = true, dataType = "String", defaultValue = "null"), 
		@ApiImplicitParam(name = "introduction", value = "酒店介绍", required = true, dataType = "String", defaultValue = "null")})
	@SuppressWarnings("rawtypes")
	@PostMapping("/hotel")
//	public ServerResponse insertHotel(TErpHotel hotel){
	public ServerResponse insertHotel(Integer shopId, String name, String phone, String address, String introduction){
		boolean flag = false;
		try {
			if(shopId != null && 
					name != null && !name.isEmpty() && 
					phone != null && !phone.isEmpty() && 
					address != null && !address.isEmpty() && 
					introduction != null && !introduction.isEmpty()){
				TErpHotel hotel = new TErpHotel();
				hotel.setBusId(1);
				hotel.setShopId(shopId);
				hotel.setName(name);
				hotel.setPhone(phone);
				hotel.setAddress(address);
				hotel.setIntroduction(introduction);
				System.err.println(hotel);
				flag = tErpHotelService.insertOrUpdate(hotel);
			}
		} catch (Exception e) {
			logger.error("backstage hotel post error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess();
		else return ServerResponse.createByError();
	}
	
	@ApiOperation(value = "删除酒店", notes = "删除酒店")
	@ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "ID集合(数组)", paramType = "delete", required = false, dataType = "List")})
	@SuppressWarnings({ "rawtypes" })
	@DeleteMapping("/hotel")
	public ServerResponse deleteHotel(Integer[] ids){
		boolean flag = false;
		try {
			for(Integer i : ids)
				System.err.println(i);
			if(ids != null && ids.length > 0){
				List<Integer> idList = Arrays.asList(ids);
				flag = tErpHotelService.deleteBatchIds(idList);
			}
		} catch (Exception e) {
			logger.error("backstage hotel delete error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess();
		else return ServerResponse.createByError();
	}

	@ApiOperation(value = "酒店-ERP设置-ERP前台设置-保存", notes = "ERP设置")
	@ApiImplicitParams({@ApiImplicitParam(name = "hotelId", value = "酒店ID", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "logo", value = "酒店LOGO URL", required = true, dataType = "String"), 
		@ApiImplicitParam(name = "ifBreakfast", value = "是否开启早餐券", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "breakfastQuantity", value = "默认早餐券数量", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "ifFreeDeposit", value = "是否开启会员免押金", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "ifLastCheckOut", value = "是否开启会员最晚退房时间", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "deposits", value = "免押金数组(形如: '[{hotelId:1, vipLevel:1, ifFreeDeposit:1}, {hotelId:1, vipLevel:2, ifFreeDeposit:1}]')", required = true, dataType = "String"), 
		@ApiImplicitParam(name = "checkOuts", value = "最晚退房数组(形如: '[{hotelId:1, vipLevel:1, last_check_out:\"2017-01-01 01:01:01\"}, {hotelId:1, vipLevel:2, last_check_out:\"2017-01-01 01:01:01\"}]')", required = true, dataType = "String")})
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@PostMapping("/hotel/erpSet")
	public ServerResponse hotelErpSet(Integer hotelId, String logo, Integer ifBreakfast, Integer breakfastQuantity, Integer ifFreeDeposit, Integer ifLastCheckOut, 
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
			
			System.err.println(hotel);
			System.err.println(hotelImage);
			System.err.println(depositList);
			System.err.println(checkOutList);
			
			flag = tErpHotelService.hotelErpSet(hotel, hotelImage, depositList, checkOutList);	
			
		} catch (Exception e) {
			logger.error("backstage hotel erpset post error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess();
		else return ServerResponse.createByError();
	}
	
	@ApiOperation(value = "酒店-ERP设置-授权管理-添加", notes = "ERP设置")
	@ApiImplicitParams({@ApiImplicitParam(name = "hotelId", value = "酒店ID", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "authorizations", value = "员工数组(形如: '[{shopId:1, accountId:1, authorizationName:\"张三\"}, {shopId:1, accountId:2, authorizationName:\"李四\"}]')", required = true, dataType = "String")})
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@PostMapping("/hotel/author")
	public ServerResponse hotelAuthorInsert(String authorizations){
		boolean flag = false;
		try {
			List<TErpHotelAuthorization> authorizationList = JSON.parseArray(authorizations, TErpHotelAuthorization.class);
			Iterator<TErpHotelAuthorization> it = authorizationList.iterator();
			while (it.hasNext()) {
				TErpHotelAuthorization a = it.next();
				a.setCreator("me");
				a.setCreateTime(new Date());
			}
			for(TErpHotelAuthorization e : authorizationList){
				System.err.println(e);
			}
			
//			flag = tErpHotelService.insertHotelAuthor(authorizationList);	
			
		} catch (Exception e) {
			logger.error("backstage author post error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess();
		else return ServerResponse.createByError();
	}
	
	
}
