package com.gt.hotel.controller.back;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.TFood;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.param.HotelMobileParameter;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.param.InfrastructureRelationParamter;
import com.gt.hotel.vo.FileRecordVo;
import com.gt.hotel.vo.FoodVo;
import com.gt.hotel.vo.HotelSettingVo;
import com.gt.hotel.vo.InfrastructureVo;
import com.gt.hotel.vo.SysDictionaryVo;
import com.gt.hotel.web.service.SysDictionaryService;
import com.gt.hotel.web.service.TFoodService;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.THotelSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 酒店后台-移动端设置
 * 
 * @author Reverien9@gmail.com 2017年10月25日 上午11:54:18
 */
@Api(tags = "酒店后台-移动端设置")
@RestController
@RequestMapping("/back/mobile")
public class HotelMobileController extends BaseController {

	@Autowired
	THotelSettingService tHotelSettingService;

	@Autowired
	SysDictionaryService sysDictionaryService;

	@Autowired
	TFoodService tFoodService;

	@Autowired
	THotelService tHotelService;

	@ApiOperation(value = "查询 移动端设置 对象", notes = "查询 移动端设置 对象")
	@GetMapping(value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<HotelSettingVo> phoneSettingR(@PathVariable("hotelId") Integer hotelId) {
		HotelSettingVo setting = tHotelSettingService.queryHotelSettingOne(hotelId);
		return ResponseDTO.createBySuccess(setting);
	}

	@ApiOperation(value = "保存 移动端设置", notes = "保存 移动端设置")
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO phoneSettingCU(
			@Validated @ModelAttribute HotelMobileParameter.MobileSaveOrUpdate setting,
			@RequestBody List<InfrastructureRelationParamter> installations,
			BindingResult result,
			HttpSession session) {
//		System.err.println(imageurls);
		System.err.println(installations);
		InvalidParameter(result);
		Integer busid = getLoginUserId(session);
		tHotelSettingService.saveSetting(busid, setting);
		return ResponseDTO.createBySuccess();
	}

	////////////////////////////////////////// ↓酒店发票↓
	////////////////////////////////////////// ////////////////////////////////////////

	@ApiOperation(value = "查询 发票列表", notes = "查询 发票列表")
	@GetMapping(value = "invoice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<Page<SysDictionaryVo>> invoiceR(@Param("参数") @ModelAttribute HotelPage param) {
		Page<SysDictionaryVo> page = sysDictionaryService.queryDictionary(CommonConst.DICT_INVOICE, param);
		return ResponseDTO.createBySuccess(page);
	}

	////////////////////////////////////////// ↓酒店移动端设备↓
	////////////////////////////////////////// ////////////////////////////////////////

	@ApiOperation(value = "酒店移动端设备", notes = "酒店移动端设备")
	@GetMapping(value = "infrastructure", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<List<InfrastructureVo>> InfrastructureR() {
		List<InfrastructureVo> page = tHotelSettingService.queryHotelSettingInfrastructure();
		return ResponseDTO.createBySuccess(page);
	}

	//////////////////////////////////////////////////////// 客房订餐
	//////////////////////////////////////////////////////// ////////////////////////////////////////////////////////

	@ApiOperation(value = "保存 订餐设置", notes = "保存 订餐设置")
	@PostMapping(value = "{hotelId}/food", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO foodCU(@Validated @RequestBody @Param("参数") HotelMobileParameter.FoodSaveOrUpdate food,
			@PathVariable("hotelId") Integer hotelId, BindingResult result, HttpSession session) {
		InvalidParameter(result);
		Integer busid = getLoginUserId(session);
		Date date = new Date();
		TFood f = new TFood();
		BeanUtils.copyProperties(food, f);
		THotel th = tHotelService.selectById(f.getHotelId());
		if (f.getId() == null) {
			f.setCreatedAt(date);
			f.setCreatedBy(busid);
		}
		if (f.getFoodProvides().equals(0)) {
			f.setFoodProvidesName(th.getName());
		}
		f.setUpdatedAt(date);
		f.setUpdatedBy(busid);
		if (f.insertOrUpdate()) {
			return ResponseDTO.createBySuccess();
		} else {
			return ResponseDTO.createByError();
		}
	}

	@ApiOperation(value = "查询 订餐设置 列表", notes = "查询 订餐设置 列表")
	@GetMapping(value = "{hotelId}/food", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<Page<FoodVo>> foodR(@Param("参数") @ModelAttribute HotelPage hpage,
			@PathVariable("hotelId") Integer hotelId) {
		Page<FoodVo> page = tFoodService.queryFood(hpage, hotelId);
		return ResponseDTO.createBySuccess(page);
	}

	@ApiOperation(value = "查询 订餐设置 对象", notes = "查询 订餐设置 对象")
	@GetMapping(value = "{hotelId}/food/{foodId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<FoodVo> foodROne(@PathVariable("foodId") @ApiParam("订餐ID") Integer foodId,
			@PathVariable("hotelId") Integer hotelId) {
		TFood food = tFoodService.selectById(foodId);
		FoodVo iFood = new FoodVo();
		BeanUtils.copyProperties(food, iFood);
		return ResponseDTO.createBySuccess(iFood);
	}

	@ApiOperation(value = "删除 订餐设置", notes = "删除 订餐设置")
	@DeleteMapping(value = "{hotelId}/food", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO foodD(@RequestBody @ApiParam("订餐ID 数组") List<Integer> ids,
			@PathVariable("hotelId") Integer hotelId, HttpSession session) {
		Integer busid = getLoginUserId(session);
		TFood f = new TFood();
		Wrapper<TFood> wrapper = new EntityWrapper<>();
		f.setUpdatedAt(new Date());
		f.setUpdatedBy(busid);
		f.setMarkModified(CommonConst.DELETED);
		wrapper.in("id", ids);
		if (tFoodService.update(f, wrapper)) {
			return ResponseDTO.createBySuccess();
		} else {
			return ResponseDTO.createByError();
		}
	}

}
