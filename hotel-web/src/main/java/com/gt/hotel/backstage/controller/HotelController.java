package com.gt.hotel.backstage.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.TErpHotel;
import com.gt.hotel.entity.TErpHotelAndImage;
import com.gt.hotel.entity.TErpHotelShop;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.web.service.TErpHotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description = "酒店后台-新增酒店")
@RestController
@RequestMapping("/backstage")
public class HotelController extends BaseController{
	
	@Autowired
	TErpHotelService tErpHotelService;
	
	@ApiOperation(value = "酒店后台-新增酒店-门店查询", notes = "")
	@ApiImplicitParams({@ApiImplicitParam(name = "pageSize", value = "每页显示多少条数据", paramType = "query", required = false, dataType = "Integer", defaultValue = "10"),
			@ApiImplicitParam(name = "pageIndex", value = "当前页码", paramType = "query", required = false, dataType = "Integer", defaultValue = "1")})
	@ApiResponses({@ApiResponse(code = 999, message = "", response = TErpHotelShop.class)})
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel/shop")
	public ResponseDTO queryShop(@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer pageIndex, HttpSession session){
		boolean flag = false;
		Page<TErpHotelShop> page = new Page<>(pageIndex, pageSize);
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("bus_id", getUser(session).getId());
			page = tErpHotelService.selectHotelShop(page, param);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess(page);
		else return ResponseDTO.createByError();
	}
	
//	@RequestMapping(value = "/hotel", method = RequestMethod.OPTIONS)
//	public void hotelOptions(){}
	@ApiOperation(value = "酒店后台-新增酒店-酒店查询", notes = "酒店查询 参数ID")
	@ApiImplicitParams({@ApiImplicitParam(name = "pageSize", value = "每页显示多少条数据", paramType = "query", required = false, dataType = "Integer", defaultValue = "10"),
			@ApiImplicitParam(name = "pageIndex", value = "当前页码", paramType = "query", required = false, dataType = "Integer", defaultValue = "1"),
			@ApiImplicitParam(name = "id", value = "酒店ID", paramType = "query", required = false, dataType = "Integer", defaultValue = "0"), 
			@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "query", required = false, dataType = "String", defaultValue = "")})
	@ApiResponses({@ApiResponse(code = 999, message = "", response = TErpHotelAndImage.class)})
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel")
	public ResponseDTO queryHotel(@RequestParam(name = "id", required = false) String id,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer pageIndex, 
			String keyword, HttpSession session){
		boolean flag = false;
		Page<TErpHotelAndImage> page = new Page<>(pageIndex, pageSize);
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("bus_id", getUser(session).getId());
			param.put("id", id);
			if(keyword != null) param.put("keyword", "%"+keyword+"%");
			page = tErpHotelService.selectHotelAndImagePage(page, param);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess(page);
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-新增酒店-添加酒店", notes = "新增酒店")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Integer", defaultValue = "null"), 
		@ApiImplicitParam(name = "shopId", value = "门店ID", required = true, dataType = "Integer", defaultValue = "null"), 
		@ApiImplicitParam(name = "name", value = "酒店名称", required = true, dataType = "String", defaultValue = "null"), 
		@ApiImplicitParam(name = "phone", value = "酒店电话", required = true, dataType = "String", defaultValue = "null"), 
		@ApiImplicitParam(name = "address", value = "酒店地址", required = true, dataType = "String", defaultValue = "null"), 
		@ApiImplicitParam(name = "longitude", value = "地址经度", required = true, dataType = "Double", defaultValue = "null"), 
		@ApiImplicitParam(name = "latitude", value = "地址纬度", required = true, dataType = "Double", defaultValue = "null"), 
		@ApiImplicitParam(name = "introduction", value = "酒店介绍", required = true, dataType = "String", defaultValue = "null")})
	@SuppressWarnings("rawtypes")
	@PostMapping("/hotel")
//	public ResponseDTO insertHotel(TErpHotel hotel){
	public ResponseDTO insertHotel(Integer id, Integer shopId, String name, String phone, String address, Double longitude, Double latitude, String introduction, HttpSession session){
		boolean flag = false;
		try {
			if(shopId != null && 
					name != null && !name.isEmpty() && 
					phone != null && !phone.isEmpty() && 
					address != null && !address.isEmpty() &&  
					introduction != null && !introduction.isEmpty()){
				TErpHotel hotel = new TErpHotel();
				hotel.setId(id);
				hotel.setBusId(getUser(session).getId());
				hotel.setShopId(shopId);
				hotel.setName(name);
				hotel.setPhone(phone);
				hotel.setAddress(address);
				hotel.setLongitude(longitude);
				hotel.setLatitude(latitude);
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
		if(flag) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-新增酒店-删除酒店", notes = "删除酒店")
	@ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "ID集合(数组)", paramType = "delete", required = false, dataType = "List")})
	@SuppressWarnings({ "rawtypes" })
	@DeleteMapping("/hotel")
	public ResponseDTO deleteHotel(Integer[] ids){
		boolean flag = false;
		try {
			for(Integer i : ids)
				System.err.println(i);
			if(ids != null && ids.length > 0){
				List<Integer> idList = Arrays.asList(ids);
//				flag = tErpHotelService.deleteBatchIds(idList);
				flag = tErpHotelService.deleteHotelBatchIds(idList);
			}
		} catch (Exception e) {
			logger.error("backstage hotel delete error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	


	@GetMapping("/home")
	public ModelAndView home(ModelAndView mav){
		mav.addObject("name", "ボタン");
		mav.addObject("data", tErpHotelService.selectList(null));
		mav.setViewName("nav");
		return mav;
	}
}
