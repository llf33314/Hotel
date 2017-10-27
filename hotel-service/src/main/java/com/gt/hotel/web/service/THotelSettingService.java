package com.gt.hotel.web.service;

import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.THotelSetting;
import com.gt.hotel.param.HotelMobileParameter.MobileSaveOrUpdate;
import com.gt.hotel.vo.HotelSettingVo;
import com.gt.hotel.vo.InfrastructureVo;

import java.util.List;

/**
 * <p>
 * 酒店移动端设置 服务类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface THotelSettingService extends BaseService<THotelSetting> {

    /**
     * 查询 移动端设置 对象
     *
     * @param hotelId 酒店ID
     * @return
     */
    HotelSettingVo queryHotelSettingOne(Integer hotelId);

    /**
     * 保存 移动端设置
     *
     * @param busid   用户ID
     * @param setting
     */
    void saveSetting(Integer busid, MobileSaveOrUpdate setting);

    /**
     * 酒店移动端设备
     *
     * @return
     */
    List<InfrastructureVo> queryHotelSettingInfrastructure();

}
