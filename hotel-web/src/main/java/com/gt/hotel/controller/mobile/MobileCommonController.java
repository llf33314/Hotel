package com.gt.hotel.controller.mobile;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.vo.ConfigVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 酒店移动端 通用
 * @author Reverien9@gmail.com
 * 2017年11月21日 上午10:57:08
 */
@Api(tags = "酒店移动端 通用")
@RestController
@RequestMapping("/mobile/78CDF1/config")
public class MobileCommonController extends BaseController {

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
}
