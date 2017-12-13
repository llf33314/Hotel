package com.gt.hotel.controller.back;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.base.Optional;
import com.gt.api.exception.SignException;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.HotelShopInfo;
import com.gt.hotel.other.HotelWsWxShopInfoExtend;
import com.gt.hotel.param.HotelParameter;
import com.gt.hotel.param.HotelParameter.HotelQuery;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.QrCodeUtil;
import com.gt.hotel.util.ShortUrlUtil;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.HotelVo;
import com.gt.hotel.vo.LinkVo;
import com.gt.hotel.web.service.THotelMemberSettingService;
import com.gt.hotel.web.service.THotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 酒店管理-新增酒店
 *
 * @author Reverien9@gmail.com
 * 2017年10月25日 下午12:03:49
 */
@Api(tags = "酒店管理-新增酒店")
@RestController
@RequestMapping("/back/hotel")
public class HotelController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private THotelService tHotelService;

    @Autowired
    private WXMPApiUtil wxmpApiUtil;

    @Autowired
    THotelMemberSettingService hotelMemberSettingService;

    @Autowired
    ShortUrlUtil shortUrlUtil;

    @Autowired
    private WebServerConfigurationProperties properties;

    @ApiOperation(value = "门店列表", notes = "门店列表")
    @GetMapping(value = "queryShop", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<List<HotelShopInfo>> shopR(HttpServletRequest request) {
        Integer busId = getLoginUser(request).getId();
        List<HotelWsWxShopInfoExtend> shops;
        try {
        	Wrapper<THotel> w = new EntityWrapper<>();
        	w.eq("bus_id", busId);
        	w.eq("mark_modified", CommonConst.ENABLED);
			List<THotel> hs = tHotelService.selectList(w);
			List<Integer> ids = new ArrayList<>();
			for(THotel h : hs) {
				ids.add(h.getShopId());
			}
            JSONObject json = wxmpApiUtil.queryWxShopByBusId(busId);
            List<HotelShopInfo> hotelShopInfoList = null;
            if (json.getBoolean(CommonConst.SUCCESS)) {
                shops = JSONArray.parseArray(json.getJSONArray("data").toJSONString(),HotelWsWxShopInfoExtend.class);
                System.err.println(shops);
                hotelShopInfoList = new ArrayList<>();
                for (HotelWsWxShopInfoExtend shop : shops) {
                    HotelShopInfo shopInfo = new HotelShopInfo();
                    shopInfo.setShopId(shop.getId());
                    shopInfo.setName(shop.getBusinessName());
                    shopInfo.setTel(shop.getTelephone());
                    shopInfo.setAddr(shop.getAddress());
                    shopInfo.setImage(properties.getWxmpService().getImageUrl() + shop.getImageUrl());
                    if(!ids.contains(shop.getId())) {
                    	hotelShopInfoList.add(shopInfo);
                    }
                }
            }
            return ResponseDTO.createBySuccess(hotelShopInfoList);
        } catch (SignException e) {
            logger.error("签名错误：{}", e.getMessage());
            throw new ResponseEntityException(ResponseEnums.SIGNATURE_ERROR);
        }
    }



    @ApiOperation(value = "酒店列表", notes = "酒店列表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<HotelVo>> hotelR(HotelQuery hpage, HttpServletRequest request) {
        Integer busId = getLoginUser(request).getId();
        Page<HotelVo> page = tHotelService.queryHotelHome(busId, hpage);
        return ResponseDTO.createBySuccess(page);
    }
    
    @ApiOperation(value = "酒店对象", notes = "酒店对象")
    @GetMapping(value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<HotelVo> hotelObjectR(@PathVariable("hotelId") Integer hotelId) {
    	HotelVo h = new HotelVo();
		THotel th = tHotelService.selectById(hotelId);
        BeanUtils.copyProperties(th, h);
        h.setHotelId(th.getId());
        try {
        	HotelWsWxShopInfoExtend shop = null;
            JSONObject json = wxmpApiUtil.getShopInfoById(h.getShopId());
            if (json.getBoolean(CommonConst.SUCCESS)) {
                shop = JSONArray.parseObject(json.getJSONObject("data").toJSONString(), HotelWsWxShopInfoExtend.class);
	    		h.setShopAddr(shop.getAddress());
	    		h.setShopPhone(shop.getTelephone());
	    		h.setShopName(shop.getBusinessName());
            }
            return ResponseDTO.createBySuccess(h);
        } catch (SignException e) {
            logger.error("签名错误：{}", e.getMessage());
            throw new ResponseEntityException(ResponseEnums.SIGNATURE_ERROR);
        }
    }

    @ApiOperation(value = "新增或更新酒店", notes = "新增或更新酒店")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO hotelCU(@Validated @RequestBody HotelParameter.HotelSaveOrUpdate hotel, 
    		BindingResult bindingResult, 
    		HttpServletRequest request) {
        ResponseDTO msg = invalidParameterII(bindingResult);
        if (msg != null) {
            return msg;
        }
        Integer busid = getLoginUser(request).getId();
        tHotelService.backHotelCU(busid, hotel);
        return ResponseDTO.createBySuccess();
    }

    @ApiOperation(value = "删除 酒店", notes = "删除 酒店")
    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO roomCategoryD(@RequestBody @ApiParam("酒店ID 数组") List<Integer> ids, HttpServletRequest request) {
        Integer busid = getLoginUser(request).getId();
        Wrapper<THotel> wrapper = new EntityWrapper<>();
        wrapper.in("id", ids);
        THotel h = new THotel();
        h.setMarkModified(CommonConst.DELETED);
        h.setUpdatedAt(new Date());
        h.setUpdatedBy(busid);
        tHotelService.update(h, wrapper);
        // 删除成功后，判断session是否有缓存
        Optional<Object> o = Optional.fromNullable(request.getSession().getAttribute(CommonConst.CURRENT_SESSION_SHOP_LIST));
        if(o.isPresent()){
           request.getSession().removeAttribute(CommonConst.CURRENT_SESSION_SHOP_LIST);
        }
        return ResponseDTO.createBySuccess();
    }

    @ApiOperation(value = "链接", notes = "链接")
    @GetMapping(value = "{hotelId}/link", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<LinkVo> link(@PathVariable("hotelId") Integer hotelId, HttpServletRequest request) {
        String url = getHost(request) + "/mobile/78CDF1/home/" + hotelId;
        LinkVo link = new LinkVo();
        link.setLongUrl(url);
        link.setShortUrl(shortUrlUtil.getShorUrl(url));
        return ResponseDTO.createBySuccess(link);
    }

    @ApiOperation(value = "二维码", notes = "二维码")
    @GetMapping(value = "/qrcode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void qrcode(String url, HttpServletResponse response) {
        OutputStream outputStream;
        try {
            outputStream = new BufferedOutputStream(response.getOutputStream());
            BufferedImage bi = QrCodeUtil.encode(url, null, "H", null, outputStream, 500, 500, 1);
            response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode("二维码.jpg", "UTF-8") + "\"");
            response.setContentType("application/octet-stream");
            outputStream = new BufferedOutputStream(response.getOutputStream());
            ImageIO.write(bi, "jpg", outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
