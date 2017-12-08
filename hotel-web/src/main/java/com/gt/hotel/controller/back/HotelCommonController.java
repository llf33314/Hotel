package com.gt.hotel.controller.back;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.base.Optional;
import com.gt.api.exception.SignException;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.HotelInfoVo;
import com.gt.hotel.other.HotelShopInfo;
import com.gt.hotel.other.HotelWsWxShopInfoExtend;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.web.service.THotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 公共API信息
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/11/28
 * @since API信息
 */
@Slf4j
@Api(tags = "公共API接口信息")
@RequestMapping("/back/common/")
@RestController
public class HotelCommonController extends BaseController {

    @Autowired
    private WebServerConfigurationProperties properties;

    @Autowired
    private THotelService tHotelService;

    @Autowired
    private WXMPApiUtil wxmpApiUtil;


    /**
     * 根据商家ID 获取门店列表
     * 无分页
     *
     * @param request HttpServletRequest
     * @return ResponseDTO<List<HotelShopInfo>>
     */
    @ApiOperation(value = "门店列表", notes = "门店列表")
    @GetMapping(value = "shops", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<List<HotelShopInfo>> shops(HttpServletRequest request) {
        Integer busId = getLoginUser(request).getId();
        List<HotelWsWxShopInfoExtend> shops;
        String shopListKey = CommonConst.CURRENT_SHOP_LIST + busId;
        log.info("=== sessionid {}", request.getSession().getId());
        try {
            // 读取session 中的门店列表
            Optional<String> shopInfoList = Optional.fromNullable((String) request.getSession().getAttribute(shopListKey));
            if (shopInfoList.isPresent()) {
                return ResponseDTO.createBySuccess(JSON.parseObject(shopInfoList.get(), new TypeReference<List<HotelShopInfo>>() {
                }));
            } else {
                // 获取商家下的酒店列表
                List<HotelShopInfo> hotelShopInfoList = null;
                Wrapper<THotel> wrapper = new EntityWrapper<>();
                wrapper.eq("bus_id", busId);
                wrapper.eq("mark_modified", CommonConst.ENABLED);
                List<THotel> hotels = tHotelService.selectList(wrapper);
                // 读取门店列表
                JSONObject json = wxmpApiUtil.queryWxShopByBusId(busId);
                if (json.getBoolean(CommonConst.SUCCESS)) {
                    shops = JSONArray.parseArray(json.getJSONArray("data").toJSONString(), HotelWsWxShopInfoExtend.class);
                    hotelShopInfoList = new ArrayList<>();
                    for (HotelWsWxShopInfoExtend shop : shops) {
                        HotelShopInfo shopInfo = new HotelShopInfo();
                        shopInfo.setShopId(shop.getId());
                        shopInfo.setName(shop.getBusinessName());
                        shopInfo.setTel(shop.getTelephone());
                        shopInfo.setAddr(shop.getAddress());
                        shopInfo.setImage(properties.getWxmpService().getImageUrl() + shop.getImageUrl());
                        for (THotel hotel : hotels) {
                            if (hotel.getShopId().equals(shopInfo.getShopId())) {
                                shopInfo.setHotelId(hotel.getId());
                            }
                        }
                        hotelShopInfoList.add(shopInfo);
                    }
                }
                request.getSession().setAttribute(shopListKey, JSON.toJSONString(hotelShopInfoList));
                return ResponseDTO.createBySuccess(hotelShopInfoList);
            }
        } catch (SignException e) {
            log.error("签名错误：{}", e.getMessage());
            throw new ResponseEntityException(ResponseEnums.SIGNATURE_ERROR);
        } catch (Exception e) {
            log.error("门店列表获取失败", e);
            throw new ResponseEntityException(ResponseEnums.SYSTEM_ERROR);
        }
    }

    /**
     * 获取门店信息
     *
     * @param request HttpServletRequest
     * @return ResponseDTO<>
     */
    @ApiOperation(value = "门店信息", notes = "根据门店ID获取门店信息")
    @GetMapping(value = "shop/{shopId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<HotelInfoVo> getShopInfoById(HttpServletRequest request, @PathVariable("shopId") Integer shopId) {
        Integer busId = getLoginUser(request).getId();
        HotelWsWxShopInfoExtend shop;
        try {
            HotelInfoVo hotelInfoVo = null;
            JSONObject json = wxmpApiUtil.getShopInfoById(shopId);
            if (json != null) {
                if (json.getInteger("code").equals(0)) {
                    shop = JSONObject.parseObject(json.get("data").toString(), HotelWsWxShopInfoExtend.class);
                    hotelInfoVo = new HotelInfoVo();
                    hotelInfoVo.setShopAddress(shop.getAddress());
                    hotelInfoVo.setShopId(shop.getId());
                    hotelInfoVo.setShopImage(properties.getWxmpService().getImageUrl() + shop.getImageUrl());
                    hotelInfoVo.setShopTel(shop.getTelephone());
                    hotelInfoVo.setShopName(shop.getBusinessName());
                    log.info(shop.toString());
                    // 填充酒店信息 根据门店ID获取酒店信息
                    Wrapper<THotel> wrapper = new EntityWrapper<>();
                    wrapper.eq("shop_id", shopId).eq("bus_id", busId).eq("mark_modified", 0);
                    THotel hotel = this.tHotelService.selectOne(wrapper);
                    if (hotel != null) {
                        BeanUtils.copyProperties(hotel, hotelInfoVo);
                    }
                } else {
                    log.error("获取错误信息：{}", json.getString("msg"));
                    return ResponseDTO.createByErrorMessage("获取门店信息失败");
                }
                return ResponseDTO.createBySuccess(hotelInfoVo);
            }
            log.warn("获取门店信息失败，Json数据包为null");
            return ResponseDTO.createByErrorMessage("获取门店信息失败");
        } catch (SignException e) {
            log.error("签名错误：{}", e.getMessage());
            throw new ResponseEntityException(ResponseEnums.SIGNATURE_ERROR);
        } catch (Exception e) {
            log.error("门店信息获取失败", e);
            throw new ResponseEntityException(ResponseEnums.SYSTEM_ERROR);
        }
    }
}
