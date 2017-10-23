package com.gt.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.hotel.entity.TActivity;
import com.gt.hotel.param.ActivityParamter;
import com.gt.hotel.vo.ActivityVo;

/**
 * <p>
 * 活动设置 - 抽出活动公共设置 Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface TActivityDAO extends BaseMapper< TActivity > {

	/**
	 * 查询活动列表
	 * @param param 酒店 活动 请求参数
	 * @param page
	 * @return
	 */
	List<ActivityVo> queryActivity(@Param("param") ActivityParamter.ActivityQuery param, @Param("page") Pagination page);

}