package com.gt.hotel.param;

import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author ReverieNight@Foxmail.com
 * @date 2017年10月12日 上午9:39:36
 */
@SuppressWarnings("rawtypes")
@Data
public class HotelPageBean<T>{
	
	@ApiModelProperty( "当前页数" )
	private Integer page = 1;

	@ApiModelProperty( "当前页条目数" )
	private Integer pageSize = 10;
    
	@ApiModelProperty( "排序字段(asc or desc)" )
    private String orderByField;
	
	@ApiModelProperty( "查询条件参数" )
    private String keyword;
	
	@ApiModelProperty( "实体参数" )
	private T t;
	
	public HotelPageBean(){
		
	}
	
	public HotelPageBean(T t){
		this.t = t;
	}

	public Page initPage() {
		return new Page(page, pageSize, orderByField);
	}
}
