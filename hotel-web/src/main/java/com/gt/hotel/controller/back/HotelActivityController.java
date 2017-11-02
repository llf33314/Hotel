package com.gt.hotel.controller.back;

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
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 酒店后台-活动设置
 *
 * @author Reverien9@gmail.com
 * 2017年10月25日 下午12:03:55
 */
@Api(tags = "酒店后台-活动设置")
@RestController
@RequestMapping("/back/activity")
public class HotelActivityController extends BaseController {

    @Autowired
    TActivityService tActivityService;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询 活动 列表", notes = "查询 活动 列表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<ActivityVo>> activityR(@Validated @Param("参数") @ModelAttribute ActivityParamter.ActivityQuery param, BindingResult bindingResult) {
    	ResponseDTO msg = InvalidParameterII(bindingResult);
        if(msg != null) {
        	return msg;
        }
        Page<ActivityVo> page = tActivityService.queryActivity(param);
        return ResponseDTO.createBySuccess(page);
    }

    @ApiOperation(value = "编辑 活动", notes = "编辑 活动")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO activityCU(@Validated @Param("参数") @RequestBody ActivityParamter.ActivitySaveOrUpdate arooms, BindingResult bindingResult, HttpSession session) {
    	ResponseDTO msg = InvalidParameterII(bindingResult);
        if(msg != null) {
        	return msg;
        }
        Integer busid = getLoginUserId(session);
        tActivityService.editActivity(busid, arooms);
        return ResponseDTO.createBySuccess();
    }

    @ApiOperation(value = "查询 活动 对象", notes = "查询 活动 对象")
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
        if (tActivityService.update(entity, wrapper))
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
        entity.setPublishStatus(CommonConst.ACTIVITY_STOP);
        entity.setUpdatedAt(new Date());
        entity.setUpdatedBy(getLoginUserId(session));
        wrapper.in("id", ids);
        if (tActivityService.update(entity, wrapper))
            return ResponseDTO.createBySuccess();
        else
            return ResponseDTO.createByError();
    }
    
    @ApiOperation(value = "开始 活动", notes = "开始 活动")
    @PostMapping(value = "open", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO activityOPEN(@RequestBody @ApiParam("活动ID 数组") List<Integer> ids, HttpSession session) {
    	Date date = new Date();
    	Wrapper<TActivity> wrapper = new EntityWrapper<>();
    	wrapper.in("id", ids);
    	List<TActivity> as = tActivityService.selectList(wrapper);
    	List<TActivity> asII = new ArrayList<>();
    	for(TActivity a :as) {
    		long begintime = a.getBeginTime().getTime();
    		long endtime = a.getEndTime().getTime();
    		if(date.getTime() < begintime) {
    			a.setPublishStatus(CommonConst.ACTIVITY_NOT_START);
    		}else if(date.getTime() > endtime) {
    			a.setPublishStatus(CommonConst.ACTIVITY_OVER);
    		}else if(date.getTime() > begintime && date.getTime() < endtime){
    			a.setPublishStatus(CommonConst.ACTIVITY_PROCESSING);
    		}
    		a.setUpdatedAt(date);
    		a.setUpdatedBy(getLoginUserId(session));
    		asII.add(a);
    	}
		if (tActivityService.updateAllColumnBatchById(asII)) {
    		return ResponseDTO.createBySuccess();
    	}else {
    		return ResponseDTO.createByError();
    	}
    }

}
