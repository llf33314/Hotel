package com.gt.hotel.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.responseEntity.HotelList;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 酒店主表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface THotelDAO extends BaseMapper< THotel > {

    /**
     * 查询 新增酒店 首页 酒店列表
     *
     * @param param
     *
     * @return
     */
    List< HotelList > queryHotelHome(Map< String, Object > param);

    /**
     * 查询 新增酒店 首页 酒店列表 统计
     *
     * @param param
     *
     * @return
     */
    Integer queryHotelHomeCount(Map< String, Object > param);

}