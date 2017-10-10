package com.gt.hotel.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TRoomCategory;
import com.gt.hotel.responseEntity.ResRoomCategory;

/**
 * <p>
 * 房型 Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface TRoomCategoryDAO extends BaseMapper< TRoomCategory > {

	List<ResRoomCategory> queryRoomCategory(Map<String, Object> param);

	Integer queryRoomCategoryCount(Map<String, Object> param);

}