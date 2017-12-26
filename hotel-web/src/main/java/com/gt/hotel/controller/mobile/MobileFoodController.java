package com.gt.hotel.controller.mobile;

import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.api.bean.session.Member;
import com.gt.api.util.KeysUtil;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.param.FoodMobileParameter;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.vo.FoodSettleVo;
import com.gt.hotel.vo.FoodVo;
import com.gt.hotel.web.service.TFoodService;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.TOrderFoodService;
import com.gt.hotel.web.service.TOrderService;

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
    
    @Autowired
    TOrderService tOrderService;
    
    @Autowired
    TOrderFoodService tOrderFoodService;
    
    @Autowired
    WebServerConfigurationProperties properties;
    
    @ApiOperation(value = "菜品列表", notes = "菜品列表")
    @GetMapping(value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<FoodVo>> moblieHotelFoodR(@PathVariable("hotelId") Integer hotelId, 
    		@ModelAttribute FoodMobileParameter.FoodMobileQuery query) {
    	if(!StringUtils.isEmpty(query.getKeyword())) {
    		query.setKeyword("%"+query.getKeyword()+"%");
    	}
    	Page<FoodVo> page = tFoodService.queryFoodNoPage(query, hotelId);
        return ResponseDTO.createBySuccess(page);
    }

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "去结算", notes = "去结算")
	@PostMapping(value = "{hotelId}/settlement", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO moblieHotelFoodSettlement(@PathVariable("hotelId") Integer hotelId, 
			@Validated @RequestBody @Param("参数") FoodMobileParameter.FoodMobileOrder order, 
			BindingResult bindingResult, HttpServletRequest request) {
    	invalidParameter(bindingResult);
    	Member member = getMember(request);
    	order.setHotelId(hotelId);
		return ResponseDTO.createBySuccess(tOrderFoodService.mobileFoodOrderBook(member, order));
	}
	
	@ApiOperation(value = "支付订单详情", notes = "支付订单详情")
    @GetMapping(value = "{hotelId}/order/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<FoodSettleVo> moblieHotelFoodOrderR(@PathVariable("hotelId") Integer hotelId,
    		@PathVariable("orderId") Integer orderId, HttpServletRequest request) {
		Member member = getMember(request);
        return ResponseDTO.createBySuccess(tOrderFoodService.queryFoodOrderOne(hotelId, orderId, member));
    }
    
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "支付", notes = "支付")
    @PostMapping(value = "{hotelId}/pay", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO moblieHotelFoodPay(@PathVariable("hotelId") Integer hotelId, 
    		@Validated @RequestBody @Param("参数") FoodMobileParameter.FoodMobileBookOrder order, 
    		BindingResult bindingResult, HttpServletRequest request) {
    	ResponseDTO msg = invalidParameterII(bindingResult);
    	if(msg != null) {
    		return msg;
    	}
    	Member member = getMember(request);
    	order.setHotelId(hotelId);
    	tOrderFoodService.mobileFoodOrderBookPay(member, order);
    	return ResponseDTO.createBySuccess(order.getOrderId());
    }
    
    @ApiOperation(value = "支付跳转(支付接口成功后调用此接口)", notes = "支付跳转(支付接口成功后调用此接口)")
    @GetMapping(value = "{hotelId}/payJump/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView moblieHotelFoodPay(@PathVariable("hotelId") Integer hotelId,  
    		@PathVariable("orderId") Integer orderId,
    		HttpServletRequest request, ModelAndView modelAndView) {
    	TOrder tOrder = tOrderService.selectById(orderId);
    	JSONObject SubQrPayParams = new JSONObject();
    	SubQrPayParams.put("totalFee", tOrder.getRealPrice() / 100d);
    	SubQrPayParams.put("model", CommonConst.PAY_MODEL_FOOD);
    	SubQrPayParams.put("busId", tOrder.getBusId());
    	SubQrPayParams.put("appidType", 0);
    	SubQrPayParams.put("orderNum", tOrder.getOrderNum());
    	SubQrPayParams.put("desc", "酒店订餐");
    	SubQrPayParams.put("isreturn", 0);
    	SubQrPayParams.put("notifyUrl", getHost(request)+"/mobile/78CDF1/food/"+hotelId+"/notifyUrl/"+orderId);
    	SubQrPayParams.put("isSendMessage", 0);
//    	SubQrPayParams.put("sendUrl", "");
    	SubQrPayParams.put("payWay", 0);
    	SubQrPayParams.put("sourceType", 1);
		try {
			String obj;
			obj = KeysUtil.getEncString(SubQrPayParams.toJSONString());
			modelAndView.setViewName("redirect:" + properties.getWxmpService().getApiMap().get("payapi").toString()+obj);
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.setViewName("/error");
		}
    	return modelAndView;
    }
    
    @ApiOperation(value = "支付异步回调", notes = "支付异步回调", hidden = true)
    @PostMapping(value = "{hotelId}/notifyUrl/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject moblieHotelFoodPayNotifyUrl(@PathVariable("hotelId") Integer hotelId,
    		@PathVariable("orderId") Integer orderId, Map<String, Object> param,
    		HttpServletRequest request) {
    	JSONObject json = new JSONObject();
		json.put("code", -1);
		json.put("msg", "支付失败");
		json = tOrderFoodService.moblieHotelFoodPayNotifyUrl(param, orderId);
		return json;
    }
}
