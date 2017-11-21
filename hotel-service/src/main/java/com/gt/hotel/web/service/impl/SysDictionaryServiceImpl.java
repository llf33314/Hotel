package com.gt.hotel.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.SysDictionaryDAO;
import com.gt.hotel.entity.SysDictionary;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.vo.MobileHotelVo;
import com.gt.hotel.vo.SysDictionaryVo;
import com.gt.hotel.web.service.SysDictionaryService;
import com.gt.hotel.web.service.THotelSettingService;

/**
 * <p>
 * 系统的字典数据表 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Service
public class SysDictionaryServiceImpl extends BaseServiceImpl<SysDictionaryDAO, SysDictionary> implements SysDictionaryService {

    @Autowired
    SysDictionaryDAO sysDictionaryDAO;

    @Autowired
    THotelSettingService tHotelSettingService;

    @SuppressWarnings("unchecked")
    @Override
    public Page<SysDictionaryVo> queryDictionary(Integer dictId, HotelPage param) {
        Page<SysDictionaryVo> page = param.initPage();
        page.setRecords(sysDictionaryDAO.queryInvoice(dictId, page));
        return page;
    }

	@Override
	public Page<SysDictionaryVo> MobileQueryDictionary(Integer dictInvoice, Integer hotelId) {
		Page<SysDictionaryVo> page = new Page<>();
		List<SysDictionaryVo> l = sysDictionaryDAO.queryInvoice(dictInvoice);
		List<SysDictionaryVo> ll = new ArrayList<SysDictionaryVo>();
		MobileHotelVo h = tHotelSettingService.queryHotelSettingOne(hotelId);
		String[] is = null;
		if(h.getInvoiceCategorys() != null) {
			is = h.getInvoiceCategorys().split(",");
			List<String> ics = Arrays.asList(is);
			for(SysDictionaryVo s : l) {
				if(ics.contains(s.getId().toString())) {
					ll.add(s);
				}
			}
		}
        page.setRecords(ll);
        return page;
	}

}
