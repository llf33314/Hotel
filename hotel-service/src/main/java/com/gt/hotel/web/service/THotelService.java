package com.gt.hotel.web.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.param.ERPParameter.ERPSave;
import com.gt.hotel.param.HotelParameter.HotelQuery;
import com.gt.hotel.param.HotelParameter.HotelSaveOrUpdate;
import com.gt.hotel.vo.HotelVo;

/**
 * <p>
 * 酒店主表 服务类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface THotelService extends BaseService<THotel> {

    /**
     * 查询 新增酒店 首页 酒店列表
     *
     * @param busid 用户ID
     * @param page  分页对象
     * @return 酒店列表
     */
    Page<HotelVo> queryHotelHome(Integer busid, HotelQuery hpage);

    /**
     * 查询酒店ERP对象
     *
     * @param hotelId 酒店ID
     * @return
     */
    HotelVo queryHotelERP(Integer hotelId);

    /**
     * 保存 ERP前台设置
     *
     * @param busid 用户ID
     * @param save
     */
    void SaveHotelERP(Integer busid, ERPSave save);

    /**
     * 编辑酒店信息
     * @param busid
     */
	void backHotelCU(Integer busid, HotelSaveOrUpdate hotel);


}
