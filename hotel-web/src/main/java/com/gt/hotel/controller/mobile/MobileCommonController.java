package com.gt.hotel.controller.mobile;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.gt.api.bean.session.Member;
import com.gt.api.exception.SignException;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.MemberCard;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.ConfigVO;
import com.gt.hotel.web.service.THotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 酒店移动端 通用
 * @author Reverien9@gmail.com
 * 2017年11月21日 上午10:57:08
 */
@Api(tags = "酒店移动端 通用")
@RestController
@RequestMapping("/mobile/78CDF1/common")
public class MobileCommonController extends BaseController {

	@Autowired
    private WebServerConfigurationProperties properties;
	
	@Autowired
	THotelService tHotelService;
	
	@Autowired
	WXMPApiUtil wXMPApiUtil;

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
    
    @ApiOperation(value = "会员卡信息", notes = "会员卡信息")
    @GetMapping(value = "{hotelId}/member", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<MemberCard> moblieMember(
    		@PathVariable("hotelId") Integer hotelId, 
    		HttpServletRequest request) {
    	THotel hotel = tHotelService.selectById(hotelId);
    	Member member = getMember(request);
    	MemberCard memberCard = null;
    	try {
    		JSONObject json = wXMPApiUtil.findMemberCard(member.getPhone(), member.getBusid(), hotel.getShopId());
    		if(json != null && json.getInteger("code").equals(0)) {
    			memberCard = JSONObject.toJavaObject(json.getJSONObject("data"), MemberCard.class);
    		}
		} catch (SignException e) {
			throw new ResponseEntityException(ResponseEnums.FAILED_TO_OBTAIN_MEMBER_INFORMATION);
		}
        return ResponseDTO.createBySuccess(memberCard);
    }
}
