package com.gt.hotel.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.hotel.entity.TRoom;
import com.gt.hotel.vo.RoomVo;
import com.gt.hotel.vo.erp.ErpRoomVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 房型关联 1 - n 客房信息
 * 房型的客房数量 对应 客房数 Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface TRoomDAO extends BaseMapper<TRoom> {

    /**
     * 查询 房间列表
     *
     * @param param 房间请求对象
     * @param page
     * @return
     */
    List<RoomVo> queryRoomList(@Param("hotelId") Integer hotelId, @Param("categoryId") Integer categoryId, @Param("page") Pagination page);

    /**
     * @param hotelId
     * @param categoryId
     * @return
     */
    List<RoomVo> queryRoomListAll(@Param("hotelId") Integer hotelId, @Param("categoryId") Integer categoryId, @Param("roomNumber") String roomNumber);

    /**
     * <pre>
     *      categoryId is null 查找 某酒店下的客房列表
     *      if categoryId is not null 查找某酒店下某房型下的客房列表
     * </pre>
     *
     * @param hotelId
     * @param categoryId
     * @return
     */
    List<ErpRoomVo> findRoomList(@Param("hotelId") Integer hotelId, @Param("categoryId") Integer categoryId);


    /**
     * 有效的客房
     * 如果，返回的结果 >1 则存在某一客房在这时间某段内已经被入住
     *
     * @param ids List<Integer>
     * @return
     */
    int checkValidRooms(List<Integer> ids);
}