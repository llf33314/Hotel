package com.gt.hotel.controller.back;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.gt.hotel.entity.TActivity;
import com.gt.hotel.param.ActivityParamter;
import com.gt.hotel.vo.ActivityVo;
import com.gt.hotel.web.service.TActivityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 酒店后台-活动设置
 * @author Reverien9@gmail.com
 * 2017年10月25日 下午12:03:55
 */
@Api(tags = "酒店后台-活动设置")
@RestController
@RequestMapping("/back/activity")
public class HotelActivityController extends BaseController {
	
	@Autowired
	TActivityService tActivityService;
	
	@ApiOperation( value = "查询 活动 列表", notes = "查询 活动 列表" )
	@GetMapping( value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseDTO<Page<ActivityVo>> activityR(@Validated @Param("参数") @ModelAttribute ActivityParamter.ActivityQuery param, BindingResult result) {
		InvalidParameter(result);
		Page<ActivityVo> page = tActivityService.queryActivity(param);
		return ResponseDTO.createBySuccess(page);
	}
	
	@ApiOperation(value = "编辑 活动", notes = "编辑 活动")
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO activityCU(@Validated @Param("参数") @RequestBody ActivityParamter.ActivitySaveOrUpdate arooms, BindingResult bindingResult, HttpSession session) {
		InvalidParameter(bindingResult);;
		Integer busid = getLoginUserId(session);
		tActivityService.editActivity(busid, arooms);
		return ResponseDTO.createBySuccess();
	}
	
	@ApiOperation( value = "查询 活动 对象", notes = "查询 活动 对象" )
	@GetMapping( value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseDTO<ActivityVo> activityR(@PathVariable("id") @ApiParam("活动ID") Integer id) {
		ActivityVo a = tActivityService.queryActivityOne(id);
		return ResponseDTO.createBySuccess(a);
	}
	
	@ApiOperation(value = "删除 活动", notes = "删除 活动")
	@DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO activityD(@RequestBody @ApiParam("活动ID 数组") List<Integer> ids, HttpSession session) {
		Wrapper<TActivity> wrapper = new EntityWrapper<>();
		TActivity entity = new TActivity();
		entity.setMarkModified(CommonConst.DELETED);
		entity.setUpdatedAt(new Date());
		entity.setUpdatedBy(getLoginUserId(session));
		wrapper.in("id", ids);
		if(tActivityService.update(entity, wrapper))
			return ResponseDTO.createBySuccess();
		else
			return ResponseDTO.createByError();
	}
	
	@ApiOperation(value = "停止 活动", notes = "停止 活动")
	@PostMapping(value = "stop", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("rawtypes")
	public ResponseDTO activitySTOP(@RequestBody @ApiParam("活动ID 数组") List<Integer> ids, HttpSession session) {
		Wrapper<TActivity> wrapper = new EntityWrapper<>();
		TActivity entity = new TActivity();
		entity.setPublishStatus(CommonConst.STOP);
		entity.setUpdatedAt(new Date());
		entity.setUpdatedBy(getLoginUserId(session));
		wrapper.in("id", ids);
		if(tActivityService.update(entity, wrapper))
			return ResponseDTO.createBySuccess();
		else
			return ResponseDTO.createByError();
	}
	
}
