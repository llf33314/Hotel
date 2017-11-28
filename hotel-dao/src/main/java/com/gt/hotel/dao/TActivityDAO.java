package com.gt.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.hotel.entity.TActivity;
import com.gt.hotel.param.ActivityParamter;
import com.gt.hotel.vo.ActivityVo;
import com.gt.hotel.vo.MobileActivityRoomCategoryVo;
import com.gt.hotel.vo.MobileActivityVo;

/**
 * <p>
 * 活动设置 - 抽出活动公共设置 Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface TActivityDAO extends BaseMapper<TActivity> {

    /**
     * 查询活动列表
     *
     * @param param 酒店 活动 请求参数
     * @param page
     * @return
     */
    List<ActivityVo> queryActivity(@Param("param") ActivityParamter.ActivityQuery param, @Param("page") Pagination page);

    /**
     * 首页房型 活动 列表
     * @param hotelId
     * @return
     */
	List<MobileActivityVo> queryMobileActivity(Integer hotelId);

	/**
	 * 首页房型 活动 房型 列表
	 * @param hotelId
	 * @param activityId
	 * @param hotelPage
	 * @return
	 */
	List<MobileActivityRoomCategoryVo> queryMobileActivityRoomCategoryList(@Param("hotelId") Integer hotelId, @Param("activityId") Integer activityId,
			@Param("page") Pagination hotelPage);


}