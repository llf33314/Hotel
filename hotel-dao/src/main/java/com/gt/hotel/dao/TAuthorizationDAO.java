package com.gt.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.hotel.entity.TAuthorization;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.vo.AuthorizationVo;

/**
 * <p>
 * ERP 功能授权管理 Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-19
 */
public interface TAuthorizationDAO extends BaseMapper<TAuthorization> {

    /**
     * 查询 授权管理列表
     *
     * @param param
     * @return
     */
	List<AuthorizationVo> queryAuthor(@Param("hotelId") Integer hotelId, @Param("param") HotelPage param, @Param("page") Pagination page);


}