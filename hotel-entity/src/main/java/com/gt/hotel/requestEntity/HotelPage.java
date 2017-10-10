package com.gt.hotel.requestEntity;

import com.gt.hotel.util.RequestBeanUtil;
<<<<<<< HEAD

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页请求对象
 * @author ReverieNight@Foxmail.com
 *
 */
@Api(description = "分页请求对象")
public class HotelPage {
	/**
	 * 当前页数
	 */
	@ApiModelProperty("当前页数" + RequestBeanUtil.OPTIONAL)
	private Integer page = 1;
	/**
	 * 当前页条目数
	 */
	@ApiModelProperty("当前页条目数" + RequestBeanUtil.OPTIONAL)
	private Integer pageSize = 10;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "HotelPage [page=" + page + ", pageSize=" + pageSize + "]";
	}
	
=======
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
    @ApiModelProperty( "当前页数" + RequestBeanUtil.OPTIONAL )
    private Integer page     = 1;
    /**
     * 当前页条目数
     */
    @ApiModelProperty( "当前页条目数" + RequestBeanUtil.OPTIONAL )
    private Integer pageSize = 10;

>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6
}
