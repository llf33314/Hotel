package com.gt.hotel.dao;

<<<<<<< HEAD
=======
import com.baomidou.mybatisplus.mapper.BaseMapper;
>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6
import com.gt.hotel.entity.THotel;
import com.gt.hotel.responseEntity.HotelList;

import java.util.List;
import java.util.Map;

<<<<<<< HEAD
import com.baomidou.mybatisplus.mapper.BaseMapper;

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
	 * @param param
	 * @return
	 */
	List<HotelList> queryHotelHome(Map<String, Object> param);

	/**
	 * 查询 新增酒店 首页 酒店列表 统计
	 * @param param
	 * @return
	 */
	Integer queryHotelHomeCount(Map<String, Object> param);
=======
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
>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6

}