package com.gt.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TFood;
import com.gt.hotel.param.FoodMobileParameter.FoodMobileQuery;
import com.gt.hotel.vo.FoodVo;

/**
 * <p>
 * 菜品 Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-20
 */
public interface TFoodDAO extends BaseMapper<TFood> {

	/**
	 * 查询 订餐设置 列表
	 * @param hpage
	 * @return
	 */
//	List<FoodVo> queryFood(@Param("param") FoodMobileQuery hpage, @Param("hotelId") Integer hotelId, @Param("page") Pagination page);
	List<FoodVo> queryFood(@Param("param") FoodMobileQuery hpage, @Param("hotelId") Integer hotelId);

}