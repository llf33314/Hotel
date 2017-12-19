package com.gt.hotel.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.THotelSetting;
import com.gt.hotel.vo.MobileHotelVo;

/**
 * <p>
 * 酒店移动端设置 Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface THotelSettingDAO extends BaseMapper<THotelSetting> {


    /**
     * 获取酒店Vo 对象
     *
     * @param hotelId 酒店ID
     * @return MobileHotelVo
     */
    MobileHotelVo findHotelVoById(Integer hotelId);


}