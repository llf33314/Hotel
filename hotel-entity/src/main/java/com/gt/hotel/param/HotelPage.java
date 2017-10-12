package com.gt.hotel.param;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页请求对象
 *
 * @author ReverieNight@Foxmail.com
 */
@Data
@Api( description = "分页请求对象" )
public class HotelPage {
    /**
     * 当前页数
     */
    @ApiModelProperty( "当前页数" )
    private Integer page     = 1;
    /**
     * 当前页条目数
     */
    @ApiModelProperty( "当前页条目数" )
    private Integer pageSize = 10;

}
