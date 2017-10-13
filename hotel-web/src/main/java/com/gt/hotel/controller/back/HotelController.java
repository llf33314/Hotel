package com.gt.hotel.controller.back;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.api.exception.SignException;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.HotelShopInfo;
import com.gt.hotel.other.HotelWsWxShopInfoExtend;
import com.gt.hotel.param.HotelParameter;
import com.gt.hotel.param.HotelParameter.Query;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.HotelVo;
import com.gt.hotel.web.service.THotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api( tags = "酒店管理相关接口" )
@RestController
@RequestMapping( "/back/hotel" )
public class HotelController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(HotelController.class);
	
	@Autowired
	private THotelService tHotelService;
	
	@Autowired
	private WXMPApiUtil WXMPApiUtil;
	
	@Value( "${wxmp.imageurl.prefixurl}" )
	private String IMAGE_PREFIX;
	
	@ApiOperation( value = "门店列表", notes = "门店列表" )
	@ApiResponses( {@ApiResponse( code = 0, message = "门店列表", response = HotelShopInfo.class )} )
	@GetMapping( value = "queryShop", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@SuppressWarnings( "rawtypes" )
	public ResponseDTO shopR(HttpSession session) {
		Integer busid = getLoginUserId(session);
		List< HotelWsWxShopInfoExtend > shops = null;
		try {
			shops = WXMPApiUtil.queryWxShopByBusId(busid);
			List< HotelShopInfo > s = new ArrayList<>();
			for (HotelWsWxShopInfoExtend shop : shops) {
				HotelShopInfo _s = new HotelShopInfo();
				_s.setShopid(shop.getId());
				_s.setName(shop.getBusinessName());
				_s.setTel(shop.getTelephone());
				_s.setAddr(shop.getAddress());
				_s.setImage(IMAGE_PREFIX + shop.getImageUrl());
				s.add(_s);
			}
			return ResponseDTO.createBySuccess(s);
		} catch (SignException e) {
			logger.error("签名错误：{}", e.getMessage());
			throw new ResponseEntityException(ResponseEnums.SIGNATURE_ERROR);
		}
	}
	
	@ApiOperation( value = "酒店列表", notes = "酒店列表" )
	@ApiResponses( {@ApiResponse( code = 0, message = "分页对象", response = ResponseDTO.class ), 
		@ApiResponse( code = 1, message = "酒店列表", response = HotelVo.class )} )
	@GetMapping( value = "queryHotel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@SuppressWarnings( "rawtypes" )
	public ResponseDTO hotelR(Query hpage, HttpSession session) {
		Page< HotelVo > page = new Page<>(hpage.getPage(), hpage.getPageSize());
		Integer busid = getLoginUserId(session);
		page = tHotelService.queryHotelHome(busid, hpage, page);
		return ResponseDTO.createBySuccess(page);
	}
	
	@ApiOperation( value = "新增或更新酒店", notes = "新增或更新酒店" )
	@ApiResponses( {@ApiResponse( code = 0, message = "", response = ResponseDTO.class )} )
	@PostMapping( value = "editHotel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@SuppressWarnings( "rawtypes" )
	public ResponseDTO hotelCU(@Validated @ModelAttribute HotelParameter.SaveOrUpdate hotel, BindingResult bindingResult, HttpSession session) {
		InvalidParameter(bindingResult);
		Integer busid = getLoginUserId(session);
		Date date = new Date();
		THotel e = new THotel();
		BeanUtils.copyProperties(hotel, e);
		e.setId(hotel.getHotelId());
		e.setPhone(hotel.getTel());
		e.setAddress(hotel.getAddr());
		e.setStoreId(hotel.getShopId());
		e.setCreatedBy(busid);
		e.setCreatedAt(date);
		e.setUpdatedBy(busid);
		e.setUpdatedAt(date);
		if (e.insertOrUpdate()) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	
}
