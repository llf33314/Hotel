package com.gt.hotel.controller.back;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.gt.hotel.util.ExcelUtil;
import com.gt.hotel.util.ExportUtil;
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
	
	////////////////////////////////////////////////////////////↓导出↓ //////////////////////////////////////////////////////////
	
	@ApiOperation(value = "房间订单导出", notes = "房间订单导出")
	@GetMapping(value = "roomExport", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void roomOrderExport(HotelOrderParameter.RoomOrderQuery param,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		OutputStream outputStream = null;
		HSSFWorkbook wb = null;
		try {
			Integer busid = getLoginUserId(session);
			List<HotelBackRoomOrderVo> page = tOrderService.queryRoomOrder(busid, param).getRecords();
			String[] titles = new String[]{"订单号", "酒店名称", "姓名", "手机号", "入住时间", "离店时间", "房间类型", "预订间数", 
					"门市价", "订单状态", "支付状态", "支付方式", "住客类型", "入住标准", "证件类型", "证件号码", "性别", "消费金额", 
					"优惠金额", "应收金额", "退还金额", "实收金额"};
			String[] contentName = new String[]{"orderNum", "hotelName", "customerName", "customerPhone", "roomInTime", "roomOutTime", 
					"categoryName", "number", "rackRate", "orderStatus", "payStatus", "payType", "guestType", "入住标准", "customerIdType", 
					"customerIdCard", "customerGender", "billPrice", "优惠金额", "receivablePrice", "退还金额", "realPrice"};
			wb = ExportUtil.getExcel("餐饮订单", titles, contentName, page, HotelBackFoodOrderVo.class, new ExcelUtil() {
				@Override
				public String fieldPprocessing(Object c, String contentName) {
					String s = "";
					if("orderStatus".equals(contentName)) {
						switch (Integer.valueOf(c.toString())) {
						case 0:s = "处理中";break;
						case 1:s = "已确认";break;
						case 2:s = "已取消";break;
						case 3:s = "已完成";break;
						}
					}
					if("payStatus".equals(contentName)) {
						switch (Integer.valueOf(c.toString())) {
						case 0:s = "待支付";break;
						case 1:s = "已支付";break;
						case 2:s = "退款中";break;
						case 3:s = "已退款";break;
						}
					}
					if("payType".equals(contentName)) {
						switch (Integer.valueOf(c.toString())) {
						case 1:s = "在线支付";break;
						case 2:s = "到店支付";break;
						case 3:s = "储值卡支付";break;
						case 4:s = "信用卡";break;
						case 5:s = "现金";break;
						}
					}
					if("customerIdType".equals(contentName)) {
						switch (Integer.valueOf(c.toString())) {
						case 0:s = "二代身份证";break;
						case 1:s = "一代身份证";break;
						case 2:s = "驾驶证";break;
						case 3:s = "护照";break;
						case 4:s = "军官证";break;
						case 5:s = "士兵证";break;
						case 6:s = "港澳通行证";break;
						case 7:s = "其他";break;
						}
					}
					if("customerGender".equals(contentName)) {
						switch (Integer.valueOf(c.toString())) {
						case 0:s = "男";break;
						case 1:s = "女";break;
						}
					}
					return s;
				}
			});
			response.setHeader("Content-Disposition", "attachment;filename=\"" + 
					URLEncoder.encode("餐饮订单"+new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())+
							".xls", "UTF-8") + "\"");  
			response.setContentType("application/vnd.ms-excel");
			outputStream = new BufferedOutputStream(response.getOutputStream());
			wb.write(outputStream);
			outputStream.flush();  
			outputStream.close();
			wb.close();
		} catch (Exception e) {
			logger.error("/back/order/foodExport error");
			e.printStackTrace();
		} finally {
			try {
				if(outputStream != null) outputStream.close();
				if(wb != null) wb.close();
			} catch (IOException e) {
				logger.error("/back/order/foodExport error");
				e.printStackTrace();
			}
		}
	}
	
	@ApiOperation(value = "餐饮订单导出", notes = "餐饮订单导出")
	@GetMapping(value = "foodExport", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void foodOrderExport(HotelOrderParameter.FoodOrderQuery param,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		
		OutputStream outputStream = null;
		HSSFWorkbook wb = null;
	    try {
	    	Integer busid = getLoginUserId(session);
	    	List<HotelBackFoodOrderVo> page = tOrderService.queryFoodOrder(busid, param).getRecords();
	        String[] titles = new String[]{"订单编号", "酒店名称", "预订人", "电话", "房号", "订单总额(元)", "下单时间", "菜品提供方", "订单状态", "支付状态", "支付方式"};
	        String[] contentName = new String[]{"orderNum", "hotelName", "customerName", "customerPhone", "roomNum", 
	        		"realPrice", "createTime", "foodProvidesName", "orderStatus", "payStatus", "payType"};
	        wb = ExportUtil.getExcel("餐饮订单", titles, contentName, page, HotelBackFoodOrderVo.class, new ExcelUtil() {
				@Override
				public String fieldPprocessing(Object c, String contentName) {
					String s = "";
					if("orderStatus".equals(contentName)) {
						switch (Integer.valueOf(c.toString())) {
						case 0:s = "处理中";break;
						case 1:s = "已确认";break;
						case 2:s = "已取消";break;
						case 3:s = "已完成";break;
						}
					}
					if("payStatus".equals(contentName)) {
						switch (Integer.valueOf(c.toString())) {
						case 0:s = "待支付";break;
						case 1:s = "已支付";break;
						case 2:s = "退款中";break;
						case 3:s = "已退款";break;
						}
					}
					if("payType".equals(contentName)) {
						switch (Integer.valueOf(c.toString())) {
						case 1:s = "在线支付";break;
						case 2:s = "到店支付";break;
						case 3:s = "储值卡支付";break;
						case 4:s = "信用卡";break;
						case 5:s = "现金";break;
						}
					}
					return s;
				}
			});
			response.setHeader("Content-Disposition", "attachment;filename=\"" + 
					URLEncoder.encode("餐饮订单"+new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())+
							".xls", "UTF-8") + "\"");  
			response.setContentType("application/vnd.ms-excel");
			outputStream = new BufferedOutputStream(response.getOutputStream());
			wb.write(outputStream);
			outputStream.flush();  
			outputStream.close();
			wb.close();
		} catch (Exception e) {
			logger.error("/back/order/foodExport error");
			e.printStackTrace();
		} finally {
			try {
				if(outputStream != null) outputStream.close();
				if(wb != null) wb.close();
			} catch (IOException e) {
				logger.error("/back/order/foodExport error");
				e.printStackTrace();
			}
		}
	}
	
}
