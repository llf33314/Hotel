package com.gt.hotel.controller.back;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.TFood;
import com.gt.hotel.param.HotelMobileParameter;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.vo.FoodVo;
import com.gt.hotel.vo.HotelSettingVo;
import com.gt.hotel.vo.SysDictionaryVo;
import com.gt.hotel.web.service.SysDictionaryService;
import com.gt.hotel.web.service.TFoodService;
import com.gt.hotel.web.service.THotelSettingService;
import io.swagger.annotations.*;
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

    @ApiOperation(value = "查询 移动端设置 对象", notes = "查询 移动端设置 对象")
    @ApiResponses({
            @ApiResponse(code = 0, message = "响应对象", response = ResponseDTO.class),
            @ApiResponse(code = 1, message = "", response = HotelSettingVo.class)})
    @GetMapping(value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings({"rawtypes"})
    public ResponseDTO phoneSettingR(@PathVariable("hotelId") Integer hotelId) {
        HotelSettingVo setting = tHotelSettingService.queryHotelSettingOne(hotelId);
        return ResponseDTO.createBySuccess(setting);
    }

    @ApiOperation(value = "保存 移动端设置", notes = "保存 移动端设置")
    @ApiResponses({
            @ApiResponse(code = 0, message = "", response = ResponseDTO.class)})
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO phoneSettingCU(@Validated @ModelAttribute @Param("参数") HotelMobileParameter.SaveOrUpdate setting, BindingResult result, HttpSession session) {
        InvalidParameter(result);
        Integer busid = getLoginUserId(session);
        tHotelSettingService.saveSetting(busid, setting);
        return ResponseDTO.createBySuccess();
    }

    @ApiOperation(value = "查询 发票列表", notes = "查询 发票列表")
    @ApiResponses({
            @ApiResponse(code = 0, message = "响应对象", response = ResponseDTO.class),
            @ApiResponse(code = 1, message = "", response = SysDictionaryVo.class)})
    @GetMapping(value = "invoice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings({"rawtypes"})
    public ResponseDTO invoiceR(@ModelAttribute @Param("参数") HotelPage param) {
        Page<SysDictionaryVo> page = sysDictionaryService.queryDictionary(CommonConst.DICT_INVOICE, param);
        return ResponseDTO.createBySuccess(page);
    }

    //////////////////////////////////////////////////////// 客房订餐 ////////////////////////////////////////////////////////

    @ApiOperation(value = "保存 订餐设置", notes = "保存 订餐设置")
    @ApiResponses({@ApiResponse(code = 0, message = "", response = ResponseDTO.class)})
    @PostMapping(value = "food", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO foodCU(@Validated @ModelAttribute @Param("参数") HotelMobileParameter.FoodSaveOrUpdate food, BindingResult result, HttpSession session) {
        InvalidParameter(result);
        Integer busid = getLoginUserId(session);
        Date date = new Date();
        TFood f = new TFood();
        BeanUtils.copyProperties(food, f);
        if (f.getId() == null) {
            f.setCreatedAt(date);
            f.setCreatedBy(busid);
        }
        f.setUpdatedAt(date);
        f.setUpdatedBy(busid);
        if (f.insertOrUpdate()) return ResponseDTO.createBySuccess();
        else return ResponseDTO.createByError();
    }

    @ApiOperation(value = "查询 订餐设置 列表", notes = "查询 订餐设置 列表")
    @ApiResponses({
            @ApiResponse(code = 0, message = "响应对象", response = ResponseDTO.class),
            @ApiResponse(code = 1, message = "", response = FoodVo.class)})
    @GetMapping(value = "food", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings({"rawtypes"})
    public ResponseDTO foodR(@ModelAttribute @Param("参数") HotelPage hpage) {
        Page<FoodVo> page = tFoodService.queryFood(hpage);
        return ResponseDTO.createBySuccess(page);
    }

    @ApiOperation(value = "查询 订餐设置 对象", notes = "查询 订餐设置 对象")
    @ApiResponses({
            @ApiResponse(code = 0, message = "响应对象", response = ResponseDTO.class),
            @ApiResponse(code = 1, message = "", response = FoodVo.class)})
    @GetMapping(value = "food/{foodId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings({"rawtypes"})
    public ResponseDTO foodROne(@PathVariable("foodId") @ApiParam("订餐ID") Integer foodId) {
        TFood food = tFoodService.selectById(foodId);
        FoodVo _food = new FoodVo();
        BeanUtils.copyProperties(food, _food);
        return ResponseDTO.createBySuccess(food);
    }

    @ApiOperation(value = "删除 订餐设置", notes = "删除 订餐设置")
    @ApiResponses({
            @ApiResponse(code = 0, message = "", response = ResponseDTO.class)})
    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO foodD(@RequestBody @ApiParam("订餐ID 数组") List<Integer> ids, HttpSession session) {
        Integer busid = getLoginUserId(session);
        TFood f = new TFood();
        Wrapper<TFood> wrapper = new EntityWrapper<>();
        f.setUpdatedAt(new Date());
        f.setUpdatedBy(busid);
        f.setMarkModified(CommonConst.DELETED);
        wrapper.in("id", ids);
        if (tFoodService.update(f, wrapper)) return ResponseDTO.createBySuccess();
        else return ResponseDTO.createByError();
    }

}
