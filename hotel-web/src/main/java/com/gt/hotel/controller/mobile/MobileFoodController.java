package com.gt.hotel.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.param.FoodMobileParameter;
import com.gt.hotel.vo.FoodVo;
import com.gt.hotel.web.service.TFoodService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 酒店移动端 餐饮
 * @author Reverien9@gmail.com
 * 2017年11月3日 下午3:01:20
 */
@Api(description = "酒店移动端 餐饮")
@RestController
@RequestMapping("/mobile/78CDF1/food")
public class MobileFoodController extends BaseController {

    @Autowired
    TFoodService tFoodService;
    
    @ApiOperation(value = "菜品列表", notes = "菜品列表")
    @GetMapping(value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<FoodVo>> moblieHotelR(@PathVariable("hotelId") Integer hotelId, 
    		@ModelAttribute FoodMobileParameter.FoodMobileQuery query) {
    	query.setKeyword("%"+query.getKeyword()+"%");
    	Page<FoodVo> page = tFoodService.queryFood(query, hotelId);
        return ResponseDTO.createBySuccess(page);
    }

    

}
