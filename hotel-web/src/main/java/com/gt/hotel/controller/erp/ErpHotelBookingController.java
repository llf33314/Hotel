package com.gt.hotel.controller.erp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gt.api.exception.SignException;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.HotelShopInfo;
import com.gt.hotel.other.HotelWsWxShopInfoExtend;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.WXMPApiUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 酒店ERP - 预订
 * @author Reverien9@gmail.com
 * 2017年11月21日 上午10:51:58
 */
@Api(tags = "酒店ERP - 预订")
@RestController
@RequestMapping("/erp/booking")
public class ErpHotelBookingController extends BaseController {
	
	@Autowired
    private WXMPApiUtil WXMPApiUtil;
	
	@Autowired
    private WebServerConfigurationProperties properties;

    private static final Logger logger = LoggerFactory.getLogger(ErpHotelBookingController.class);

    @ApiOperation(value = "门店列表", notes = "门店列表")
    @GetMapping(value = "queryShop", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<List<HotelShopInfo>> shopR(HttpServletRequest request) {
        Integer busid = getLoginUser(request).getId();
        List<HotelWsWxShopInfoExtend> shops = null;
        try {
            JSONObject json = WXMPApiUtil.queryWxShopByBusId(busid);
            if (json.getBoolean("success")) {
                shops = JSONArray.parseArray(json.getJSONArray("data").toJSONString(),
                        HotelWsWxShopInfoExtend.class);
            }
            List<HotelShopInfo> s = new ArrayList<>();
            for (HotelWsWxShopInfoExtend shop : shops) {
                HotelShopInfo _s = new HotelShopInfo();
                _s.setShopid(shop.getId());
                _s.setName(shop.getBusinessName());
                _s.setTel(shop.getTelephone());
                _s.setAddr(shop.getAddress());
                _s.setImage(properties.getWxmpService().getImageUrl() + shop.getImageUrl());
                s.add(_s);
            }
            return ResponseDTO.createBySuccess(s);
        } catch (SignException e) {
            logger.error("签名错误：{}", e.getMessage());
            throw new ResponseEntityException(ResponseEnums.SIGNATURE_ERROR);
        }
    }
    
}
