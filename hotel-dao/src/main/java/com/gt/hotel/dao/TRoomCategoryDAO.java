package com.gt.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.hotel.entity.TRoomCategory;
import com.gt.hotel.param.RoomCategoryParameter.MobileQueryRoomCategory;
import com.gt.hotel.param.RoomCategoryParameter.QueryRoomCategory;
import com.gt.hotel.vo.MobileRoomCategoryVo;
import com.gt.hotel.vo.RoomCategoryVo;

/**
 * <p>
 * 房型 Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface TRoomCategoryDAO extends BaseMapper<TRoomCategory> {

    /**
     * 查询 房型列表
     *
     * @param hotelId 酒店ID
     * @param page
     * @return
     */
    List<RoomCategoryVo> queryRoomCategory(@Param("queryRoomCategory") QueryRoomCategory param, @Param("page") Pagination page);

    /**
     * 移动端 首页房型列表
     * @param hotelId
     * @param req
     * @return
     */
//	List<MobileRoomCategoryVo> queryMobileRoomCategory(@Param("hotelId") Integer hotelId, @Param("param") MobileQueryRoomCategory req, 
//			@Param("page") Pagination page);
	List<MobileRoomCategoryVo> queryMobileRoomCategory(@Param("hotelId") Integer hotelId, @Param("param") MobileQueryRoomCategory req);

}