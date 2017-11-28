package com.gt.hotel.controller.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gt.api.bean.session.BusUser;
import com.gt.api.util.SessionUtils;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.vo.ConfigVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 酒店配置信息
 * @author Reverien9@gmail.com
 * 2017年11月8日 上午10:49:01
 */
@Api(tags = "酒店配置信息")
@RestController
@RequestMapping("/back/config")
public class HotelConfigController extends BaseController {

    @Autowired
    private WebServerConfigurationProperties properties;

    @ApiOperation(value = "配置", notes = "配置")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<ConfigVO> config(HttpServletRequest request) {
    	ConfigVO vo = new ConfigVO();
    	vo.setHostUrl(getHost(request));
    	vo.setPrefixUrl(properties.getWxmpService().getImageUrl());
    	vo.setSocketUrl(properties.getWxmpService().getSocketUrl());
    	vo.setApiUrl(properties.getWxmpService().getServiceUrl());
    	vo.setMaterialUrl(properties.getWxmpService().getMaterialUrl());
    	return ResponseDTO.createBySuccess(vo);
    }
    
    @ApiOperation(value = "用户信息", notes = "用户信息")
    @GetMapping(value = "user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<BusUser> user(HttpServletRequest request) {
    	return ResponseDTO.createBySuccess(getLoginUser(request));
    }
    
    @SuppressWarnings("rawtypes")
	@ApiOperation(value = "退出登录", notes = "退出登录")
    @GetMapping(value = "logOut", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO logOut(HttpServletRequest request) {
    	SessionUtils.setLoginUser(request, null);
    	return ResponseDTO.createBySuccess(properties.getWxmpService().getWxmpLogin());
    }
    
}
