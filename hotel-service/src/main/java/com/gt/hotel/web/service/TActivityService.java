package com.gt.hotel.web.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TActivity;
import com.gt.hotel.param.ActivityParamter.Query;
import com.gt.hotel.param.ActivityParamter.SaveOrUpdate;
import com.gt.hotel.vo.ActivityVo;

/**
 * <p>
 * 活动设置 - 抽出活动公共设置 服务类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface TActivityService extends BaseService< TActivity > {

	/**
	 * 查询活动列表
	 * @param param 酒店 活动 请求参数
	 * @param page 
	 * @return
	 */
	Page<ActivityVo> queryActivity(Query param);

	/**
	 * 编辑 活动房 信息 
	 * @param busid 用户ID
	 * @param arooms 酒店 活动 编辑 参数
	 */
	void editActivity(Integer busid, SaveOrUpdate arooms);

	/**
	 * 查询 活动 对象
	 * @param id 
	 * @return
	 */
	ActivityVo queryActivityOne(Integer id);


}
