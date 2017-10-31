package com.gt.hotel.controller.back;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.param.HotelOrderParameter;
import com.gt.hotel.param.RoomCategoryParameter.QueryRoomCategoryOne;
import com.gt.hotel.vo.HotelBackFoodOrderVo;
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

	@ApiOperation(value = "删除 房间&餐饮订单(共用)", notes = "删除 房间&餐饮订单(共用)")
    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO orderD(@RequestBody @ApiParam("订单ID 数组") List<Integer> ids, HttpSession session) {
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

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "订单  确认 操作", notes = "订单  确认 操作")
	@PostMapping(value = "{orderId}/confirm", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO orderConfirm(@ApiParam("订单ID") @PathVariable("orderId") Integer orderId, HttpSession session) {
		Integer busid = getLoginUserId(session);
		TOrder order = tOrderService.selectById(orderId);
		if(!order.getOrderStatus().equals(0)) {
			return ResponseDTO.createByErrorMessage(ResponseEnums.ORDER_STATUS_ERROR.getMsg());
		}
		Wrapper<TOrder> wrapper = new EntityWrapper<>();
		wrapper.eq("id", orderId);
		TOrder newOrder = new TOrder();
		newOrder.setOrderStatus(CommonConst.ORDER_CONFIRMED);
		newOrder.setUpdatedBy(busid);
		newOrder.setUpdatedAt(new Date());
		if(!tOrderService.update(newOrder, wrapper)) {
			return ResponseDTO.createByErrorMessage(ResponseEnums.SAVE_ERROR.getMsg());
		}
		return ResponseDTO.createBySuccess();
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "订单  取消 操作", notes = "订单  取消 操作")
	@PostMapping(value = "{orderId}/cancel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO orderCancel(@ApiParam("订单ID") @PathVariable("orderId") Integer orderId, HttpSession session) {
		Integer busid = getLoginUserId(session);
		TOrder order = tOrderService.selectById(orderId);
		if(!order.getOrderStatus().equals(0)) {
			return ResponseDTO.createByErrorMessage(ResponseEnums.ORDER_STATUS_ERROR.getMsg());
		}
		Wrapper<TOrder> wrapper = new EntityWrapper<>();
		wrapper.eq("id", orderId);
		TOrder newOrder = new TOrder();
		newOrder.setOrderStatus(CommonConst.ORDER_CANCALLED);
		newOrder.setUpdatedBy(busid);
		newOrder.setUpdatedAt(new Date());
		if(!tOrderService.update(newOrder, wrapper)) {
			return ResponseDTO.createByErrorMessage(ResponseEnums.OPERATING_ERROR.getMsg());
		}
		return ResponseDTO.createBySuccess();
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "订单  完成 操作", notes = "订单  完成 操作")
	@PostMapping(value = "{orderId}/complete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO foodOrderComplete(@ApiParam("订单ID") @PathVariable("orderId") Integer orderId, HttpSession session) {
		Integer busid = getLoginUserId(session);
		TOrder order = tOrderService.selectById(orderId);
		if(!order.getOrderStatus().equals(1) || !order.getOrderStatus().equals(2)) {
			return ResponseDTO.createByErrorMessage(ResponseEnums.ORDER_STATUS_ERROR.getMsg());
		}
		Wrapper<TOrder> wrapper = new EntityWrapper<>();
		wrapper.eq("id", orderId);
		TOrder newOrder = new TOrder();
		newOrder.setOrderStatus(CommonConst.ORDER_CANCALLED);
		newOrder.setUpdatedBy(busid);
		newOrder.setUpdatedAt(new Date());
		if(!tOrderService.update(newOrder, wrapper)) {
			return ResponseDTO.createByErrorMessage(ResponseEnums.OPERATING_ERROR.getMsg());
		}
		return ResponseDTO.createBySuccess();
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "订单  退款 操作(占位)", notes = "订单  退款 操作(占位)")
	@PostMapping(value = "{orderId}/refunds", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO orderRefunds(@ApiParam("订单ID") @PathVariable("orderId") Integer orderId, HttpSession session) {
		Integer busid = getLoginUserId(session);
		TOrder order = tOrderService.selectById(orderId);
		if(!order.getPayStatus().equals(1)) {
			return ResponseDTO.createByErrorMessage(ResponseEnums.PAY_STATUS_ERROR.getMsg());
		}
		
		//TODO 退款
		
		//TODO 退款
		
		Wrapper<TOrder> wrapper = new EntityWrapper<>();
		wrapper.eq("id", orderId);
		TOrder newOrder = new TOrder();
		newOrder.setOrderStatus(CommonConst.PAY_STATUS_REFUNDS);
		newOrder.setUpdatedBy(busid);
		newOrder.setUpdatedAt(new Date());
		if(!tOrderService.update(newOrder, wrapper)) {
			return ResponseDTO.createByErrorMessage(ResponseEnums.OPERATING_ERROR.getMsg());
		}
		return ResponseDTO.createBySuccess();
	}
	
	////////////////////////////////////////////////////////////↓房间↓ //////////////////////////////////////////////////////////
	
	@ApiOperation(value = "房间订单列表", notes = "房间订单列表")
	@GetMapping(value = "room", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<Page<HotelBackRoomOrderVo>> roomOrderR(HotelOrderParameter.RoomOrderQuery param,
			HttpSession session) {
		Integer busid = getLoginUserId(session);
		Page<HotelBackRoomOrderVo> page = tOrderService.queryRoomOrder(busid, param);
		return ResponseDTO.createBySuccess(page);
	}
	
	@ApiOperation(value = "房间订单详情", notes = "房间订单详情")
	@GetMapping(value = "room/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<HotelBackRoomOrderVo> roomOrderOneR(@ApiParam("订单ID") @PathVariable("orderId") Integer orderId) {
		HotelBackRoomOrderVo order = tOrderService.queryRoomOrderOne(orderId);
		return ResponseDTO.createBySuccess(order);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "添加线下订单", notes = "添加线下订单")
	@PostMapping(value = "AddOffLineOrder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<QueryRoomCategoryOne> AddOffLineOrder(@Validated @RequestBody HotelOrderParameter.OffLineOrder order, 
			BindingResult bindingResult, HttpSession session) {
		ResponseDTO msg = InvalidParameterII(bindingResult);
        if(msg != null) {
        	return msg;
        }
		Integer busid = getLoginUserId(session);
		tOrderService.AddOffLineOrder(busid, order);
		return ResponseDTO.createBySuccess();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "房间订单入住", notes = "房间订单入住")
	@PostMapping(value = "{orderId}/checkIn", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<QueryRoomCategoryOne> checkIn(@PathVariable("orderId") Integer orderId, 
			@Validated @RequestBody HotelOrderParameter.CheckInParam param, 
			BindingResult bindingResult, HttpSession session) {
		ResponseDTO msg = InvalidParameterII(bindingResult);
		if(msg != null) {
			return msg;
		}
		Integer busid = getLoginUserId(session);
		tOrderService.checkIn(busid, orderId, param);
		return ResponseDTO.createBySuccess();
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "房间订单  结账退房 操作(占位)", notes = "房间订单  结账退房 操作(占位)")
	@PostMapping(value = "{orderId}/checkOut", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO checkOut(@ApiParam("订单ID") @PathVariable("orderId") Integer orderId, HttpSession session) {
		Integer busid = getLoginUserId(session);
		TOrder order = tOrderService.selectById(orderId);
		if(!order.getPayStatus().equals(3)) {
			return ResponseDTO.createByErrorMessage(ResponseEnums.PAY_STATUS_ERROR.getMsg());
		}
		
		//TODO 结账退房
		
		//TODO 结账退房
		
		Wrapper<TOrder> wrapper = new EntityWrapper<>();
		wrapper.eq("id", orderId);
		Date date = new Date();
		TOrder newOrder = new TOrder();
		newOrder.setOrderStatus(CommonConst.PAY_STATUS_REFUNDS);
		newOrder.setUpdatedBy(busid);
		newOrder.setUpdatedAt(date);
		if(!tOrderService.update(newOrder, wrapper)) {
			return ResponseDTO.createByErrorMessage(ResponseEnums.OPERATING_ERROR.getMsg());
		}
		return ResponseDTO.createBySuccess();
	}
	
	////////////////////////////////////////////////////////////↓餐饮↓ //////////////////////////////////////////////////////////
	
	@ApiOperation(value = "餐饮订单列表", notes = "餐饮订单列表")
	@GetMapping(value = "food", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<Page<HotelBackFoodOrderVo>> foodOrderR(HotelOrderParameter.FoodOrderQuery param,
			HttpSession session) {
		Integer busid = getLoginUserId(session);
		Page<HotelBackFoodOrderVo> page = tOrderService.queryFoodOrder(busid, param);
		return ResponseDTO.createBySuccess(page);
	}
	
	
	
}
