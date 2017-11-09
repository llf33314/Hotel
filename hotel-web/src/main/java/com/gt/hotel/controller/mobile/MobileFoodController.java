package com.gt.hotel.controller.mobile;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.api.bean.session.Member;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.param.FoodMobileParameter;
import com.gt.hotel.vo.FoodVo;
import com.gt.hotel.web.service.TFoodService;
import com.gt.hotel.web.service.THotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 酒店移动端 餐饮
 * @author Reverien9@gmail.com
 * 2017年11月3日 下午3:01:20
 */
@Api(tags = "酒店移动端 餐饮")
@RestController
@RequestMapping("/mobile/78CDF1/food")
public class MobileFoodController extends BaseController {

    @Autowired
    TFoodService tFoodService;
    
    @Autowired
    THotelService tHotelService;
    
    @ApiOperation(value = "菜品列表", notes = "菜品列表")
    @GetMapping(value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<FoodVo>> moblieHotelR(@PathVariable("hotelId") Integer hotelId, 
    		@ModelAttribute FoodMobileParameter.FoodMobileQuery query) {
    	if(!StringUtils.isEmpty(query.getKeyword())) {
    		query.setKeyword("%"+query.getKeyword()+"%");
    	}
    	Page<FoodVo> page = tFoodService.queryFood(query, hotelId);
        return ResponseDTO.createBySuccess(page);
    }

    @SuppressWarnings("rawtypes")
	@ApiOperation(value = "去结算", notes = "去结算")
	@PostMapping(value = "{hotelId}/settlement", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO phoneSettingCU(@PathVariable("hotelId") Integer hotelId, 
			@Validated @RequestBody @Param("参数") FoodMobileParameter.FoodMobileOrder order, 
			BindingResult bindingResult, HttpServletRequest request) {
    	THotel hotel = tHotelService.selectById(hotelId);
//    	Member member = SessionUtils.getLoginMember(request, hotel.getBusId());
    	//test
    	Member member = new Member();
    	member.setId(1071);
    	member.setBusid(33);
    	member.setPhone("13433550667");
    	member.setPublicId(492);
    	member.setCardid("15338");
    	//test
		ResponseDTO msg = InvalidParameterII(bindingResult);
        if(msg != null) {
        	return msg;
        }
        
		return ResponseDTO.createBySuccess();
	}

}
