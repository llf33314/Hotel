package com.gt.hotel.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.param.RoomCategoryParameter;
import com.gt.hotel.vo.MobileActivityRoomCategoryVo;
import com.gt.hotel.vo.MobileActivityVo;
import com.gt.hotel.vo.MobileHotelVo;
import com.gt.hotel.vo.MobileRoomCategoryVo;
import com.gt.hotel.web.service.TActivityService;
import com.gt.hotel.web.service.THotelSettingService;
import com.gt.hotel.web.service.TRoomCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 酒店移动端
 *
 * @author Reverien9@gmail.com 
 * 2017年10月25日 下午12:04:18
 */
@Api(description = "酒店移动端")
@RestController
@RequestMapping("/mobile")
public class MobileHotelController extends BaseController {

    @Autowired
    THotelSettingService tHotelSettingService;

    @Autowired
    TRoomCategoryService tRoomCategoryService;

    @Autowired
    TActivityService tActivityService;
    
    @ApiOperation(value = "首页酒店信息", notes = "首页酒店信息")
    @GetMapping(value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<MobileHotelVo> moblieHotelR(@PathVariable("hotelId") Integer hotelId) {
    	MobileHotelVo setting = tHotelSettingService.queryHotelSettingOne(hotelId);
        return ResponseDTO.createBySuccess(setting);
    }

    @ApiOperation(value = "首页房型列表", notes = "首页房型列表")
    @GetMapping(value = "{hotelId}/roomCategory", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<MobileRoomCategoryVo>> mobileRoomCategoryR(@PathVariable("hotelId") Integer hotelId, 
    		@RequestBody RoomCategoryParameter.MobileQueryRoomCategory req) {
    	Page<MobileRoomCategoryVo> page = tRoomCategoryService.queryMobileRoomCategory(hotelId, req);
        return ResponseDTO.createBySuccess(page);
    }
    
    @ApiOperation(value = "首页房型 活动 列表", notes = "首页房型 活动 列表")
    @GetMapping(value = "{hotelId}/activity", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<MobileActivityVo>> mobileActivityR(@PathVariable("hotelId") Integer hotelId) {
    	Page<MobileActivityVo> page = tActivityService.queryMobileActivity(hotelId);
    	return ResponseDTO.createBySuccess(page);
    }
    
    @ApiOperation(value = "首页房型 活动 房型 列表", notes = "首页房型 活动 房型 列表")
    @GetMapping(value = "{hotelId}/activity/{activityId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<MobileActivityRoomCategoryVo>> mobileActivityR(@PathVariable("hotelId") Integer hotelId, 
    		@PathVariable("activityId") Integer activityId, @ModelAttribute HotelPage hotelPage) {
    	Page<MobileActivityRoomCategoryVo> page = tActivityService.queryMobileActivityRoomCategoryList(hotelId, activityId, hotelPage);
    	return ResponseDTO.createBySuccess(page);
    }

}
