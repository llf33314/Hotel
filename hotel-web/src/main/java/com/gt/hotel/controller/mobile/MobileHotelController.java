package com.gt.hotel.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.param.RoomCategoryParameter;
import com.gt.hotel.vo.HotelSettingVo;
import com.gt.hotel.web.service.THotelSettingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 酒店移动端
 * 
 * @author Reverien9@gmail.com 2017年10月25日 下午12:04:18
 */
@Api(description = "酒店移动端")
@RestController
@RequestMapping("/mobile")
public class MobileHotelController extends BaseController {

	@Autowired
	THotelSettingService tHotelSettingService;

	@ApiOperation(value = "首页酒店信息", notes = "首页酒店信息")
	@GetMapping(value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<HotelSettingVo> moblieHotelR(@PathVariable("hotelId") Integer hotelId) {
		HotelSettingVo setting = tHotelSettingService.queryHotelSettingOne(hotelId);
		return ResponseDTO.createBySuccess(setting);
	}

	@ApiOperation(value = "首页房型列表", notes = "首页房型列表")
	@GetMapping(value = "{hotelId}/roomCategory", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<HotelSettingVo> mobileRoomCategoryR(@PathVariable("hotelId") Integer hotelId,
			@ModelAttribute RoomCategoryParameter.MobileQueryRoomCategory req) {
		
		return ResponseDTO.createBySuccess();
	}

}
