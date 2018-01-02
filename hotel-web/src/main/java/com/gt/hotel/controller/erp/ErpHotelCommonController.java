package com.gt.hotel.controller.erp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.api.bean.sign.SignBean;
import com.gt.api.exception.SignException;
import com.gt.api.util.sign.SignUtils;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.entity.THotelMemberSetting;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.HotelShopInfo;
import com.gt.hotel.other.HotelWsWxShopInfoExtend;
import com.gt.hotel.param.RoomCategoryParameter;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.ConfigVO;
import com.gt.hotel.vo.HotelMemberSettingVo;
import com.gt.hotel.vo.HotelVo;
import com.gt.hotel.vo.RoomCategoryVo;
import com.gt.hotel.web.service.THotelMemberSettingService;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.TRoomCategoryService;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 酒店ERP - 通用
 * @author Reverien9@gmail.com
 * 2017年11月21日 上午10:52:21
 */
@Api(tags = "酒店ERP 通用")
@RestController
@RequestMapping("/erp/common")
public class ErpHotelCommonController extends BaseController {

	@Autowired
    private WXMPApiUtil wxmpApiUtil;

	@Autowired
    private WebServerConfigurationProperties properties;

	@Autowired
	TRoomCategoryService roomCategoryService;

	@Autowired
	THotelService hotelService;

	@Autowired
	THotelMemberSettingService hotelMemberSettingService;

    private static final Logger logger = LoggerFactory.getLogger(ErpHotelCommonController.class);

    @ApiOperation(value = "门店列表", notes = "门店列表")
    @GetMapping(value = "queryShop", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<List<HotelShopInfo>> shopR(HttpServletRequest request) {
        Integer busid = getLoginUser(request).getId();
        List<HotelWsWxShopInfoExtend> shops = null;
        try {
            JSONObject json = wxmpApiUtil.queryWxShopByBusId(busid);
            if (json.getBoolean("success")) {
                shops = JSONArray.parseArray(json.getJSONArray("data").toJSONString(),
                        HotelWsWxShopInfoExtend.class);
            }
            Wrapper<THotel> w = new EntityWrapper<>();
        	w.eq("bus_id", busid);
			List<THotel> hs = hotelService.selectList(w);
			List<HotelVo> hvs = new ArrayList<>();
			List<Integer> sids = new ArrayList<>();
			for(THotel h : hs) {
				HotelVo hv = new HotelVo();
				BeanUtils.copyProperties(h, hv);
				hv.setHotelId(h.getId());
				hvs.add(hv);
				sids.add(h.getShopId());
			}

            List<HotelShopInfo> s = new ArrayList<>();
			assert shops != null;
			for (HotelWsWxShopInfoExtend shop : shops) {
                if(sids.contains(shop.getId())) {
                	HotelShopInfo hsi = new HotelShopInfo();
                	hsi.setShopId(shop.getId());
                	hsi.setName(shop.getBusinessName());
                	hsi.setTel(shop.getTelephone());
                	hsi.setAddr(shop.getAddress());
                	hsi.setImage(properties.getWxmpService().getImageUrl() + shop.getImageUrl());
                	for(HotelVo hv : hvs) {
                		if(hv.getShopId().equals(shop.getId())) {
                			hsi.setHotelId(hv.getHotelId());
                			hsi.setHotelName(hv.getName());
                			hsi.setLogo(hv.getLogo());                		}
                	}
                	s.add(hsi);
                }
            }
            return ResponseDTO.createBySuccess(s);
        } catch (SignException e) {
            logger.error("签名错误：{}", e.getMessage());
            throw new ResponseEntityException(ResponseEnums.SIGNATURE_ERROR);
        }
    }

    @ApiOperation(value = "erp登录签名", notes = "erp登录签名")
    @GetMapping(value = "getSign", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<SignBean> getSign(
    		@ApiParam("账号") String username,
    		@ApiParam("密码") String password) {
    	Map<String, Object> map = new ConcurrentSkipListMap<String, Object>();
    	map.put("login_name", username);
    	map.put("password", password);
    	SignBean sign = SignUtils.sign(properties.getWxmpService().getSignKey(), JSONObject.toJSONString(map));
    	return ResponseDTO.createBySuccess(sign);
    }

    @ApiOperation(value = "erp退出登录", notes = "erp退出登录")
    @GetMapping(value = "logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<JSONObject> logout(HttpServletRequest request) {
    	Cookie cookie = new Cookie("WXMPJSESSIONID", null);
    	cookie.setMaxAge(0);
    	return ResponseDTO.createBySuccess();
    }

    @ApiOperation(value = "配置", notes = "配置")
    @GetMapping(value = "config", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<ConfigVO> config(HttpServletRequest request) {
    	ConfigVO vo = new ConfigVO();
    	vo.setHostUrl(getHost(request));
    	vo.setPrefixUrl(properties.getWxmpService().getImageUrl());
    	vo.setSocketUrl(properties.getWxmpService().getSocketUrl());
    	vo.setApiUrl(properties.getWxmpService().getServiceUrl());
    	vo.setMaterialUrl(properties.getWxmpService().getMaterialUrl());
    	return ResponseDTO.createBySuccess(vo);
    }

	@ApiOperation(value = "房间类型列表", notes = "房间类型列表")
	@GetMapping(value = "roomCategory/{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<Page<RoomCategoryVo>> getRoomCategory(
			@ApiParam("门店ID") @PathVariable("hotelId") Integer hotelId,
			@Validated @ModelAttribute RoomCategoryParameter.QueryRoomCategory param,
			BindingResult bindingResult) {
		invalidParameter(bindingResult);
		Page<RoomCategoryVo> page = roomCategoryService.queryRoomCategory(param);
		return ResponseDTO.createBySuccess(page);
	}

	@ApiOperation(value = "酒店信息", notes = "酒店信息")
	@GetMapping(value = "hotel/{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseDTO<HotelVo> getHotel(
			@ApiParam("门店ID") @PathVariable("hotelId") Integer hotelId) {
		Wrapper<THotel> arg0 = new EntityWrapper<>();
		arg0.eq("hotel_id", hotelId);
		THotel th = hotelService.selectOne(arg0);
        HotelVo h = new HotelVo();
        BeanUtils.copyProperties(th, h);
        h.setHotelId(th.getId());

        Wrapper<THotelMemberSetting> wrapper = new EntityWrapper<>();
        wrapper.eq("hotel_id", th.getId());
        List<THotelMemberSetting> hmss = hotelMemberSettingService.selectList(wrapper);
        List<HotelMemberSettingVo> hmsv = new ArrayList<>();
        for (THotelMemberSetting hms : hmss) {
            HotelMemberSettingVo v = new HotelMemberSettingVo();
            BeanUtils.copyProperties(hms, v);
            hmsv.add(v);
        }
        h.setMemberSetting(hmsv);
		return ResponseDTO.createBySuccess(h);
	}

}
