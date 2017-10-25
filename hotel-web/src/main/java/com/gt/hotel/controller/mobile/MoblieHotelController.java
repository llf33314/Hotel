package com.gt.hotel.controller.mobile;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.param.HotelParameter.HotelQuery;
import com.gt.hotel.vo.HotelVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "移动端")
@RestController
@RequestMapping("/mobile")
public class MoblieHotelController extends BaseController {
	
	@ApiOperation( value = "首页酒店信息", notes = "首页酒店信息" )
	@GetMapping( value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseDTO<Page<HotelVo>> hotelR(HotelQuery hpage, HttpSession session) {
		Integer busid = getLoginUserId(session);
		Page< HotelVo > page = null;
		return ResponseDTO.createBySuccess(page);
	}
	
}
