package com.gt.hotel.web.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.SysDictionary;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.vo.SysDictionaryVo;

/**
 * <p>
 * 系统的字典数据表 服务类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface SysDictionaryService extends BaseService<SysDictionary> {

    /**
     * 查询 字典 列表
     *
     * @param dictName 字典名
     * @param param
     * @return
     */
    Page<SysDictionaryVo> queryDictionary(Integer dictId, HotelPage param);


}
