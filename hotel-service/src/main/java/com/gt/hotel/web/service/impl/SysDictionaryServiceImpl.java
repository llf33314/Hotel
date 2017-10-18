package com.gt.hotel.web.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dao.SysDictionaryDAO;
import com.gt.hotel.entity.SysDictionary;
import com.gt.hotel.vo.SysDictionaryVo;
import com.gt.hotel.web.service.SysDictionaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Override
	public Page<SysDictionaryVo> queryInvoice(Page<SysDictionaryVo> page) {
		page.setRecords(sysDictionaryDAO.queryInvoice(CommonConst.DICT_INVOICE, page));
		return page;
	}

}
