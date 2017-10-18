package com.gt.hotel.param;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页请求对象
 *
 * @author ReverieNight@Foxmail.com
 */
@Data
@Api(description = "分页请求对象")
public class HotelPage {
    /**
     * 当前页数
     */
    @ApiModelProperty("当前页数")
    private Integer page     = 1;
    /**
     * 当前页条目数
     */
    @ApiModelProperty("当前页条目数")
    private Integer pageSize = 10;

    @ApiModelProperty("排序字段(asc or desc)")
    private String orderByField;

    @ApiModelProperty("查询条件参数")
    private String keyword;

    @SuppressWarnings("rawtypes") public Page initPage() {
    	return new Page(page, pageSize, orderByField);
    }
}
