
package com.gt.hotel.controller.back;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.api.exception.SignException;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.HotelWsWxShopInfoExtend;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.requestEntity.HotelPage;
import com.gt.hotel.requestEntity.HotelParameter;
import com.gt.hotel.responseEntity.HotelList;
import com.gt.hotel.responseEntity.HotelShopInfo;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.web.serviceVO.HotelService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@Api( tags = "酒店管理相关接口" )
@RestController
@RequestMapping( "/back/hotel" )
public class HotelController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(HotelController.class);
	
	@Autowired
	private HotelService hotelService;
	
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
		@ApiResponse( code = 1, message = "酒店列表", response = HotelList.class )} )
	@PostMapping( value = "queryHotel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	@SuppressWarnings( "rawtypes" )
	public ResponseDTO hotelR(@RequestBody @ApiParam( value = "分页请求对象" ) HotelPage hpage, HttpSession session) {
		Page< HotelList > page = new Page<>(hpage.getPage(), hpage.getPageSize());
		Integer busid = getLoginUserId(session);
		page = hotelService.queryHotelHome(busid, page);
		return ResponseDTO.createBySuccess(page);
	}
	
	@ApiOperation( value = "新增或更新酒店", notes = "新增或更新酒店" )
	@ApiResponses( {@ApiResponse( code = 0, message = "", response = HotelParameter.SaveOrUpdate.class )} )
	@PostMapping( value = "insertHotel" )
	@SuppressWarnings( "rawtypes" )
	public ResponseDTO hotelCU(@Validated HotelParameter.SaveOrUpdate hotel, 
			HttpSession session, BindingResult bindingResult) {
		System.err.println("4444444444444444444444444444444444444444444444444444444444");
		System.err.println(bindingResult.hasErrors());
		InvalidParameter(bindingResult);
		Integer busid = getLoginUserId(session);
//		boolean i = hotelService.editHotel(busid, hotel);
//		if (i) return ResponseDTO.createBySuccess();
		/*else*/ return ResponseDTO.createByError();
	}
	
}