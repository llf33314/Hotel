package com.gt.hotel.web.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TFood;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.vo.FoodVo;

/**
 * <p>
 * 菜品 服务类
 * </p>
 *
 * @author
 * @since 2017-10-20
 */
public interface TFoodService extends BaseService<TFood> {

    /**
     * 查询 订餐设置 列表
     *
     * @param hpage
     * @return
     */
    Page<FoodVo> queryFood(HotelPage hpage);

}
