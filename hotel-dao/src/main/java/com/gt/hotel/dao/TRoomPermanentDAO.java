package com.gt.hotel.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.hotel.entity.TRoomPermanent;
import com.gt.hotel.param.RoomParameter.RoomPermanentQuery;
import com.gt.hotel.vo.RoomPermanentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 客房长包房 Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-18
 */
public interface TRoomPermanentDAO extends BaseMapper<TRoomPermanent> {

    /**
     * 查询 长包房设置
     *
     * @param param
     * @param page
     * @return
     */
    List<RoomPermanentVo> queryRoomPermanent(@Param("hotelId") Integer hotelId, @Param("param") RoomPermanentQuery param, @Param("page") Pagination page);

}