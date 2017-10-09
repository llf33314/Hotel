package com.gt.hotel.controller.back;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.TErpHotel;
import com.gt.hotel.entity.TErpHotelFood;
import com.gt.hotel.entity.TErpHotelFoodVO;
import com.gt.hotel.entity.TErpHotelImage;
import com.gt.hotel.entity.TErpHotelInvoiceRelation;
import com.gt.hotel.entity.TErpHotelMobileSet;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.web.service.TErpHotelFoodService;
import com.gt.hotel.web.service.TErpHotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description = "酒店后台-移动端设置")
@RestController
@RequestMapping("/backstage")
public class HotelMobileController extends BaseController{
	
	@Autowired
	TErpHotelService TErpHotelService;
	
	@Autowired
	TErpHotelFoodService TErpHotelFoodService;
	
	@ApiOperation(value = "酒店后台-移动端设置-手机页面设置", notes = "查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "hotelId", value = "酒店ID", paramType = "query", required = true, dataType = "Integer", defaultValue = "0")})
	@ApiResponses({@ApiResponse(code = 999, message = "", response = TErpHotelMobileSet.class)})
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel/mobile")
	public ResponseDTO hotelMobileQuery(@RequestParam(name = "hotelId", required = true) Integer hotelId){
		boolean flag = false;
		TErpHotelMobileSet hotel = new TErpHotelMobileSet();
		try {
			Wrapper<TErpHotel> wrapper = new EntityWrapper<TErpHotel>();
			wrapper.eq(hotelId != null, "id", hotelId);
			hotel = TErpHotelService.selectMobileOne(wrapper);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel mobile get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess(hotel);
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-移动端设置-手机页面设置", notes = "保存")
	@ApiImplicitParams({@ApiImplicitParam(name = "hotelId", value = "酒店ID", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "ifReserveMan", value = "是否收集预约人", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "ifReservePhone", value = "是否收集预约电话", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "ifRemark", value = "是否收集备注", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "payMode", value = "支付方式(1：在线支付 | 2：到店支付 | 3：1&2)", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "ifSms", value = "是否开启短信通知", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "smsPhone", value = "接受信息手机号", required = true, dataType = "String"),
		@ApiImplicitParam(name = "ifCheckOut", value = "是否开启一键退房", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "ifFood", value = "是否开启餐饮", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "ifFood", value = "是否开启餐饮", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "ifBulletin", value = "是否开启公告", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "ifGroupBuy", value = "是否开启团购", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "ifSpike", value = "是否开启秒杀房", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "ifHour", value = "是否开启钟点房", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "ifSpecial", value = "是否开启特价房", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "bulletin", value = "公告", required = true, dataType = "String"),
		@ApiImplicitParam(name = "ifRemnantRoom", value = "是否显示剩余房型", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "ifContinue", value = "是否开启一键续住", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "ifConfirmInfo", value = "是否确认订单信息功能", required = true, dataType = "Integer"),
		
		@ApiImplicitParam(name = "foodPayMode", value = "餐饮支付方式(1：在线支付 | 2：到店支付 | 3：1&2)", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "checkOutPhone", value = "退房接受信息手机号", dataType = "String"),
		@ApiImplicitParam(name = "ifInvoice", value = "是否开启预约发票", dataType = "Integer"),
		@ApiImplicitParam(name = "neekPrompt", value = "需发票退房成功提示", dataType = "String"),
		@ApiImplicitParam(name = "unneekPrompt", value = "无需发票退房成功提示", dataType = "String"),
		@ApiImplicitParam(name = "invoiceList", value = "发票类目数组(形如: '[{hotelId:1, invoiceId:1, invoiceName:'asdfghjkl'}, {hotelId:1, invoiceId:2, invoiceName:'zxcvbnm'}]')", required = false, dataType = "String", defaultValue = "null"), 
		
		@ApiImplicitParam(name = "imageList", value = "图片数组(形如: '[{name:'啊', url:'a.jpg'}, {name:'吧', url:'b.jpg'}]')", required = false, dataType = "String", defaultValue = "null"), 
		@ApiImplicitParam(name = "installationList", value = "基础设施ID数组(形如: '[1, 2, 3]')", required = false, dataType = "String")})
	@SuppressWarnings("rawtypes")
	@PostMapping("/hotel/mobile")
	public ResponseDTO hotelMobileCU(Integer hotelId, @ApiParam(hidden = true) TErpHotelMobileSet erpHotelMobileSet, 
			String imageList, String installationList, String invoiceList){
		boolean flag = false;
		try {
			erpHotelMobileSet.setId(hotelId);
			List<TErpHotelImage> images = JSON.parseArray(imageList, TErpHotelImage.class);
			List<Integer> idList = JSON.parseArray(installationList, Integer.class);
			List<TErpHotelInvoiceRelation> invoices = JSON.parseArray(invoiceList, TErpHotelInvoiceRelation.class);
			flag = TErpHotelService.mobileInfoUpdate(erpHotelMobileSet, images, idList, invoices);	
		} catch (Exception e) {
			logger.error("backstage hotel mobile post error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-移动端设置-客房订餐设置", notes = "查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "hotelId", value = "酒店ID", paramType = "query", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "id", value = "ID", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页显示多少条数据", paramType = "query", required = false, dataType = "int", defaultValue = "10"),
		@ApiImplicitParam(name = "pageIndex", value = "当前页码", paramType = "query", required = false, dataType = "int", defaultValue = "1"),
		@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "query", required = false, dataType = "String", defaultValue = "") })
	@ApiResponses({@ApiResponse(code = 999, message = "", response = TErpHotelFood.class)})
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel/food")
	public ResponseDTO hotelMobileFoodQuery(@RequestParam(name = "id", required = false) String id,
			@RequestParam(name = "hotelId", required = false) String hotelId,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer pageIndex, 
			String keyword){
		boolean flag = false;
		Page<TErpHotelFood> page = new Page<>(pageIndex, pageSize);
		try {
			Wrapper<TErpHotelFood> wrapper = new EntityWrapper<>();
			wrapper.eq(id != null, "id", id);
			wrapper.eq(hotelId != null, "hotel_id", hotelId);
			wrapper.like(keyword != null, "name", keyword);
			wrapper.like(keyword != null, "company_name", keyword);
			wrapper.like(keyword != null, "order_phone", keyword);
			page = TErpHotelFoodService.selectPage(page, wrapper);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel food get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess(page); 
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-移动端设置-客房订餐设置", notes = "编辑显示数据")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID", paramType = "query", required = false, dataType = "Integer")})
	@ApiResponses({@ApiResponse(code = 999, message = "", response = TErpHotelFoodVO.class)})
	@SuppressWarnings("rawtypes")
	@GetMapping("/hotel/food/edit")
	public ResponseDTO hotelMobileFoodQuery(@RequestParam(name = "id", required = false) Integer id){
		boolean flag = false;
		TErpHotelFoodVO food = new TErpHotelFoodVO();
		try {
			food = TErpHotelFoodService.selectPageFoodVO(id);
			flag = true;
		} catch (Exception e) {
			logger.error("backstage hotel food edit get error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess(food); 
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-移动端设置-客房订餐设置", notes = "新增 or 更新")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID(更新时需要)", paramType = "query", required = false, dataType = "Integer"), 
		@ApiImplicitParam(name = "hotelId", value = "酒店ID", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "name", value = "菜品名称", required = true, dataType = "String"), 
		@ApiImplicitParam(name = "breakfast", value = "早餐", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "lunch", value = "午餐", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "dinner", value = "晚餐", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "supper", value = "宵夜", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "provideFrom", value = "菜品提供方 1:本酒店 2:合作方", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "companyName", value = "合作方名称", required = false, dataType = "String"), 
		@ApiImplicitParam(name = "orderPhone", value = "新订单接受电话", required = false, dataType = "String"), 
		@ApiImplicitParam(name = "foodPrice", value = "菜品单价", required = true, dataType = "Integer"), 
		@ApiImplicitParam(name = "foodDeliverTime", value = "配送时间 分钟为单位", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "imageList", value = "图片数组(形如: '[{name:'啊', url:'a.jpg'}, {name:'吧', url:'b.jpg'}]')", required = false, dataType = "String", defaultValue = "null")}) 
	@SuppressWarnings("rawtypes")
	@PostMapping("/hotel/food")
	public ResponseDTO hotelMobileFoodIU(@ApiParam(hidden = true) TErpHotelFood food, String imageList, HttpSession session){
		boolean flag = false;
		try {
			food.setCreator(getUser(session).getName());
			food.setCreateTime(new Date());
			List<TErpHotelImage> images = JSON.parseArray(imageList, TErpHotelImage.class);
			flag = TErpHotelFoodService.insertOrUpdate(food, images);
		} catch (Exception e) {
			logger.error("backstage hotel food post error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "酒店后台-移动端设置-客房订餐设置", notes = "删除")
	@ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "ID集合(数组)", paramType = "delete", required = false, dataType = "List")})
	@SuppressWarnings({ "rawtypes" })
	@DeleteMapping("/hotel/food")
	public ResponseDTO hotelMobileFoodDel(Integer[] ids){
		boolean flag = false;
		try {
			for(Integer i : ids)
				System.err.println(i);
			if(ids != null && ids.length > 0){
				List<Integer> idList = Arrays.asList(ids);
				flag = TErpHotelFoodService.deleteBatchIdsANDImage(idList);
			}
		} catch (Exception e) {
			logger.error("backstage hotel food delete error",e);
			throw new ResponseEntityException(ResponseEnums.ERROR);
		}
		if(flag) return ResponseDTO.createBySuccess();
		else return ResponseDTO.createByError();
	}
	
}
