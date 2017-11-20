package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api("配置")
@Data
public class ConfigVO {

	@ApiModelProperty("图片前缀URL")
	private String prefixUrl;
	
	@ApiModelProperty("socket监听地址")
	private String socketUrl;
	
	@ApiModelProperty("服务器根路径")
	private String hostUrl;
	
	@ApiModelProperty("API服务器路径")
	private String apiUrl;
	
	@ApiModelProperty("素材库服务器路径")
	private String materialUrl;
	
}
