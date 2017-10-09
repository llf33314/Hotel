package com.gt.hotel.backstage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.HotelWsWxShopInfoExtend;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.requestEntity.HotelPage;
import com.gt.hotel.responseEntity.HotelList;
import com.gt.hotel.responseEntity.HotelShopInfo;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.web.serviceVO.HotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description = "酒店后台-新增酒店")
@RestController
@RequestMapping("/backstage/hotel")
public class HotelController extends BaseController{
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private WXMPApiUtil WXMPApiUtil;
	
	@ApiOperation(value = "第一页门店列表", notes = "第一页门店列表")
	@ApiResponses({@ApiResponse(code = 0, message = "门店列表", response = HotelShopInfo.class)})
	@PostMapping("queryShop")
	@SuppressWarnings("rawtypes")
	public ResponseDTO shopR(HttpSession session) {
		try {
			Integer busid = getUser(session).getId();
			List<HotelWsWxShopInfoExtend> shops = WXMPApiUtil.queryWxShopByBusId(busid);
			List<HotelShopInfo> s = new ArrayList<>();
			for(HotelWsWxShopInfoExtend shop: shops){
				HotelShopInfo _s = new HotelShopInfo();
				_s.setShopid(shop.getId());
				_s.setName(shop.getBusinessName());
				_s.setTel(shop.getTelephone());
				_s.setAddr(shop.getAddress());
				_s.setImage(shop.getImageUrl());
				s.add(_s);
			}
			return ResponseDTO.createBySuccess(s);
		} catch (Exception e) {
			logger.error("backstage hotel queryShop error", e);
		    throw new ResponseEntityException(ResponseEnums.ERROR);
		}
	}
	
	@ApiOperation(value = "酒店列表", notes = "酒店列表")
	@ApiResponses({@ApiResponse(code = 0, message = "分页对象", response = ResponseDTO.class),
		@ApiResponse(code = 1, message = "酒店列表", response = HotelList.class)})
	@PostMapping("queryHotel")
	@SuppressWarnings("rawtypes")
	public ResponseDTO hotelR(@RequestBody @ApiParam(value = "分页请求对象") HotelPage hpage, HttpSession session) {
		Page<HotelList> page = new Page<>(hpage.getPage(), hpage.getPageSize());
		try {
			Integer busid = getUser(session).getId();
			page = hotelService.queryHotelHome(busid, page);
			return ResponseDTO.createBySuccess(page);
		} catch (Exception e) {
			logger.error("backstage hotel queryHotel error", e);
		    throw new ResponseEntityException(ResponseEnums.ERROR);
		}
	}
	
	
	
	
	
}
