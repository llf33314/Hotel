package com.gt.hotel.web.service;

import com.gt.hotel.entity.TAuthorization;
import com.gt.hotel.param.ERPParameter.AuthorSave;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.vo.AuthorizationVo;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;

/**
 * <p>
 * ERP 功能授权管理 服务类
 * </p>
 *
 * @author 
 * @since 2017-10-19
 */
public interface TAuthorizationService extends BaseService<TAuthorization> {

	/**
	 * 查询 授权管理列表
	 * @param param
	 * @return
	 */
	Page<AuthorizationVo> queryAuthor(HotelPage param);

	/**
	 * 删除 授权管理
	 * @param busId
	 * @param ids
	 */
	void delAuthor(Integer busId, List<Integer> ids);

	/**
	 * 新增 授权管理
	 * @param busid
	 * @param authors
	 */
	void saveAuthor(Integer busid, List<AuthorSave> authors);
	
}
