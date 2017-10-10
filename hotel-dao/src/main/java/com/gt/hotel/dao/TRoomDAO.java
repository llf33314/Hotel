package com.gt.hotel.dao;

<<<<<<< HEAD
import com.gt.hotel.entity.TRoom;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 房型关联 1 - n 客房信息
房型的客房数量 对应 客房数 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2017-10-09
 */
public interface TRoomDAO extends BaseMapper<TRoom> {
=======
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TRoom;

/**
 * <p>
 * 房型关联 1 - n 客房信息
 * 房型的客房数量 对应 客房数 Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface TRoomDAO extends BaseMapper< TRoom > {
>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6

}