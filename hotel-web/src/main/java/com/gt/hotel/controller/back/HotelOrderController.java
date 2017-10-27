package com.gt.hotel.controller.back;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.param.HotelOrderParameter;
import com.gt.hotel.vo.HotelBackRoomOrderVo;
import com.gt.hotel.web.service.TOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 酒店后台-订单管理
 *
 * @author Reverien9@gmail.com
 * 2017年10月25日 下午12:04:02
 */
@Api(tags = "酒店后台-订单管理")
@RestController
@RequestMapping("/back/order")
public class HotelOrderController extends BaseController {
	
	@Autowired
	TOrderService tOrderService;
	
	 @ApiOperation(value = "房间订单列表", notes = "房间订单列表")
	    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	    public ResponseDTO<Page<HotelBackRoomOrderVo>> roomOrderR(HotelOrderParameter.OrderQuery param, HttpSession session) {
	        Integer busid = getLoginUserId(session);
	        Page<HotelBackRoomOrderVo> page = tOrderService.queryRoomOrder(busid, param);
	        return ResponseDTO.createBySuccess();
	    }
	
}
