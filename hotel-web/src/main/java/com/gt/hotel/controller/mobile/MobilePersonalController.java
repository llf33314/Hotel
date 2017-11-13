package com.gt.hotel.controller.mobile;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 酒店移动端 我的
 * @author Reverien9@gmail.com
 * 2017年11月10日 下午3:54:20
 */
@Api(tags = "酒店移动端 我的")
@RestController
@RequestMapping("/mobile/78CDF1/my")
public class MobilePersonalController extends BaseController {

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "个人中心 会员信息", notes = "个人中心 会员信息")
    @GetMapping(value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO moblieHotelFoodR(@PathVariable("hotelId") Integer hotelId, HttpServletRequest request) {
    	
        return ResponseDTO.createBySuccess();
    }
    
	
}
