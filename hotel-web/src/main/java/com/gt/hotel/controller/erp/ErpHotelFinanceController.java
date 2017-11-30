package com.gt.hotel.controller.erp;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 酒店ERP - 财务
 * @author Reverien9@gmail.com
 * 2017年11月21日 上午10:52:29
 */
@Api(tags = "酒店ERP 财务")
@RestController
@RequestMapping("/erp/finance")
public class ErpHotelFinanceController extends BaseController {
	
	@Autowired
    private WXMPApiUtil WXMPApiUtil;
	
	@Autowired
    private WebServerConfigurationProperties properties;

    private static final Logger logger = LoggerFactory.getLogger(ErpHotelFinanceController.class);

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
                _s.setShopId(shop.getId());
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
