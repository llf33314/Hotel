package com.gt.hotel.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.param.HotelParameter;
import com.gt.hotel.vo.HotelVo;
import com.gt.hotel.vo.MobileHotelVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 酒店主表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface THotelDAO extends BaseMapper<THotel> {

    /**
     * 查询 新增酒店 首页 酒店列表
     *
     * @param page
     * @param busid 用户ID
     * @return
     */
    List<HotelVo> queryHotelHome(@Param("page") Pagination page, @Param("req") HotelParameter.HotelQuery hpage, @Param("busId") Integer busid);

    /**
     * 获取酒店Vo 对象
     *
     * @param hotelId 酒店ID
     * @return MobileHotelVo
     */
    MobileHotelVo findHotelVoById(Integer hotelId);


}