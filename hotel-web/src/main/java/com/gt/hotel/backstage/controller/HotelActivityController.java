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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ServerResponse;
import com.gt.hotel.entity.TErpHotel;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.web.service.TErpHotelService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/backstage")
public class HotelActivityController extends BaseController{
	
	@Autowired
	TErpHotelService tErpHotelService;
	
	@ApiOperation(value = "新增酒店-酒店查询", notes = "酒店查询 参数ID")
	@ApiImplicitParams({@ApiImplicitParam(name = "pageSize", value = "每页显示多少条数据", paramType = "query", required = false, dataType = "int", defaultValue = "10"),
			@ApiImplicitParam(name = "pageIndex", value = "当前页码", paramType = "query", required = false, dataType = "int", defaultValue = "1"),
			@ApiImplicitParam(name = "id", value = "酒店ID", paramType = "query", required = false, dataType = "int", defaultValue = "0"), 
			@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "query", required = false, dataType = "String", defaultValue = "")})
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel")
	public ServerResponse queryHotel(@RequestParam(name = "id", required = false) String id, 
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer pageIndex, 
			String keyword){
		boolean flag = false;
		Page<TErpHotel> page = new Page<>(pageIndex, pageSize);
		try {
			Wrapper<TErpHotel> wrapper = new EntityWrapper<>();
			wrapper.eq(id != null, "id", id);
			wrapper.like(keyword != null, "name", keyword);
			wrapper.like(keyword != null, "phone", keyword);
			wrapper.like(keyword != null, "address", keyword);
			page = tErpHotelService.selectPage(page, wrapper);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess(page);
		else return ServerResponse.createByError();
	}
	
	@ApiOperation(value = "新增酒店-添加酒店", notes = "新增酒店")
	@ApiImplicitParams({@ApiImplicitParam(name = "shopId", value = "门店ID", required = true, dataType = "Integer", defaultValue = "null"), 
		@ApiImplicitParam(name = "name", value = "酒店名称", required = true, dataType = "String", defaultValue = "null"), 
		@ApiImplicitParam(name = "phone", value = "酒店电话", required = true, dataType = "String", defaultValue = "null"), 
		@ApiImplicitParam(name = "address", value = "酒店地址", required = true, dataType = "String", defaultValue = "null"), 
		@ApiImplicitParam(name = "introduction", value = "酒店介绍", required = true, dataType = "String", defaultValue = "null")})
	@SuppressWarnings("rawtypes")
	@PostMapping("/hotel")
//	public ServerResponse insertHotel(TErpHotel hotel){
	public ServerResponse insertHotel(Integer shopId, String name, String phone, String address, String introduction, HttpSession session){
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
				hotel.setCreator(getUser(session).getName());
				hotel.setCreateTime(new Date());
//				System.err.println(hotel);
				flag = tErpHotelService.insertOrUpdate(hotel);
			}
		} catch (Exception e) {
			logger.error("backstage hotel post error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ServerResponse.createBySuccess();
		else return ServerResponse.createByError();
	}
	
	@ApiOperation(value = "新增酒店-删除酒店(未完)", notes = "删除酒店")
	@ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "ID集合(数组)", paramType = "delete", required = false, dataType = "List")})
	@SuppressWarnings({ "rawtypes" })
	@DeleteMapping("/hotel")
	public ServerResponse deleteHotel(Integer[] ids){
		// TODO 新增酒店-删除酒店(未完)
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

}
