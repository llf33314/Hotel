package com.gt.hotel.web.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.responseEntity.HotelList;

/**
 * <p>
 * 酒店主表 服务类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface THotelService extends BaseService< THotel > {

	/**
     * 查询 新增酒店 首页 酒店列表
     *
     * @param busid 用户ID
     * @param page  分页对象
     *
     * @return 酒店列表
     */
	Page<HotelList> queryHotelHome(Integer busid, Page<HotelList> page);

}
