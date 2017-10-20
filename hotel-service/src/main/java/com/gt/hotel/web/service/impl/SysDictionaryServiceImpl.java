package com.gt.hotel.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.SysDictionaryDAO;
import com.gt.hotel.entity.SysDictionary;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.vo.SysDictionaryVo;
import com.gt.hotel.web.service.SysDictionaryService;

/**
 * <p>
 * 系统的字典数据表 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Service
public class SysDictionaryServiceImpl extends BaseServiceImpl< SysDictionaryDAO, SysDictionary > implements SysDictionaryService {
	
	@Autowired
	SysDictionaryDAO sysDictionaryDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<SysDictionaryVo> queryDictionary(Integer dictId, HotelPage param) {
		Page<SysDictionaryVo> page = param.initPage();
		page.setRecords(sysDictionaryDAO.queryInvoice(dictId, page));
		return page;
	}

}
