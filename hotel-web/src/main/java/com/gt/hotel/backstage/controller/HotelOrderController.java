package com.gt.hotel.backstage.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.TErpHotelFoodOrder;
import com.gt.hotel.entity.TErpHotelFoodOrderVO;
import com.gt.hotel.entity.TErpHotelRoomOrder;
import com.gt.hotel.entity.TErpHotelRoomOrderEx;
import com.gt.hotel.entity.TErpHotelRoomOrderGuest;
import com.gt.hotel.entity.TErpHotelRoomOrderVO;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.util.ExcelUtil;
import com.gt.hotel.util.ExportUtil;
import com.gt.hotel.web.service.TErpHotelFoodOrderService;
import com.gt.hotel.web.service.TErpHotelRoomOrderService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/backstage")
public class HotelOrderController extends BaseController{
	
	@Autowired
	TErpHotelRoomOrderService TErpHotelRoomOrderService;
	
	@Autowired
	TErpHotelFoodOrderService TErpHotelFoodOrderService;
	
	@ApiOperation(value = "酒店后台-订单管理-房间订单", notes = "查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "hotelId", value = "酒店ID", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "pageSize", value = "每页显示多少条数据", paramType = "query", required = false, dataType = "Integer", defaultValue = "10"),
		@ApiImplicitParam(name = "pageIndex", value = "当前页码", paramType = "query", required = false, dataType = "Integer", defaultValue = "1"), 
		@ApiImplicitParam(name = "orderStatus", value = "订单状态", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "payStatus", value = "支付状态", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "query", required = false, dataType = "String", defaultValue = "") })
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel/order/room")
	public ResponseDTO hotelOrderRoomR(@RequestParam(name = "hotelId", required = false) Integer hotelId, 
			@RequestParam(name = "id", required = false) String id,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer pageIndex, 
			String keyword, Integer orderStatus, Integer payStatus, HttpSession session){
		boolean flag = false;
		Page<TErpHotelRoomOrderVO> page = new Page<>(pageIndex, pageSize);
		try {
			Wrapper<TErpHotelRoomOrder> wrapper = new EntityWrapper<TErpHotelRoomOrder>();
			wrapper.eq("bus_id", getUser(session).getId());
			wrapper.eq(id != null, "id", id);
			wrapper.eq(hotelId != null, "hotel_id", hotelId);
			wrapper.eq(orderStatus != null, "order_status", orderStatus);
			wrapper.eq(payStatus != null, "pay_status", payStatus);
			Wrapper<TErpHotelRoomOrderGuest> wrapperg = new EntityWrapper<TErpHotelRoomOrderGuest>();
			wrapperg.like(keyword != null, "book_name", keyword);
			wrapperg.like(keyword != null, "book_phone", keyword);
			page = TErpHotelRoomOrderService.selectRoomOrderPage(page, wrapper, wrapperg);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel order room get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess(page);
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-订单管理-房间订单", notes = "删除")
	@ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "ID集合(数组)", paramType = "delete", required = false, dataType = "List")})
	@SuppressWarnings({ "rawtypes" })
	@DeleteMapping("/hotel/order/room")
	public ResponseDTO hotelOrderRoomD(Integer[] ids){
		boolean flag = false;
		try {
			if(ids != null && ids.length > 0){
				List<Integer> idList = Arrays.asList(ids);
				flag = TErpHotelRoomOrderService.deleteRoomOrder(idList);
			}
		} catch (Exception e) {
			logger.error("backstage hotel order room del error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-订单管理-房间订单", notes = "状态改变【订单状态 0:处理中 1:已确认 2:已取消 3:已入住(不需要) 4:已完成】")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID", paramType = "update", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "orderStatus", value = "需改变的状态", paramType = "update", required = true, dataType = "Integer")})
	@SuppressWarnings({ "rawtypes" })
	@PostMapping("/hotel/order/room")
	public ResponseDTO hotelOrderRoomU(@ApiParam(hidden = true) TErpHotelRoomOrder po){
		boolean flag = false;
		try {
			TErpHotelRoomOrder order = TErpHotelRoomOrderService.selectById(po.getId());
			Integer status = order.getOrderStatus();
			Integer newstatus = po.getOrderStatus();
			if( !(status == 0 && (newstatus == 1 || newstatus == 2)) ){
				//暂无操作
			}else if( !(status == 1 && newstatus == 3) ){
				//暂无操作
			}else if( !(status == 3 && newstatus == 4) ){
				//暂无操作
			}else if( status == 2 && newstatus == -1 ){
				//TODO 退款
			}else{
				TErpHotelRoomOrderService.updateStatus(po);
				flag = true;
			}
		} catch (Exception e) {
			logger.error("backstage hotel order room status change error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-订单管理-房间订单", notes = "导出")
	@ApiImplicitParams({@ApiImplicitParam(name = "hotelId", value = "酒店ID", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "orderStatus", value = "订单状态", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "payStatus", value = "支付状态", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "query", required = false, dataType = "String", defaultValue = "") })
	@GetMapping("/hotel/order/room/export")
	public void hotelOrderRoomE(@RequestParam(name = "hotelId", required = false) Integer hotelId, 
			@RequestParam(name = "id", required = false) String id,
			String keyword, Integer orderStatus, Integer payStatus, HttpSession session, HttpServletResponse response){
		List<TErpHotelRoomOrderEx> data = new ArrayList<TErpHotelRoomOrderEx>();
		OutputStream outputStream = null;
		HSSFWorkbook wb = null;
		try {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("bus_id", getUser(session).getId());
			p.put("hotelId", hotelId);
			p.put("orderStatus", orderStatus);
			p.put("payStatus", payStatus);
			if(keyword != null) p.put("keyword", "%"+keyword+"%");
			data = TErpHotelRoomOrderService.selectRoomOrderExport(p);
			String fileName = "房间订单";
			String[] titles = new String[]{"酒店名称", "预订人", "电话", "入店时间", "离店时间", "房间类型", "价格(元)", "数量", "订单状态", "支付状态", "支付方式"};
	        String[] contentName = new String[]{"name", "book_name", "book_phone", "check_in_time", "check_out_time", "check_in_standard", 
	        		"price", "quantity", "order_status", "pay_status", "pay_type"};
	        wb = ExportUtil.getExcel(fileName, titles, contentName, data, TErpHotelRoomOrderEx.class, new ExcelUtil() {
				@Override
				public String fieldPprocessing(Object value, String contentName) {
					String v = value.toString();
					if("check_in_standard".equals(contentName) ){
						v = "0".equals(v)?"全天房":("1".equals(v)?"钟点房":"长包房");
					}else if("order_status".equals(contentName) ){
						v = "0".equals(v)?"处理中":("1".equals(v)?"已确认":("2".equals(v)?"已取消":
							("3".equals(v)?"已入住":("4".equals(v)?"已完成":""))));
					}else if("pay_status".equals(contentName) ){
						v = "0".equals(v)?"未支付":("1".equals(v)?"已支付":("2".equals(v)?"挂账":"已退款"));
					}else if("pay_type".equals(contentName) ){
						v = "1".equals(v)?"在线支付":("2".equals(v)?"到店支付":
							("3".equals(v)?"储值卡支付":("4".equals(v)?"支付宝":("5".equals(v)?"银行卡":"现金"))));
					}
					return v;
				}
			});
			response.setHeader("Content-Disposition", "attachment;filename=\"" + 
					URLEncoder.encode(fileName+new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())+
							".xls", "UTF-8") + "\"");  
			response.setContentType("application/vnd.ms-excel");
			outputStream = new BufferedOutputStream(response.getOutputStream());
			wb.write(outputStream);
			outputStream.flush();  
			outputStream.close();
			wb.close();
		} catch (Exception e) {
			logger.error("backstage hotel order room export error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		} finally {
			try {
				if(outputStream != null) outputStream.close();
				if(wb != null) wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes" })
	@ApiOperation(value = "酒店后台-订单管理-客户订餐订单", notes = "查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "hotelId", value = "酒店ID", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "pageSize", value = "每页显示多少条数据", paramType = "query", required = false, dataType = "Integer", defaultValue = "10"),
		@ApiImplicitParam(name = "pageIndex", value = "当前页码", paramType = "query", required = false, dataType = "Integer", defaultValue = "1"), 
		@ApiImplicitParam(name = "provideFrom", value = "菜品提供方 1:本酒店 2:合作方", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "orderStatus", value = "订单状态", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "payStatus", value = "支付状态", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "query", required = false, dataType = "String", defaultValue = "") })
	@GetMapping("/hotel/order/food")
	public ResponseDTO hotelOrderFoodR(@RequestParam(name = "hotelId", required = false) Integer hotelId, 
			@RequestParam(name = "id", required = false) String id,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer pageIndex, 
			String keyword, Integer provideFrom, Integer orderStatus, 
			Integer payStatus, HttpSession session){
		boolean flag = false;
		Page<TErpHotelFoodOrderVO> page = new Page<TErpHotelFoodOrderVO>(pageIndex, pageSize);
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("bus_id", getUser(session).getId());
			param.put("id", id);
			param.put("hotel_id", hotelId);
			param.put("provide_from", provideFrom);
			param.put("order_status", orderStatus);
			param.put("pay_status", payStatus);
			if(keyword != null) param.put("keyword", "%"+keyword+"%");
			page = TErpHotelFoodOrderService.selectFoodOrderPage(page, param);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel order food get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess(page);
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-订单管理-客户订餐订单", notes = "导出")
	@ApiImplicitParams({@ApiImplicitParam(name = "hotelId", value = "酒店ID", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "provideFrom", value = "菜品提供方 1:本酒店 2:合作方", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "orderStatus", value = "订单状态", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "payStatus", value = "支付状态", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "query", required = false, dataType = "String", defaultValue = "") })
	@GetMapping("/hotel/order/food/export")
	public void hotelOrderFoodEx(@RequestParam(name = "hotelId", required = false) Integer hotelId, 
			String keyword, Integer provideFrom, Integer orderStatus, 
			Integer payStatus, HttpSession session, HttpServletResponse response){
		List<TErpHotelFoodOrderVO> data = new ArrayList<TErpHotelFoodOrderVO>();
		OutputStream outputStream = null;
		HSSFWorkbook wb = null;
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("bus_id", getUser(session).getId());
			param.put("hotel_id", hotelId);
			param.put("provide_from", provideFrom);
			param.put("order_status", orderStatus);
			param.put("pay_status", payStatus);
			if(keyword != null) param.put("keyword", "%"+keyword+"%");
			data = TErpHotelFoodOrderService.selectFoodOrderExport(param);
			String fileName = "客户订餐订单";
			String[] titles = new String[]{"酒店名称", "预订人", "电话", "房号", "价格(元)", "下单时间", "菜品提供方", "订单状态", "支付状态", "支付方式"};
	        String[] contentName = new String[]{"hotelName", "bookName", "bookPhone", "number", "price", "createTime", "companyName", "orderStatus", "payStatus", "payType"};
	        wb = ExportUtil.getExcel(fileName, titles, contentName, data, TErpHotelFoodOrderVO.class, new ExcelUtil() {
				@Override
				public String fieldPprocessing(Object value, String contentName) {
					String v = value.toString();
					if("orderStatus".equals(contentName) ){
						v = "0".equals(v)?"处理中":("1".equals(v)?"已确认":("2".equals(v)?"已取消":
							("3".equals(v)?"":("4".equals(v)?"已完成":""))));
					}else if("payStatus".equals(contentName) ){
						v = "0".equals(v)?"未支付":("1".equals(v)?"已支付":("2".equals(v)?"挂账":"已退款"));
					}else if("payType".equals(contentName) ){
						v = "1".equals(v)?"在线支付":("2".equals(v)?"到店支付":
							("3".equals(v)?"储值卡支付":("4".equals(v)?"支付宝":("5".equals(v)?"银行卡":"现金"))));
					}
					return v;
				}
			});
			response.setHeader("Content-Disposition", "attachment;filename=\"" + 
					URLEncoder.encode(fileName+new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())+
							".xls", "UTF-8") + "\"");  
			response.setContentType("application/vnd.ms-excel");
			outputStream = new BufferedOutputStream(response.getOutputStream());
			wb.write(outputStream);
			outputStream.flush();  
			outputStream.close();
			wb.close();
		} catch (Exception e) {
			logger.error("backstage hotel order food export error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		} finally {
			try {
				if(outputStream != null) outputStream.close();
				if(wb != null) wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@ApiOperation(value = "酒店后台-订单管理-客户订餐订单", notes = "删除")
	@ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "ID集合(数组)", paramType = "delete", required = false, dataType = "List")})
	@SuppressWarnings({ "rawtypes" })
	@DeleteMapping("/hotel/order/food")
	public ResponseDTO hotelMobileFoodDel(Integer[] ids){
		boolean flag = false;
		try {
			if(ids != null && ids.length > 0){
				List<Integer> idList = Arrays.asList(ids);
				flag = TErpHotelFoodOrderService.deleteFoodOrder(idList);
			}
		} catch (Exception e) {
			logger.error("backstage hotel order food del error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-订单管理-客户订餐订单", notes = "状态改变【订单状态 0:处理中 1:已确认 2:已取消 3:已入住(不需要) 4:已完成】")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID", paramType = "update", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "orderStatus", value = "需改变的状态", paramType = "update", required = true, dataType = "Integer")})
	@SuppressWarnings({ "rawtypes" })
	@PostMapping("/hotel/order/food")
	public ResponseDTO hotelOrderFoodU(@ApiParam(hidden = true) TErpHotelFoodOrder po){
		boolean flag = false;
		try {
			TErpHotelFoodOrder order = TErpHotelFoodOrderService.selectById(po.getId());
			Integer status = order.getOrderStatus();
			Integer newstatus = po.getOrderStatus();
			if( !(status == 0 && (newstatus == 1 || newstatus == 2)) ){
				//暂无操作
			}else if( !(status == 1 && newstatus == 4) ){
				//暂无操作
			}else if( status == 2 && newstatus == -1 ){
				//TODO 退款
			}else{
				TErpHotelFoodOrderService.updateStatus(po);
				flag = true;
			}
		} catch (Exception e) {
			logger.error("backstage hotel order room status change error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	
	
}
