package com.gt.hotel.controller.back;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.api.exception.SignException;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.entity.THotelMemberSetting;
import com.gt.hotel.entity.THotelSetting;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.HotelShopInfo;
import com.gt.hotel.other.HotelWsWxShopInfoExtend;
import com.gt.hotel.param.HotelParameter;
import com.gt.hotel.param.HotelParameter.HotelQuery;
import com.gt.hotel.util.QrCodeUtil;
import com.gt.hotel.util.ShortUrlUtil;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.HotelVo;
import com.gt.hotel.vo.LinkVo;
import com.gt.hotel.web.service.THotelMemberSettingService;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.THotelSettingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
    private THotelSettingService tHotelSettingService;

    @Autowired
    private WXMPApiUtil WXMPApiUtil;
    
    @Autowired
    THotelMemberSettingService hotelMemberSettingService; 
    
    @Autowired
    ShortUrlUtil shortUrlUtil;

    @Value("${wxmp.imageurl.prefixurl}")
    private String IMAGE_PREFIX;

    @ApiOperation(value = "门店列表", notes = "门店列表")
    @GetMapping(value = "queryShop", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<List<HotelShopInfo>> shopR(HttpSession session) {
        Integer busid = getLoginUserId(session);
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
                _s.setImage(IMAGE_PREFIX + shop.getImageUrl());
                s.add(_s);
            }
            return ResponseDTO.createBySuccess(s);
        } catch (SignException e) {
            logger.error("签名错误：{}", e.getMessage());
            throw new ResponseEntityException(ResponseEnums.SIGNATURE_ERROR);
        }
    }

    @ApiOperation(value = "酒店列表", notes = "酒店列表")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<HotelVo>> hotelR(HotelQuery hpage, HttpSession session) {
        Integer busid = getLoginUserId(session);
        Page<HotelVo> page = tHotelService.queryHotelHome(busid, hpage);
        return ResponseDTO.createBySuccess(page);
    }

    @ApiOperation(value = "新增或更新酒店", notes = "新增或更新酒店")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO hotelCU(@Validated @RequestBody HotelParameter.HotelSaveOrUpdate hotel, BindingResult bindingResult, HttpSession session) {
    	ResponseDTO msg = InvalidParameterII(bindingResult);
        if(msg != null) {
        	return msg;
        }
        Integer busid = getLoginUserId(session);
        Date date = new Date();
        THotel e = new THotel();
        BeanUtils.copyProperties(hotel, e);
        e.setBusId(busid);
        e.setId(hotel.getHotelId());
        e.setPhone(hotel.getTel());
        e.setAddress(hotel.getAddr());
        e.setShopId(hotel.getShopId());
        if (e.getId() == null) {
            e.setCreatedBy(busid);
            e.setCreatedAt(date);
        }
        e.setUpdatedBy(busid);
        e.setUpdatedAt(date);
        boolean f = e.insertOrUpdate();
        
        if (e.getId() == null) {
        	THotelSetting hs = new THotelSetting();
        	hs.setHotelId(e.getId());
        	f &= tHotelSettingService.insert(hs);
        	
        	List<THotelMemberSetting> hotelMemberSettings = new ArrayList<>();
        	for(int i = 0;i < 4;i++) {
        		THotelMemberSetting hotelMemberSetting = new THotelMemberSetting();
        		hotelMemberSetting.setHotelId(e.getId());
        		hotelMemberSetting.setVipLevel(i+1);
        		hotelMemberSettings.add(hotelMemberSetting);
        	}
        	f &= hotelMemberSettingService.insertBatch(hotelMemberSettings);
        }
        
        if (f) return ResponseDTO.createBySuccess();
        else return ResponseDTO.createByError();
    }

    @ApiOperation(value = "删除 酒店", notes = "删除 酒店")
    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO roomCategoryD(@RequestBody @ApiParam("酒店ID 数组") List<Integer> ids, HttpSession session) {
        Integer busid = getLoginUserId(session);
        Wrapper<THotel> wrapper = new EntityWrapper<>();
        wrapper.in("id", ids);
        THotel h = new THotel();
        h.setMarkModified(CommonConst.DELETED);
        h.setUpdatedAt(new Date());
        h.setUpdatedBy(busid);
        tHotelService.update(h, wrapper);
        return ResponseDTO.createBySuccess();
    }

    @ApiOperation(value = "链接", notes = "链接")
    @GetMapping(value = "{hotelId}/link", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<LinkVo> link(@PathVariable("hotelId") Integer hotelId, HttpServletRequest request) {
    	String url = getHost(request) + "/mobile/78CDF1" + hotelId + "/home/";
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
