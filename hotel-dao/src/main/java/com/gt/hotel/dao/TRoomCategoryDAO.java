package com.gt.hotel.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.hotel.entity.TRoomCategory;
import com.gt.hotel.param.RoomCategoryParameter.QueryRoomCategory;
import com.gt.hotel.vo.RoomCategoryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 房型 Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface TRoomCategoryDAO extends BaseMapper< TRoomCategory > {

    /**
     * 查询 房型列表
     *
     * @param hotelId 酒店ID
     * @param page
     *
     * @return
     */
    List< RoomCategoryVo > queryRoomCategory(@Param( "queryRoomCategory" ) QueryRoomCategory param, @Param( "page" ) Pagination page);

}