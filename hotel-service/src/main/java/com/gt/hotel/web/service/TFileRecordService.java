package com.gt.hotel.web.service;

import java.util.List;

import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TFileRecord;

/**
 * <p>
 * 文件记录表 — 文件实际存储在 素材库。此表仅做记录 服务类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface TFileRecordService extends BaseService<TFileRecord> {

	/**
	 * 更新mark
	 * @param fi
	 * @param ids id集合
	 * @param mark mark_modified
	 */
	void updateFileRecordMark(TFileRecord fi, List<Integer> frsu, Integer deleted);

}
