package com.gt.hotel.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TFileRecordDAO;
import com.gt.hotel.entity.TFileRecord;
import com.gt.hotel.web.service.TFileRecordService;

/**
 * <p>
 * 文件记录表 — 文件实际存储在 素材库。此表仅做记录 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Service
public class TFileRecordServiceImpl extends BaseServiceImpl<TFileRecordDAO, TFileRecord> implements TFileRecordService {

	@Autowired
	private TFileRecordDAO fileRecordDAO;
	
	@Override
	public void updateFileRecordMark(TFileRecord fi, List<Integer> ids, Integer mark) {
		fileRecordDAO.updateFileRecordMark(fi, ids, mark);
	}


}
