package com.gt.hotel.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TFileRecord;
import com.gt.hotel.vo.FileRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文件记录表 — 文件实际存储在 素材库。此表仅做记录 Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface TFileRecordDAO extends BaseMapper<TFileRecord> {

    /**
     * 查找文件列表
     *
     * @param referenceId 引用ID
     * @param module      模块类型
     * @return List
     */
    List<FileRecordVo> findRecordVoListByRefIdAndModule(@Param("refId") Integer referenceId, @Param("module") String module);

    /**
     * 更新mark
     * @param fi
     * @param ids
     * @param mark
     */
	void updateFileRecordMark(@Param("file") TFileRecord fi, @Param("ids") List<Integer> ids, @Param("mark") Integer mark);

}