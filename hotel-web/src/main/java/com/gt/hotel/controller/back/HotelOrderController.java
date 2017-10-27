package com.gt.hotel.controller.back;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.param.HotelOrderParameter;
import com.gt.hotel.vo.HotelBackRoomOrderVo;
import com.gt.hotel.web.service.TOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 酒店后台-订单管理
 *
 * @author Reverien9@gmail.com 2017年10月25日 下午12:04:02
 */
@Api(tags = "酒店后台-订单管理")
@RestController
@RequestMapping("/back/order")
public class HotelOrderController extends BaseController {

	@Autowired
	TOrderService tOrderService;

	@ApiOperation(value = "房间订单列表", notes = "房间订单列表")
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<Page<HotelBackRoomOrderVo>> roomOrderR(HotelOrderParameter.OrderQuery param,
			HttpSession session) {
		Integer busid = getLoginUserId(session);
		Page<HotelBackRoomOrderVo> page = tOrderService.queryRoomOrder(busid, param);
		return ResponseDTO.createBySuccess(page);
	}
	
	@ApiOperation(value = "删除 房间订单", notes = "删除 房间订单")
    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO roomCategoryD(@RequestBody @ApiParam("订单ID 数组") List<Integer> ids, HttpSession session) {
        Integer busid = getLoginUserId(session);
        Wrapper<TOrder> wrapper = new EntityWrapper<>();
        wrapper.in("id", ids);
        TOrder h = new TOrder();
        h.setMarkModified(CommonConst.DELETED);
        h.setUpdatedAt(new Date());
        h.setUpdatedBy(busid);
        tOrderService.update(h, wrapper);
        return ResponseDTO.createBySuccess();
    }

}
