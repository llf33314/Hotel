package com.gt.hotel.controller.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
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

    @Value("${wxmp.imageurl.prefixurl}")
    private String IMAGE_PREFIX;
    
    @Value("${wxmp.socket.url}")
    private String SOCKET_URL;

    @ApiOperation(value = "配置", notes = "配置")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<ConfigVO> config(HttpServletRequest request) {
    	ConfigVO vo = new ConfigVO();
    	vo.setHostUrl(getHost(request));
    	vo.setPrefixUrl(IMAGE_PREFIX);
    	vo.setSocketUrl(SOCKET_URL);
    	return ResponseDTO.createBySuccess();
    }
}
